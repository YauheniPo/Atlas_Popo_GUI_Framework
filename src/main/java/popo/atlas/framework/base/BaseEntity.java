package popo.atlas.framework.base;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import popo.atlas.framework.base.driver.Browser;
import popo.atlas.framework.utils.ResourcePropertiesManager;
import popo.atlas.framework.utils.configurations.StageProperties;
import popo.atlas.framework.utils.listener.CustomListener;

@Log4j2
@Listeners({CustomListener.class})
public class BaseEntity {

    static {
        System.getProperties().setProperty("test.env", new ResourcePropertiesManager("stage.properties").getStringProperty("stage"));
    }

    protected final StageProperties stageProperties = StageProperties.getInstance();

    @BeforeMethod
    public void beforeMethod() {
        Browser.getInstance().openStartPage(stageProperties.getUrl());
        AtlasHelper.getInstance(stageProperties.getUrl());
    }

    @AfterMethod
    public void afterMethod() {
        Browser.getInstance().exit();
        AtlasHelper.cleanAtlas();
    }

    protected static WebDriver getWebDriver() {
        return Browser.getDriver();
    }
}
