package basics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Lesson3 {

    private WebDriver driver;

    @Before
    public void setup() {
        // initialize driver
        driver = new ChromeDriver();

        // set implicit wait of 5 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    /*
    * Supported search strategies:
    *
    * By.id
    * By.tag
    * By.className
    * By.cssSelector
    * By.name
    * By.linkText
    * By.partialLinkText
    * By.xpath
    *
    * */

    @Test
    public void howToApplySearchStrategies() {
        WebElement form1 = driver.findElement(By.id("login-form"));
        WebElement form2 = driver.findElement(By.tagName("form"));
        WebElement form3 = driver.findElement(By.className("login"));
        WebElement form4 = driver.findElement(By.cssSelector("form.login"));
        WebElement form5 = driver.findElement(By.cssSelector("#login-form"));

        WebElement field1 = driver.findElement(By.name("username"));
        WebElement field2 = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement link = driver.findElement(By.linkText("Logout"));
        List<WebElement> links = driver.findElements(By.tagName("a"));
    }


    // stop driver and shut down browser
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
