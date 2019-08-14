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

import java.util.Arrays;

@Log4j2
public class AtlasHelper {

    private static ThreadLocal<Atlas> atlasThreadLocal = new ThreadLocal<>();
    private static AtlasHelper instance = null;

    private AtlasHelper(String baseUrl) {
        log.info("Init AtlasHelper");
        System.getProperties().setProperty("ATLAS_WEBSITE_URL", baseUrl);
    }

    static AtlasHelper getInstance(String baseUrl) {
        if (instance == null) {
            synchronized (AtlasHelper.class) {
                if (instance == null) {
                    instance = new AtlasHelper(baseUrl);
                }
            }
        }
        return instance;
    }

    private static Atlas initAtlas() {
        return new Atlas(new WebDriverConfiguration(Browser.getDriver()))
                .extension(new ContainsClassExtension())
                .context(new RetryerContext(
                        new DefaultRetryer(
                                (long) SmartWait.BROWSER_PROPERTIES.getDefaultConditionTimeoutMillis(),
                                SmartWait.BROWSER_PROPERTIES.getDefaultPollingInterval(),
                                Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class))));
    }

    public static Atlas getAtlas() {
        if (atlasThreadLocal.get() == null) {
            atlasThreadLocal.set(initAtlas());
        }
        return atlasThreadLocal.get();
    }

    static void cleanAtlas() {
        if (atlasThreadLocal.get() != null) {
            atlasThreadLocal.set(null);
        }
    }
}
