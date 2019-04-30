package was.servlet;


import was.config.ServletConfig;
import was.http.HttpRequest;
import was.http.HttpResponse;

import java.io.IOException;

public interface SimpleServlet {
    void init(ServletConfig servletConfig);
    void service(HttpRequest request, HttpResponse response) throws IOException;
    void destroy();
}
