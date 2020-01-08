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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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

        ProcessBuilder processBuilder = new ProcessBuilder();
        if (isWindows) {
            processBuilder.command("cmd.exe", "/c", "mvn allure:report");
        } else {
            processBuilder.command("mvn allure:report");
        }

        try{
            Process process = processBuilder.start();
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
//                System.exit(0);
            } else {
                //abnormal...
            }
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
