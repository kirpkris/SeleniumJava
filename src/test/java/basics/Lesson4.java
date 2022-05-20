package basics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Lesson4 {

    private WebDriver driver;

    @Before
    public void setup() {
        // initialize driver
        driver = new ChromeDriver();

        // set implicit wait of 5 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    /*
    * Types of possible exceptions during element search:
    *
    * NoSuchElementException - no element is found in DOM/ element is located in an iframe or different window
    * StaleElementReferenceException - elements' unique identifiers are no longer valid due to page reload
    * InvalidSelectorException - provided selector has syntactic errors
    *
    * */

    // findElement() returns WebElement object or NoSuchElementException if nothing is found
    @Test
    public void howToFindSingleElement() {
        driver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        WebElement element = driver.findElement(By.cssSelector(".registration_form h2"));
    }

    // findElements() returns list of elements or an empty list if nothing is found
    @Test
    public void howToFindMultipleElements() {
        driver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        WebElement element = driver.findElement(By.cssSelector(".registration_form div"));
    }

    // stop driver and shut down browser
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
