import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selectors.*;
import com.codeborne.selenide.SelenideElement;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.FindBy;
import javax.security.auth.login.Configuration;

import static com.codeborne.selenide.Selenide.*;

public class JavaExample {

    MainPage mainPage = new MainPage();

    @Test
    public void loginWithIncorrectCredentials(){
        open("https://pikabu.ru/");
        mainPage.login("login", "password");
        mainPage.getVerifyRecaptchaButton().shouldBe(Condition.visible)
                .pressEscape();
        mainPage.clickHidePostByIndex(0);
        sleep(4000);
    }

    @Test
    public void seeNewPosts(){
        mainPage.clickNewPosts();
    }

    @Test
    public void createNewUser(){
        mainPage.clickSignUp();
        mainPage.fillUsername();
        mainPage.fillPhoneNumber();
        mainPage.fillEmail();
        mainPage.fillPassword();
        mainPage.clickCreateAccount();

    }

}
