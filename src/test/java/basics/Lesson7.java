package basics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Lesson7 {

    private static ThreadLocal<WebDriver> tl = new ThreadLocal<>();
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        if (tl.get() != null) {
            driver = tl.get();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        // initialize driver
        driver = new ChromeDriver();
        tl.set(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // set implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // wait for element to be present
    @Test
    public void howToWaitForElementDisappearance() {
        driver.navigate().to("https://google.com");

        WebElement searchField = driver.findElement(By.cssSelector("input.gLFyf"));
        searchField.sendKeys("test");
        searchField.sendKeys(Keys.ENTER);

        List<WebElement> itemsPage1 = driver.findElements(By.cssSelector(".g.tF2Cxc h3"));
        List<WebElement> pagination = driver.findElements(By.cssSelector(".AaVjTc td > a"));
        pagination.get(0).click();

        // explicitly wait for element to disappear
        wait.until(ExpectedConditions.stalenessOf(itemsPage1.get(0)));

        List<WebElement> itemsPage2 = driver.findElements(By.cssSelector(".g.tF2Cxc h3"));
        assert itemsPage2.get(0).getText().equals("Test Çöz - Online Müfredata Uygun Testleri Çöz - Sorubak.Com");
    }

    @Test
    public void test2() {
        driver.navigate().to("https://google.com");

        WebElement searchField = driver.findElement(By.cssSelector("input.gLFyf"));
        searchField.sendKeys("test");
        searchField.sendKeys(Keys.ENTER);

        List<WebElement> itemsPage1 = driver.findElements(By.cssSelector(".g.tF2Cxc h3"));
        List<WebElement> pagination = driver.findElements(By.cssSelector(".AaVjTc td > a"));
        pagination.get(0).click();

        /*
        * explicitly wait for element to be visible
        * Other methods to be used:
        * wait.until(ExpectedConditions.visibilityOfAllElements(elementList)) - wait for multiple elements' visibility
        * wait.until(ExpectedConditions.invisibilityOfAllElements(elementList)) - wait for multiple elements' invisibility
        * wait.until(ExpectedConditions.visibilityOfElementLocatedBy(elementList)) - wait for element by locator
        * */
        wait.until(ExpectedConditions.visibilityOf(itemsPage1.get(1)));

        List<WebElement> itemsPage2 = driver.findElements(By.cssSelector(".g.tF2Cxc h3"));
        assert itemsPage2.get(0).getText().equals("Test Çöz - Online Müfredata Uygun Testleri Çöz - Sorubak.Com");
    }

    // stop driver and shut down browser
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
