package popo.atlas.framework.base;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.core.context.RetryerContext;
import io.qameta.atlas.core.internal.DefaultRetryer;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import popo.atlas.framework.atlas.extention.ContainsClassExtension;
import popo.atlas.framework.base.driver.Browser;
import popo.atlas.framework.utils.configurations.StageConfiguration;
import popo.atlas.framework.utils.listener.CustomListener;

import java.util.Arrays;

@Log4j2
@Listeners({CustomListener.class})
public class BaseEntity {

    protected static final StageConfiguration STAGE_CONFIGURATION = StageConfiguration.getInstance();
    protected ThreadLocal<Atlas> atlasThreadLocal = new ThreadLocal<>();

    @BeforeMethod()
    public void beforeMethod() {
        Browser.getInstance().openStartPage(STAGE_CONFIGURATION.getStageUrl());
        if (this.atlasThreadLocal.get() == null) {
            this.atlasThreadLocal.set(new Atlas(new WebDriverConfiguration(getWebDriver(), STAGE_CONFIGURATION.getStageUrl()))
                    .extension(new ContainsClassExtension())
                    .context(new RetryerContext(
                            new DefaultRetryer(
                                    SmartWait.BROWSER_CONFIGURATION.getTimeoutForConditionInMellis(),
                                    SmartWait.BROWSER_CONFIGURATION.getPollingInterval(),
                                    Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class)))));
        }
    }

    @AfterMethod
    public void afterMethod() {
        Browser.getInstance().exit();
        if (this.atlasThreadLocal.get() != null) {
            this.atlasThreadLocal.set(null);
        }
    }

    protected static RemoteWebDriver getWebDriver() {
        return Browser.getDriver();
    }
}
