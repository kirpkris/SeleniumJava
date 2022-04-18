import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Lesson7 {

    private WebDriver driver;

    @Before
    public void setup() {
        // initialize driver
        driver = new ChromeDriver();

        // set implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // wait for element to be present
    @Test
    public void howToWaitForElement() {
        //set explicit wait with timeout in 30 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait.until(d -> d.findElement(By.cssSelector("")));
        WebElement element1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));
    }

    // wait for element to disappear from object model
    @Test
    public void howToWaitForElementToDisappear() {
        driver.get("https://google.com");
        WebElement searchField = driver.findElement(By.cssSelector("input.gLFyf"));
        searchField.sendKeys("test");
        searchField.sendKeys(Keys.ENTER);

        List<WebElement> itemsPage1 = driver.findElements(By.cssSelector(".g.tF2Cxc h3"));
        List<WebElement> pagination = driver.findElements(By.cssSelector(".AaVjTc td > a"));

        pagination.get(0).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.stalenessOf(itemsPage1.get(0)));

        List<WebElement> itemsPage2 = driver.findElements(By.cssSelector(".g.tF2Cxc h3"));
        assert itemsPage2.get(0).getText().equals("Test Çöz - Online Müfredata Uygun Testleri Çöz - Sorubak.Com");
    }

    // wait for element/s to be visible
    @Test
    public void howToWaitForElementToBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // for single element
        WebElement element = driver.findElement(By.cssSelector(""));
        wait.until(ExpectedConditions.visibilityOf(element));

        // find element + wait for visibility
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));

        //for multiple elements
        List<WebElement> elements = driver.findElements(By.cssSelector(""));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));

        // find elements + wait for visibility
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("")));
    }

    // wait for element/s to be invisible
    @Test
    public void howToWaitForElementToBeInvisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // for single element
        WebElement element = driver.findElement(By.cssSelector(""));
        wait.until(ExpectedConditions.invisibilityOf(element));

        //for multiple elements
        List<WebElement> elements = driver.findElements(By.cssSelector(""));
        wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    // stop driver and shut down browser
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
