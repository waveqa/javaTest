package e2e;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import services.MainPage;
import services.helpers.CommonHelper;

public class TestLogin {

    MainPage mainPage = new MainPage();
    CommonHelper commonHelper = new CommonHelper();

    @Test
    public void loginWithIncorrectCredentials(){
        System.out.println("Test login started");
        mainPage.login(commonHelper.getRandomName(), commonHelper.getRandomPassword(6,8));
        mainPage.getVerifyRecaptchaButton().shouldBe(Condition.visible)
                .pressEscape();
        mainPage.clickHidePostByIndex(0);
    }
}
