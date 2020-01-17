package e2e;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.helpers.FakerHelper;

import java.io.*;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(AllureRuleTest.class);
    protected FakerHelper fakerHelper = new FakerHelper();

    private static String getDataFromProperty(String propName) {
        try (InputStream input = new FileInputStream(".\\src\\test\\resources\\config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            System.out.println(prop.getProperty(propName));

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
    }
}