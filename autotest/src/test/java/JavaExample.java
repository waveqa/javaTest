import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selectors.*;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.support.FindBy;
import javax.security.auth.login.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeSuite;


import static com.codeborne.selenide.Selenide.*;

public class JavaExample {

    MainPage mainPage = new MainPage();
    CommonHelper commonHelper = new CommonHelper();

    @BeforeClass
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }
    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        open("https://pikabu.ru/");
    }

    @Test
    public void loginWithIncorrectCredentials(){
        mainPage.login(commonHelper.getRandomName(), commonHelper.getRandomPassword(6,8));
        mainPage.getVerifyRecaptchaButton().shouldBe(Condition.visible)
                .pressEscape();
        mainPage.clickHidePostByIndex(0);
    }

    @Test
    public void seeNewPosts(){
        mainPage.clickNewPosts();
        mainPage.getVerifyRecaptchaButton().shouldBe(Condition.visible);// fail
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
