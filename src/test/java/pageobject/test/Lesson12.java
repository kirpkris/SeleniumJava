package pageobject.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import pageobject.TestBase;
import pageobject.TestUser;

import java.time.Duration;

public class Lesson12 extends TestBase {

    @Test
    public void userRegistration() {
        TestUser testUser = new TestUser()
                .setFirstName("TestUser" + RandomStringUtils.randomAlphabetic(5))
                .setLastName("TestUser" + RandomStringUtils.randomAlphabetic(5))
                .setEmail(RandomStringUtils.randomAlphabetic(5) + "@test.com")
                .setPassword(RandomStringUtils.randomAlphabetic(5));

        app.fillOutFields(testUser);
        app.submitTheForm();

        assert app.isUserRegistered(testUser);
    }

}
