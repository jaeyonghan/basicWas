package was.servlet;

import was.servlet.impl.currenttime.CurrentTime;
import org.junit.Assert;
import org.junit.Test;

public class CurrentTimeTest {
    @Test
    public void test(){
        SimpleServlet currentTime = new CurrentTime();
        Assert.assertEquals(currentTime.getClass().getSimpleName(), "CurrentTime");
    }
}