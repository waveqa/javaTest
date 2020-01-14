package e2e;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import services.MainPage;
import services.helpers.FakerHelper;

public class LoginTest {

    MainPage mainPage = new MainPage();
    FakerHelper fakerHelper = new FakerHelper();

    @Test
    public void loginWithIncorrectCredentials(){
        System.out.println("Test login started");
        mainPage.login(fakerHelper.getRandomName(), fakerHelper.getRandomPassword(6,8));
        mainPage.getVerifyRecaptchaButton().shouldBe(Condition.visible)
                .pressEscape();
        mainPage.clickHidePostByIndex(0);
    }
}
