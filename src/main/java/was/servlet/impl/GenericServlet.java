package was.servlet.impl;

import was.config.ServletConfig;
import was.http.HttpRequest;
import was.http.HttpResponse;
import was.servlet.SimpleServlet;

import java.io.IOException;

public class GenericServlet implements SimpleServlet {
    protected ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
    }

    @Override
    public void destroy() {
        servletConfig = null;
    }
}
