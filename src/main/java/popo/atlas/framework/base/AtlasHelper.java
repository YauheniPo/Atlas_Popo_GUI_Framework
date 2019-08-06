package popo.atlas.framework.base;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.core.context.RetryerContext;
import io.qameta.atlas.core.internal.DefaultRetryer;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import popo.atlas.framework.atlas.extention.ContainsClassExtension;
import popo.atlas.framework.base.driver.Browser;
import popo.atlas.framework.utils.configurations.StageConfiguration;

import java.util.Arrays;

@Log4j2
public class AtlasHelper {

    private static ThreadLocal<Atlas> atlasThreadLocal = ThreadLocal.withInitial(AtlasHelper::initAtlas);
    private static AtlasHelper instance = new AtlasHelper();

    private AtlasHelper() {
        log.info("Init AtlasHelper");
    }

    public static AtlasHelper getInstance() {
        if (instance == null) {
            synchronized (AtlasHelper.class) {
                if (instance == null) {
                    instance = new AtlasHelper();
                }
            }
        }
        return instance;
    }

    private static Atlas initAtlas() {
        return new Atlas(new WebDriverConfiguration(Browser.getDriver(), StageConfiguration.getInstance().getStageUrl()))
                .extension(new ContainsClassExtension())
                .context(new RetryerContext(
                        new DefaultRetryer(
                                SmartWait.BROWSER_CONFIGURATION.getTimeoutForConditionInMellis(),
                                SmartWait.BROWSER_CONFIGURATION.getPollingInterval(),
                                Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class))));
    }

    public static Atlas getAtlas() {
        if (atlasThreadLocal.get() == null) {
            atlasThreadLocal.set(initAtlas());
        }
        return atlasThreadLocal.get();
    }

    public void cleanAtlas() {
        if (atlasThreadLocal.get() != null) {
            atlasThreadLocal.set(null);
        }
    }
}
