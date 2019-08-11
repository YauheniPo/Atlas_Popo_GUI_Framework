package popo.atlas.framework.base;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import popo.atlas.framework.base.driver.Browser;
import popo.atlas.framework.utils.configurations.StageProperties;
import popo.atlas.framework.utils.listener.CustomListener;

@Log4j2
@Listeners({CustomListener.class})
public class BaseEntity {

    @BeforeMethod()
    public void beforeMethod() {
        Browser.getInstance().openStartPage(StageProperties.getInstance().getUrl());
        AtlasHelper.getInstance();
    }

    @AfterMethod
    public void afterMethod() {
        Browser.getInstance().exit();
        AtlasHelper.getInstance().cleanAtlas();
    }

    protected static EventFiringWebDriver getWebDriver() {
        return Browser.getDriver();
    }
}
