package pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends Page {

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user[first_name]")
    public WebElement firstNameField;

    @FindBy(id = "user[last_name]")
    public WebElement lastNameField;

    @FindBy(id = "user[email]")
    public WebElement emailField;

    @FindBy(id = "user[password]")
    public WebElement passwordField;

    @FindBy(id = "user[terms]")
    public WebElement termsCheckbox;

    @FindBy(css = "[type=submit]")
    public WebElement submitButton;

    public RegistrationPage open() {
        driver.get("https://courses.ultimateqa.com/users/sign_up");
        return this;
    }

    public RegistrationPage fillOutFirstNameField(String firstName) {
        firstNameField.sendKeys(firstName);
        return this;
    }

    public RegistrationPage fillOutLastNameField(String lastName) {
        lastNameField.sendKeys(lastName);
        return this;
    }

    public RegistrationPage fillOutEmailField(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public RegistrationPage fillOutPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public RegistrationPage acceptTerms() {
        termsCheckbox.click();
        return this;
    }

    public StartPage clickSubmitButton() {
        submitButton.click();
        return new StartPage(driver);
    }

}
