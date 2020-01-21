package e2e;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AllureRule implements MethodRule {

    @Override
    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                }
                catch (Throwable throwable){
                    makeScreenshot();
                    throw throwable;
                }
            }
        };
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
