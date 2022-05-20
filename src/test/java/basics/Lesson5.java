package basics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Lesson5 {

    private WebDriver driver;

    @Before
    public void setup() {
        // initialize driver
        driver = new ChromeDriver();

        // set implicit wait of 5 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    // open page > find search field > search for "test" keyword > press Enter > check that there are search results on the page
    public void howToWaitForElement() {
        driver.get("https://google.com");
        WebElement searchField = driver.findElement(By.cssSelector("input.gLFyf"));
        searchField.sendKeys("test");
        searchField.sendKeys(Keys.ENTER);
        assert driver.findElements(By.cssSelector("#search .g.tF2Cxc")).size() > 0;
    }

    @Test
    // open page > find search field > search for "test" keyword > press Enter > check search query in search field
    public void howToGetAttributes() {
        driver.get("https://google.com");
        WebElement searchField = driver.findElement(By.cssSelector("input.gLFyf"));
        searchField.sendKeys("test");
        searchField.sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("input.gLFyf")).getAttribute("value");
    }

    @Test
    // open page > find first dropdown > get "selected" property value
    public void howToGetBooleanAttributes() {
        driver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        driver.findElement(By.cssSelector(".time_feild option")).getAttribute("selected");
    }

    @Test
    // open page > check the logo is displayed
    public void howToCheckVisibility() {
        driver.get("https://google.com");
        assert driver.findElement(By.cssSelector("img.lnXdpd")).isDisplayed();
    }

    @Test
    // open page > get the Registration form header text
    public void howToGetText() {
        driver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        driver.findElement(By.cssSelector(".registration_form h2")).getText();
    }

    @Test
    // open page > get logo size > get logo location > get logo size + location
    public void howToGetSize() {
        driver.get("https://google.com");
        // get element's size
        driver.findElement(By.cssSelector("img.lnXdpd")).getSize();

        // get location
        driver.findElement(By.cssSelector("img.lnXdpd")).getLocation();

        //get size + location
        driver.findElement(By.cssSelector("img.lnXdpd")).getRect();
    }

    // stop driver and shut down browser
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
