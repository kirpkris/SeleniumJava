package basics;

import com.google.common.io.Files;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.Level;

public class Lesson10 {

    private EventFiringWebDriver driver;
    private WebDriverWait wait;
    public BrowserMobProxy proxy;

    public static class MyListener extends AbstractWebDriverEventListener {

        // log every element search
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        // configure to log all exceptions and make screenshots
        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshot = new File("screen-" + System.currentTimeMillis() + ".png");
            try {
                Files.copy(tmp, screenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(screenshot);
        }

        // log if element was found
        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

    }

    // configure proxy to log request/response to a server
    @Before
    public void setup() {
        // start the proxy
        proxy = new BrowserMobProxyServer();
        proxy.start(0);

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        // add performance logs to logging preferences
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        driver = new EventFiringWebDriver(new ChromeDriver(capabilities));
        driver.register(new MyListener());
        // set implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // set explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void howToCheckAvailableLogTypes() {
        System.out.println(driver.manage().logs().getAvailableLogTypes());
    }

    @Test
    public void howToUsePerformanceLogging() {
        driver.get("https://google.com");
        driver.manage().logs().get("performance").forEach(l -> System.out.println(l));
    }

    @Test
    public void howToUseHttpLogging() {
        proxy.newHar();
        driver.get("https://google.com");
        Har har = proxy.endHar();
        har.getLog().getEntries().forEach(l -> System.out.println(l.getRequest().getMethod() +
                " " + l.getRequest().getUrl() + " - " + l.getResponse().getStatus()));
    }

    // stop driver and shut down browser
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
