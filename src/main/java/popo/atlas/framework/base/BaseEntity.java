package popo.atlas.framework.base;

import com.automation.remarks.testng.UniversalVideoListener;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import popo.atlas.framework.atlas.extention.ContainsClassExtension;
import popo.atlas.framework.base.driver.Browser;
import popo.atlas.framework.utils.configurations.BrowserConfiguration;
import popo.atlas.framework.utils.configurations.StageConfiguration;
import popo.atlas.framework.utils.listener.CustomListener;

@Log4j2
@Listeners({UniversalVideoListener.class, CustomListener.class})
public class BaseEntity {

    protected static final StageConfiguration STAGE_CONFIGURATION = StageConfiguration.getInstance();
    protected static final BrowserConfiguration BROWSER_CONFIGURATION = BrowserConfiguration.getInstance();
    protected ThreadLocal<Atlas> atlasThreadLocal = new ThreadLocal<>();

    @BeforeSuite
    public void beforeSuite() {
        System.getProperties().setProperty("ATLAS_WEBSITE_URL", STAGE_CONFIGURATION.getStageUrl());
    }

    @BeforeMethod()
    public void beforeMethod() {
        Browser browser = Browser.getInstance();
        browser.openStartPage(STAGE_CONFIGURATION.getStageUrl());
        if (this.atlasThreadLocal.get() == null) {
            this.atlasThreadLocal.set(new Atlas(new WebDriverConfiguration(getWebDriver()))
                    .extension(new ContainsClassExtension()));
        }
        log.info(String.format("Screen size is %s", getWebDriver().manage().window().getSize()));
    }

    @AfterMethod
    public void afterMethod() {
        Browser.getInstance().exit();
    }

    protected static RemoteWebDriver getWebDriver() {
        return Browser.getDriver();
    }
}
