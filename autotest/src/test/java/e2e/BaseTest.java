package e2e;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.helpers.FakerHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(AllureRuleTest.class);
    protected FakerHelper fakerHelper = new FakerHelper();


    public static String getProperty(String propName) {
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String appConfigPath = rootPath + "config.properties";
            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));
            String property = appProps.getProperty(propName);//"globalTimeout"
            System.out.println(property);
            return property;
        }
        catch (Exception e){
            e.printStackTrace();
            return "1";
        }

    }

    @BeforeClass
    public static void setUp() {
        LOG.info("Before class started");
        System.out.println(getProperty("globalTimeout"));
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
//        Configuration.headless = true;
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        Configuration.browser = "chrome";
        open("https://pikabu.ru/");
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