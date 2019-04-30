package was.config;

public interface ServletConfig {
    String getHost();
    String getRoot();
    String getResource(String statusCode);
    String getService(String endpoint);
}
