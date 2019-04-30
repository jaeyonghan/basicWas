package was.servlet.impl.currenttime;

import was.config.ServletConfig;
import was.http.HttpRequest;
import was.http.HttpResponse;
import was.servlet.impl.HttpServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentTime extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CurrentTime.class.getCanonicalName());
    private String indexFileName = "index.html";
    String today ;

    @Override
    public void init(ServletConfig servletConfig) {
        super.init(servletConfig);

        Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        today = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        super.service(request, response);
        logger.debug("method is {} : ", request.getMethod());
        switch (request.getMethod()){
            case "GET":
                doGet(request, response);
                break;
            case "POST":
                doPost(request, response);
                break;
            case "OPTION":
                doOption(request, response);
                break;
        }
    }

    @Override
    public void doGet(HttpRequest httpRequest, HttpResponse response) throws IOException {
        String body = new StringBuilder("<HTML>\r\n")
                .append("<HEAD><TITLE>Current Time</TITLE>\r\n")
                .append("</HEAD>")
                .append("<BODY>")
                .append("<H1>")
                .append(today)
                .append("</H1>")
                .append("</BODY></HTML>")
                .toString();

        response.sendHeader(200, body.length());
        response.send(body);
    }

    @Override
    public void doPost(HttpRequest httpRequest, HttpResponse response) {
    }

    @Override
    public void doOption(HttpRequest httpRequest, HttpResponse response) {
    }

}
