package was.server;

import was.config.ServletConfig;
import was.config.Settings;
import was.exception.BasicWasException;
import was.http.HttpRequest;
import was.http.HttpResponse;
import was.servlet.SimpleServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;

public class RequestProcessor implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestProcessor.class.getCanonicalName());
    private Socket connection;

    public RequestProcessor(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {

            HttpRequest request = new HttpRequest(connection);
            HttpResponse response = new HttpResponse(connection);

            Settings settings = HttpServer.getSettings();

            for(ServletConfig info : settings.server){
                if(info.getHost().equals(request.getHost())){
                    ServletConfig servletConfig = info;
                    try {
                        SimpleServlet simpleServlet = getServlet(servletConfig, request.getHost(), request.getPath());
                        simpleServlet.init(servletConfig);
                        simpleServlet.service(request, response);

                    }catch (BasicWasException e) {
                        handlingError(e.code, response);
                    }catch (IllegalAccessException | InstantiationException e) {
                        logger.error("Failed init servlet", e);
                        handlingError(500,servletConfig.getResource("root") +"/" + servletConfig.getResource("500"),
                                response);
                    }catch (ClassNotFoundException e) {
                        logger.error("Server Error", e);
                        handlingError(404,servletConfig.getResource("root") +"/" +servletConfig.getResource("404"),
                                response);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("Error Occur " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
            }
        }
    }

    private void handlingError(Integer statusCode, HttpResponse response) throws IOException {
        String htmlPath = "src/main/resources/html/default/";
        switch (statusCode) {
            case 403:
                htmlPath = htmlPath + "403.html";
                break;
            case 404:
                htmlPath = htmlPath + "404.html";
                break;
            case 500:
                htmlPath = htmlPath + "500.html";
                break;
            default:
                htmlPath = htmlPath + "500.html";
                break;
        }

        File theFile = new File(htmlPath);
        response.sendHeader(statusCode, (int) theFile.length());
        response.send(Files.readAllBytes(theFile.toPath()));
    }

    private void handlingError(int statusCode, String resourcePath, HttpResponse response) throws IOException {
        File theFile = new File("src/main/resources/html/" + resourcePath);
        response.sendHeader(statusCode, (int) theFile.length());
        response.send(Files.readAllBytes(theFile.toPath()));
    }

    private SimpleServlet getServlet(ServletConfig servletConfig, String host, String path) throws BasicWasException, IllegalAccessException, InstantiationException, ClassNotFoundException {

        //servlet(class) mapping
        StringBuilder stringBuilder = new StringBuilder("was.servlet.impl.");

        switch (host) {
            case "currenttime.com":
                stringBuilder.append("currenttime.");
                break;
            case "sample.com":
                stringBuilder.append("sample.");
                break;
            default:
                throw new BasicWasException("Domain Not Found", 404);
        }

        //Call the class using path
        String mappingClassName = servletConfig.getService(path.substring(1));
        stringBuilder.append(mappingClassName);

        return (SimpleServlet) Class.forName(stringBuilder.toString()).newInstance();
    }
}