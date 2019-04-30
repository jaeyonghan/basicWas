package was.config.impl;

import was.config.ServletConfig;
import was.servlet.impl.HttpServlet;

import java.util.Map;

public class HttpConfig implements ServletConfig {
    private String host;
    private Map<String, String> mapper;
    private Map<String, String> resource;

    public HttpConfig(){

    }

    public HttpConfig(String host, Map<String, String> mapper, Map<String, String> resource) {
        this.host = host;
        this.mapper = mapper;
        this.resource = resource;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setMapper(Map<String, String> mapper) {
        this.mapper = mapper;
    }

    public void setResource(Map<String, String> resource) {
        this.resource = resource;
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public String getRoot() {
        return this.resource.get("root");
    }

    @Override
    public String getResource(String statusCode) {
        return this.resource.get(statusCode.toString());
    }

    @Override
    public String getService(String endpoint) {
        String mappingPath = this.mapper.get(endpoint);

        if(mappingPath == null){
            return endpoint;
        }

        return this.mapper.get(endpoint);
    }
}
