package popo.atlas.framework.base;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.GlobalTextReport;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.*;
import popo.atlas.framework.atlas.extention.ContainsClassExtension;
import popo.atlas.framework.base.driver.Browser;
import popo.atlas.framework.util.ResourcePropertiesManager;
import popo.atlas.framework.util.listener.CustomListener;
import popo.atlas.framework.util.listener.FailureRetryListener;

@Log4j2
@Listeners({BrowserPerTest.class, ScreenShooter.class, GlobalTextReport.class, CustomListener.class, FailureRetryListener.class})
public class BaseEntity implements IHookable {

    public static ResourcePropertiesManager testConfig = new ResourcePropertiesManager("config.properties");
    Atlas atlas;

    @BeforeMethod()
    public void beforeTest() {
        Browser.getInstance();
        Browser.openStartPage();
        atlas = new Atlas(new WebDriverConfiguration(getWebDriver()))
                .extension(new ContainsClassExtension());
    }

    protected static RemoteWebDriver getWebDriver() {
        return Browser.getDriver();
    }

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        try {
            callBack.runTestMethod(testResult);
        } catch (Throwable throwable) {
            log.fatal(throwable);
            throwable.printStackTrace();
        }
    }
}
