package popo.atlas.framework.base;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import popo.atlas.framework.base.driver.Browser;
import popo.atlas.framework.utils.configurations.StageConfiguration;
import popo.atlas.framework.utils.listener.CustomListener;

@Log4j2
@Listeners({CustomListener.class})
public class BaseEntity {

    protected static final StageConfiguration STAGE_CONFIGURATION = StageConfiguration.getInstance();

    @BeforeMethod()
    public void beforeMethod() {
        Browser.getInstance().openStartPage(STAGE_CONFIGURATION.getStageUrl());
        AtlasHelper.getInstance();
    }

    @AfterMethod
    public void afterMethod() {
        Browser.getInstance().exit();
        AtlasHelper.getInstance().cleanAtlas();
    }

    protected static RemoteWebDriver getWebDriver() {
        return Browser.getDriver();
    }
}
