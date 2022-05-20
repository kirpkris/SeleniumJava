package pageobject;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestBase {

    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @Before
    public void setup() {
        if (tlApp.get() != null) {
            app = tlApp.get();
        }

        app = new Application();
        tlApp.set(app);
    }

    // stop driver and shut down browser
    @After
    public void stop() {
        Runtime.getRuntime().addShutdownHook( new Thread(() -> { app.quit(); app = null; } ));
    }

}
