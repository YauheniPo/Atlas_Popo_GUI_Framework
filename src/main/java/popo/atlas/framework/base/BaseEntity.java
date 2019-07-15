package popo.atlas.framework.base;

import com.automation.remarks.video.annotations.Video;
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

@Log4j2
//@Listeners({CustomListener.class, ScreenShooter.class})
public class BaseEntity implements IHookable {

    public static ResourcePropertiesManager testConfig = new ResourcePropertiesManager("config.properties");
    Atlas atlas;

    @Video
    @BeforeMethod()
    public void beforeTest() {
        Browser.getInstance();
        Browser.openStartPage();
        atlas = new Atlas(new WebDriverConfiguration(getWebDriver()))
                .extension(new ContainsClassExtension());
        log.info(String.format("Screen size is %s", getWebDriver().manage().window().getSize()));
    }

    protected static RemoteWebDriver getWebDriver() {
        return Browser.getDriver();
    }

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
    }
}
