package popo.atlas.framework.base;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import popo.atlas.framework.base.driver.Browser;
import popo.atlas.framework.utils.configurations.StageConfiguration;
import popo.atlas.framework.utils.listener.CustomListener;

@Log4j2
@Listeners({CustomListener.class})
@ContextConfiguration(classes = StageConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class BaseEntity extends AbstractTestNGSpringContextTests {

    @Autowired
    private StageConfiguration stageConfiguration;

    @BeforeMethod()
    public void beforeMethod() {
        Browser.getInstance().openStartPage(stageConfiguration.getStageUrl());
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
