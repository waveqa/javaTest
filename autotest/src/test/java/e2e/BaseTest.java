package e2e;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.helpers.CommonHelper;
import services.helpers.FakerHelper;
import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {

    @Rule
    public AllureRule allureRule = new AllureRule();
    protected FakerHelper fakerHelper = new FakerHelper();
    protected CommonHelper commonHelper = new CommonHelper();
    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    public static void setUp() {
        LOG.info("Before class started");
        System.out.println();
        Configuration.startMaximized = true;
        Configuration.timeout = Integer.parseInt(CommonHelper.getDataFromProperty("globalTimeout"));
//        Configuration.headless = true;
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        Configuration.browser = CommonHelper.getDataFromProperty("browserName");
        open(CommonHelper.getDataFromProperty("baseUrl"));
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
}