package e2e;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import services.MainPage;
import services.helpers.FakerHelper;

public class LoginTest extends BaseTest {

    MainPage mainPage = new MainPage();

    @Test
    public void loginWithIncorrectCredentials(){
        System.out.println("Test login started");
        mainPage.login(fakerHelper.getRandomName(), fakerHelper.getRandomPassword(6,8));
        mainPage.getVerifyRecaptchaButton().shouldBe(Condition.visible)
                .pressEscape();
        mainPage.clickHidePostByIndex(0);
    }
}
