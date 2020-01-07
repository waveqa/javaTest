import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;


public class TestClass {
    private static final Logger LOG = LoggerFactory.getLogger(TestClass.class);
    MainPage mainPage = new MainPage();
    CommonHelper commonHelper = new CommonHelper();

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        open("https://pikabu.ru/");
    }

    @AfterClass
    public static void afterExec(){
        LOG.info("After class started");


            boolean isWindows = System.getProperty("os.name")
                    .toLowerCase().startsWith("windows");

        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows) {
            builder.command("cmd.exe", "mvn allure:report");
        } else {
            builder.command("mvn allure:report");
        }
        builder.directory(new File(System.getProperty("user.home")));
        Process process = builder.start();

        //mvn allure:report
        LOG.info("After class finished");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }

        @Attachment(value = "Page screenshot", type = "image/png")
        public byte[] saveScreenshot(byte[] screenShot) {
            return screenShot;
        }

        public void screenshot() {
            if (WebDriverRunner.getWebDriver() == null) {
                LOG.info("Driver for screenshot not found");
                return;
            }
            saveScreenshot(((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES));
        }
    };
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

   /* @Test
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
