package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.pages.RegistrationPage;
import pageobject.pages.StartPage;

import java.time.Duration;

public class Application {

    private WebDriver driver;
    private WebDriverWait wait;

    private RegistrationPage registrationPage;
    private StartPage startPage;

    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        registrationPage = new RegistrationPage(driver);
        startPage = new StartPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void fillOutFields(TestUser testUser) {
        registrationPage
                .open()
                .fillOutFirstNameField(testUser.getFirstName())
                .fillOutLastNameField(testUser.getLastName())
                .fillOutEmailField(testUser.getEmail())
                .fillOutPasswordField(testUser.getPassword())
                .acceptTerms();
    }

    public void submitTheForm() {
        registrationPage.clickSubmitButton();
    }

    public boolean isUserRegistered(TestUser testUser) {
        return startPage.checkUserRegistration(testUser.getEmail());
    }

}
