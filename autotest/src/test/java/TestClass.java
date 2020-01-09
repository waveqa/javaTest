import com.codeborne.selenide.Condition;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.codeborne.selenide.Selenide.*;

@RunWith(JUnit4.class)
public class TestClass {
    MainPage mainPage = new MainPage();
    CommonHelper commonHelper = new CommonHelper();

/*
    @Test
    public void loginWithIncorrectCredentials(){
        mainPage.login(commonHelper.getRandomName(), commonHelper.getRandomPassword(6,8));
        mainPage.getVerifyRecaptchaButton().shouldBe(Condition.visible)
                .pressEscape();
        mainPage.clickHidePostByIndex(0);
    }*/

    @Test
    public void seeNewPosts(){
        mainPage.clickNewPosts();
        mainPage.getVerifyRecaptchaButton().shouldBe(Condition.visible);
    }

    @Test
    public void seetest(){
        assert 1==1;
    }
/*
    @Test
    public void createNewUser(){
        mainPage.clickSignUp();
        mainPage.fillUsername(commonHelper.getRandomName());
        mainPage.fillPhoneNumber(commonHelper.getRandomPhoneNumber());
        mainPage.fillEmail(commonHelper.getRandomEmail("test123"));
        mainPage.fillPassword(commonHelper.getRandomPassword(6,8));
        mainPage.clickCreateAccount();
        sleep(5000);
    }*/
}
