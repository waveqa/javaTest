import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runners.Suite.SuiteClasses;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

@RunWith(Suite.class)
@SuiteClasses({TestClass.class})
public class TestConfig {
    private static final Logger LOG = LoggerFactory.getLogger(TestConfig.class);

    @BeforeClass
    public static void init(){
        LOG.info("Before class started");
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        open("https://pikabu.ru/");
    }

    @After
    public void afterExec(){
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
}
