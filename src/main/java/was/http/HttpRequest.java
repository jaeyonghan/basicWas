package was.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private Map<String, String> header;
    private Map<String, String> params;
    private String host;
    private String path;
    private String protocol;
    private String body;
    private String method;

    public HttpRequest(Socket connection) throws IOException {
        this.header = new HashMap<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        String[] requestLine = in.readLine().split("\\s+");
        this.method = requestLine[0];
        this.path = requestLine[1];
        this.protocol = requestLine[2];

        int dividerIndex;

        //header
        while (!(inputLine = in.readLine()).equals("")){
            dividerIndex = inputLine.indexOf(":");
            this.header.put(inputLine.substring(0, dividerIndex).toLowerCase(),
                inputLine.substring(dividerIndex+1).trim());
        }

        //body
        if(this.header.containsKey("content-length") &&
            Integer.parseInt(this.header.get("content-length")) > 0){
            char[] bodyArray = new char[Integer.parseInt(this.header.get("content-length"))];
            in.read(bodyArray);
            this.body = new String(bodyArray);
        }

        this.host = this.header.get("host");
    }

    public String getHost() {
        return this.host;
    }

    public String getPath() {
        return this.path;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public String getHeader(String key) {
        return header.get(key);
    }

    public String getParams(String key) {
        return params.get(key);
    }

    public String getBody() {
        return this.body;
    }

    public String getMethod() {
        return this.method;
    }
}
