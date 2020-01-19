package e2e;

import org.junit.Test;
import services.MainPage;

public class LoginTest extends BaseTest {

    MainPage mainPage = new MainPage();

    @Test
    public void loginWithIncorrectCredentials(){
        mainPage.login(fakerHelper.getRandomName(), fakerHelper.getRandomPassword(6,8));
        mainPage.getVerifyRecaptchaButton();
        mainPage.clickHidePostByIndex(0);
    }
}
