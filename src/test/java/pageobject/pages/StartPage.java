package pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.Page;

public class StartPage extends Page {

    public StartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".dropdown.header__nav-item")
    public WebElement navigationMenu;

    @FindBy(css = ".dropdown [href $= account]")
    public WebElement myAccountLink;

    @FindBy(id = "user[email]")
    public WebElement emailField;

    public boolean checkUserRegistration(String email) {
        navigationMenu.click();
        myAccountLink.click();
        return emailField.getAttribute("value").equals(email);
    }

}
