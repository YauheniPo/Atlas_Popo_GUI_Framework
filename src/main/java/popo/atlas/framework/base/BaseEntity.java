package popo.atlas.framework.base;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.GlobalTextReport;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import popo.atlas.framework.atlas.extention.ContainsClassExtension;
import popo.atlas.framework.base.driver.Browser;

@Log4j2
@Listeners({BrowserPerTest.class, ScreenShooter.class, GlobalTextReport.class})
public class BaseEntity {

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
}
