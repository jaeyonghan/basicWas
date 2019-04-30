package was.server;

import was.config.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpServer {
    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class.getCanonicalName());
    private static final int NUM_THREADS = 50;
    private static Settings settings;

    public HttpServer() {
        try {
            settings = setSettings();
        } catch (Exception e) {
            logger.error("Init Exception : {}", e.getMessage());
            return;
        }
    }

    public Settings setSettings() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("src/main/resources/setting.json"), Settings.class);
    }

    public static Settings getSettings() {
        return settings;
    }

    public void run() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        ServerSocket server = new ServerSocket(settings.port);
        logger.info("server port is : {} " , server.getLocalPort());

        while (true) {
            try {
                Socket request = server.accept();
                Runnable r = new RequestProcessor(request);
                pool.submit(r);
            } catch (Exception e) {
                logger.error("Error accepting connection", e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            new HttpServer().run();
        } catch (Exception e) {
            logger.error("Server error : {}", e.getMessage());
        }
    }
}