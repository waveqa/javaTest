package e2e;

import org.junit.*;
import services.helpers.CommonHelper;
import services.MainPage;

public class TestClass {
    MainPage mainPage = new MainPage();
    CommonHelper commonHelper = new CommonHelper();

//    @Test
//    public void loginWithIncorrectCredentials(){
//        mainPage.login(commonHelper.getRandomName(), commonHelper.getRandomPassword(6,8));
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
//        mainPage.fillUsername(commonHelper.getRandomName());
//        mainPage.fillPhoneNumber(commonHelper.getRandomPhoneNumber());
//        mainPage.fillEmail(commonHelper.getRandomEmail("test123"));
//        mainPage.fillPassword(commonHelper.getRandomPassword(6,8));
//        mainPage.clickCreateAccount();
//    }
}
