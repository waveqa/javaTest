package e2e;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.helpers.FakerHelper;

import java.io.*;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);
    protected FakerHelper fakerHelper = new FakerHelper();
    @Rule
    public AllureRule allureRule = new AllureRule();

    private static String getDataFromProperty(String propName) {
        try (InputStream input = new FileInputStream(".\\src\\test\\resources\\config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            return prop.getProperty(propName);
        } catch (IOException ex) {
            ex.printStackTrace();
            return "1";
        }
    }

    @BeforeClass
    public static void setUp() {
        LOG.info("Before class started");
        System.out.println();
        Configuration.startMaximized = true;
        Configuration.timeout = Integer.parseInt(getDataFromProperty("globalTimeout"));
//        Configuration.headless = true;
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        Configuration.browser = getDataFromProperty("browserName");
        open(getDataFromProperty("baseUrl"));
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
/*
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
    }*/
}