package e2e;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import services.MainPage;

public class LoginTest extends BaseTest {

    MainPage mainPage = new MainPage();
    private String tooltipMessage = "Обязательное поле";

    @Test
    public void loginWithIncorrectCredentials() {
        mainPage.login(fakerHelper.getRandomName(), fakerHelper.getRandomPassword(6,8));
        mainPage.getVerifyRecaptchaButton();
        mainPage.clickHidePostByIndex(0);
    }

    @Test
    public void loginWithoutCredentials() {
        mainPage.getInputTooltip().shouldBe(Condition.disappears);
        mainPage.clickLogin();
        mainPage.getInputTooltip().shouldHave(Condition.text(tooltipMessage));
    }

    @Test
    public void loginWithoutEmail() {
        mainPage.getInputTooltip().waitUntil(Condition.disappears,
                Integer.parseInt(commonHelper.getDataFromProperty("globalTimeout")));
        mainPage.login("", fakerHelper.getRandomPassword(6,8));
        mainPage.getInputError("email").shouldBe(Condition.visible);
        mainPage.getInputTooltip().shouldHave(Condition.text(tooltipMessage));
    }

    @Test
    public void loginWithoutPassword() {
        mainPage.getInputTooltip().waitUntil(Condition.disappears,
                Integer.parseInt(commonHelper.getDataFromProperty("globalTimeout")));
        mainPage.login(fakerHelper.getRandomName(), "");
        mainPage.getInputError("password").shouldBe(Condition.visible);
        mainPage.getInputTooltip().shouldHave(Condition.text(tooltipMessage));
    }
}
