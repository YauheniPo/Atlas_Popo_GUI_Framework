package popo.atlas.framework.base;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import popo.atlas.framework.atlas.extention.ContainsClassExtension;
import popo.atlas.framework.base.driver.Browser;
import popo.atlas.framework.utils.configurations.StageConfiguration;
import popo.atlas.framework.utils.listener.CustomListener;

@Log4j2
@Listeners({CustomListener.class})
public class BaseEntity {

    private static final StageConfiguration STAGE_CONFIGURATION = StageConfiguration.getInstance();

    protected Atlas atlas;

    @BeforeMethod()
    public void beforeMethod() {
        Browser browser = Browser.getInstance();
        browser.openStartPage(STAGE_CONFIGURATION.getStageUrl());
        atlas = new Atlas(new WebDriverConfiguration(getWebDriver()))
                .extension(new ContainsClassExtension());
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
