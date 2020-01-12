import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import e2e.TestClass;
import io.qameta.allure.Attachment;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runners.Suite.SuiteClasses;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

@RunWith(Suite.class)
@SuiteClasses({TestClass.class})
public class TestConfig {
    private static final Logger LOG = LoggerFactory.getLogger(TestConfig.class);
    private static ChromeDriverService service;
    private WebDriver driver;
// todo 1) structure of the project, file with data for precondition
    @BeforeClass
    public static void setUp(){
        LOG.info("Before class started");
//        service = new ChromeDriverService.Builder()
//                .usingDriverExecutable(new File("./chromedriver.exe"))
//                .usingAnyFreePort()
//                .build();
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
//        System.setProperty("chromeoptions.args", "--start-maximized");

        Configuration.browser = "chrome";
        open("https://pikabu.ru/");
    }

    @AfterClass
    public static void afterExec(){
        LOG.info("After class started");
//        service.stop();
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
    public void printInConsole(){
        LOG.info("Rule is executed in config");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            LOG.info("failed");
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
