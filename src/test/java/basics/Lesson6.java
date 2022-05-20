package basics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Lesson6 {

    private WebDriver driver;

    @Before
    public void setup() {
        // initialize driver
        driver = new ChromeDriver();

        // set implicit wait of 5 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void howToTypeText() {
        driver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        String text = "some test text";
        String newText = "NEW TEXT!";
        WebElement emailField = driver.findElement(By.cssSelector("#register_form input[name = \"name\"]"));
        emailField.sendKeys(text);
        emailField.sendKeys(newText);
        assert emailField.getAttribute("value").equals(text + newText);
    }

    @Test
    public void howToPressKey() {
        driver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        WebElement emailField = driver.findElement(By.cssSelector("#register_form input[name = \"name\"]"));
        emailField.click();
        emailField.sendKeys(Keys.TAB);
        assert emailField.isDisplayed();
    }

    @Test
    public void howToUploadFile() {
        driver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        WebElement uploadField = driver.findElement(By.cssSelector("[type = file]"));
        uploadField.sendKeys("C:/GIT/DIH/SeleniumJava/src/test/testfiles/test_image.png");
        assert uploadField.isDisplayed();
    }

    @Test
    public void howToRemoveTextWithClear() {
        driver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        String text = "some test text";
        WebElement emailField = driver.findElement(By.cssSelector("#register_form input[name = \"name\"]"));
        emailField.sendKeys(text);
        assert emailField.getAttribute("value").equals(text);
        emailField.clear();
        assert emailField.getAttribute("value").equals("");
    }

    @Test
    public void howToRemoveTextWithSendKeys() {
        driver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        String text = "some test text";
        WebElement emailField = driver.findElement(By.cssSelector("#register_form input[name = \"name\"]"));
        emailField.sendKeys(text);
        assert emailField.getAttribute("value").equals(text);
        emailField.sendKeys(Keys.CONTROL, Keys.chord("A"));
        emailField.sendKeys(Keys.DELETE);
        assert emailField.getAttribute("value").equals("");
    }

    @Test
    public void howToUseAdvancedActions() {
        driver.get("https://demo.guru99.com/test/drag_drop.html");
        WebElement draggable = driver.findElement(By.cssSelector(".ui-draggable:nth-child(5)"));
        WebElement dropArea = driver.findElement(By.cssSelector("#bank > li"));

/*      alternative drag and drop
        new Actions(driver)
                .moveToElement(draggable)
                .clickAndHold()
                .moveToElement(dropArea)
                .release()
                .perform();*/

        new Actions(driver)
                .dragAndDrop(draggable, dropArea)
                .perform();

        assert draggable.isDisplayed();
    }

    // stop driver and shut down browser
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
