package e2e;

import com.codeborne.selenide.Condition;
import org.junit.*;
import services.MainPage;

public class ClassTest extends BaseTest {

    MainPage mainPage = new MainPage();

//    @Test
//    public void loginWithIncorrectCredentials(){
//        mainPage.login(fakerHelper.getRandomName(), fakerHelper.getRandomPassword(6,8));
//        mainPage.getVerifyRecaptchaButton().shouldBe(Condition.visible)
//                .pressEscape();
//        mainPage.clickHidePostByIndex(0);
//    }

    @Test
    public void seeNewPosts(){
        mainPage.clickNewPosts();
        assert 1==2;
//        mainPage.getVerifyRecaptchaButton().shouldBe(Condition.visible);
    }

//    @Test
//    public void createNewUser(){
//        mainPage.clickSignUp();
//        mainPage.fillUsername(fakerHelper.getRandomName());
//        mainPage.fillPhoneNumber(fakerHelper.getRandomPhoneNumber());
//        mainPage.fillEmail(fakerHelper.getRandomEmail("test123"));
//        mainPage.fillPassword(fakerHelper.getRandomPassword(6,8));
//        mainPage.clickCreateAccount();
//    }
}