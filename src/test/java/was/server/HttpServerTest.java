package was.server;

import was.config.Settings;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HttpServerTest {
    private Settings settings;
    private HttpServer httpServer;

    @Before
    public void initServer() {
        httpServer = new HttpServer();
    }

    @Test
    public void checkSettings() {
        settings = httpServer.getSettings();

        Assert.assertEquals(settings.port.longValue(), 8081);
        Assert.assertEquals(settings.server.size(), 2);
        Assert.assertEquals(settings.server.get(0).getHost(), "sample.com");
        Assert.assertEquals(settings.server.get(0).getRoot(), "sample");
        Assert.assertEquals(settings.server.get(0).getResource("403"), "403.html");
        Assert.assertEquals(settings.server.get(0).getResource("404"), "404.html");
        Assert.assertEquals(settings.server.get(0).getResource("500"), "500.html");

        Assert.assertEquals(settings.server.get(1).getHost(), "currenttime.com");
        Assert.assertEquals(settings.server.get(1).getRoot(), "current");
        Assert.assertEquals(settings.server.get(1).getResource("403"), "403.html");
        Assert.assertEquals(settings.server.get(1).getResource("404"), "404.html");
        Assert.assertEquals(settings.server.get(1).getResource("500"), "500.html");
    }
}