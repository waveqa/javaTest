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
    CommonHelper commonHelper = new CommonHelper();

    @Test
    public void loginWithIncorrectCredentials(){
        open("https://pikabu.ru/");
        mainPage.login(commonHelper.getRandomName(), commonHelper.getRandomPassword(6,8));
        mainPage.getVerifyRecaptchaButton().shouldBe(Condition.visible)
                .pressEscape();
        mainPage.clickHidePostByIndex(0);
    }

    @Test
    public void seeNewPosts(){
        mainPage.clickNewPosts();
    }

    @Test
    public void createNewUser(){
        mainPage.clickSignUp();
        mainPage.fillUsername(commonHelper.getRandomName());
        mainPage.fillPhoneNumber(commonHelper.getRandomPhoneNumber());
        mainPage.fillEmail(commonHelper.getRandomEmail("test123"));
        mainPage.fillPassword(commonHelper.getRandomPassword(6,8));
        mainPage.clickCreateAccount();
        sleep(5000);
    }

}
