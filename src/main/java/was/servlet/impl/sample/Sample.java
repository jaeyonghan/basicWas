package was.servlet.impl.sample;

import was.http.HttpRequest;
import was.http.HttpResponse;
import was.servlet.impl.HttpServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Sample extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(Sample.class.getCanonicalName());
    private String indexFileName = "index.html";

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        super.service(request, response);

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
    public void doGet(HttpRequest httpRequest, HttpResponse response) {
        httpRequest.getPath();
    }

    @Override
    public void doPost(HttpRequest httpRequest, HttpResponse response) {
        httpRequest.getPath();
    }

    @Override
    public void doOption(HttpRequest httpRequest, HttpResponse response) {
        httpRequest.getPath();
    }

    @Override
    public void destroy() { }
}
