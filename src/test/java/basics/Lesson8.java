package basics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Lesson8 {

    private static ThreadLocal<WebDriver> tl = new ThreadLocal<>();
    private WebDriver driver;

    @Before
    public void setup() {
        // initialize driver
        if (tl.get() != null) {
            driver = tl.get();
            return;
        }
        // set browser capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("pageLoadStrategy", "eager");
        driver = new ChromeDriver(capabilities);

        /*
        * set command line option
        * ChromeOptions options = new ChromeOptions();
        * options.addArguments("start-maximized");
        * driver = new ChromeDriver(options);
        * */

        tl.set(driver);
    }

    @Test
    public void howToGetBrowserCapabilities() {
        HasCapabilities browserCapabilities = (HasCapabilities) driver;
        System.out.print(browserCapabilities.getCapabilities());
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
