package was.http;

import java.io.*;
import java.net.Socket;

public class HttpResponse {
    private Writer out;
    private OutputStream str;

    public HttpResponse(Socket connection) throws IOException {
        this.str = new BufferedOutputStream(connection.getOutputStream());
        this.out = new OutputStreamWriter(str);
    }

    public void send(byte[] data) throws IOException {
        this.str.write(data);
        this.str.flush();
    }

    public void send(String data) throws IOException {
        out.write(data);
        out.flush();
    }

    public void sendHeader(Integer responseCode, int length)
            throws IOException {
        if(responseCode == 200){
            out.write("HTTP/1.1 200 OK" + "\r\n");
        }else if(responseCode == 403){
            out.write("HTTP/1.1 403 Forbidden" + "\r\n");
        }else if(responseCode == 404){
            out.write("HTTP/1.1 404 File Not Found" + "\r\n");
        }else if(responseCode == 500){
            out.write("HTTP/1.1 500 Internal Server Error" + "\r\n");
        }

        out.write(responseCode + "\r\n");
        out.write("Server: HTTP 1.1\r\n");
        out.write("Content-length: " + length + "\r\n");
        out.write("Content-type: text/html; charset=utf-8" + "\r\n\r\n");
        out.flush();
    }
}
