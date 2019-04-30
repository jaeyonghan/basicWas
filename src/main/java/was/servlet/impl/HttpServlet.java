package was.servlet.impl;

import was.config.ServletConfig;
import was.config.impl.HttpConfig;
import was.http.HttpRequest;
import was.http.HttpResponse;

import java.io.File;
import java.io.IOException;

public class HttpServlet extends GenericServlet {
    private HttpConfig httpConfig;
    protected File rootDirectory;

    @Override
    public void init(ServletConfig servletConfig) {
        this.httpConfig = (HttpConfig) servletConfig;
        this.rootDirectory = new File("src/main/resources/html/" +
                this.httpConfig.getRoot());
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        super.service(request, response);
    }

    public void doGet(HttpRequest httpRequest, HttpResponse response) throws IOException {

    }

    public void doPost(HttpRequest httpRequest, HttpResponse response){

    }

    public void doOption(HttpRequest httpRequest, HttpResponse response){

    }

}
