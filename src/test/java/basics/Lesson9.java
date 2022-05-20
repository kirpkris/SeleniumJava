package basics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Lesson9 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        // initialize driver
        driver = new ChromeDriver();

        // set implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // set explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void howToHandleAlerts() throws InterruptedException {
        driver.get("https://omayo.blogspot.com/");
        WebElement openAlertButton = driver.findElement(By.id("alert1"));
        openAlertButton.click();

        Alert alert = driver.switchTo().alert();
        /*
        * (alternative) wait until alert is present
        * Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        * */
        alert.dismiss();
        Thread.sleep(5000);
    }

    @Test
    public void howToOpenAndSwitchWindows() {
        driver.get("https://omayo.blogspot.com/");
        String initialWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.WINDOW);

        assert !driver.getWindowHandle().equals(initialWindow);

        driver.switchTo().window(initialWindow);

        assert driver.getWindowHandle().equals(initialWindow);
    }

    @Test
    public void howToCloseWindows(){
        driver.get("https://omayo.blogspot.com/");
        String initialWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.WINDOW);

        assert !driver.getWindowHandle().equals(initialWindow);

        driver.close();

        driver.switchTo().window(initialWindow);

        driver.getWindowHandle();
    }

    @Test
    public void howToHandleIFrame(){
        driver.get("https://omayo.blogspot.com/");

        assert driver.findElements(By.id("alert1")).size() == 1;

        WebElement iFrame = driver.findElement(By.id("iframe1"));
        driver.switchTo().frame(iFrame);

        assert driver.findElements(By.id("alert1")).size() == 0;

        driver.switchTo().defaultContent();

        assert driver.findElements(By.id("alert1")).size() == 1;
    }

    @Test
    public void howToUseQuitAndClose() {
        driver.get("https://omayo.blogspot.com/");
        driver.switchTo().newWindow(WindowType.WINDOW);

        driver.quit();
    }

    // stop driver and shut down browser
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
