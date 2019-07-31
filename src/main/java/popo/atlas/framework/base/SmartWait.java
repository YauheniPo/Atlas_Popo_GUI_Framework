package popo.atlas.framework.base;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import popo.atlas.framework.base.driver.Browser;
import popo.atlas.framework.utils.configurations.BrowserConfiguration;

import java.util.concurrent.TimeUnit;

@Log4j2
final public class SmartWait {

    private static final BrowserConfiguration BROWSER_CONFIGURATION = BrowserConfiguration.getInstance();

    private SmartWait() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> T waitFor(ExpectedCondition<T> condition) {
        return waitFor(condition, BROWSER_CONFIGURATION.getTimeoutForCondition());
    }

    public static void waitUntil(ExpectedCondition<Boolean> condition, long timeOutInSeconds) {
        wait(timeOutInSeconds, 500L).until(condition);
    }

    public static void waitUntil(ExpectedCondition<Boolean> condition) {
        waitUntil(condition, BROWSER_CONFIGURATION.getTimeoutForCondition());
    }

    private static Wait<WebDriver> wait(long timeOutInSeconds, long pollingInterval) {
        Browser.getDriver().manage().timeouts().implicitlyWait(0L, TimeUnit.MILLISECONDS);
        return new WebDriverWait(Browser.getDriver(), timeOutInSeconds, pollingInterval)
                .ignoring(StaleElementReferenceException.class, WebDriverException.class);
    }

    private static <T> T waitFor(ExpectedCondition<T> condition, long timeOutInSeconds) {
        try {
            return wait(timeOutInSeconds, BROWSER_CONFIGURATION.getPollingInterval()).until(condition);
        } finally {
            Browser.getDriver().manage().timeouts().implicitlyWait(BROWSER_CONFIGURATION.getImplicitlyWait(), TimeUnit.SECONDS);
        }
    }

    public static boolean waitForTrue(ExpectedCondition<Boolean> condition) {
        return waitForTrue(condition, BROWSER_CONFIGURATION.getTimeoutForCondition());
    }

    public static boolean waitForTrue(ExpectedCondition<Boolean> condition, long timeOutInSeconds) {
        try {
            return waitFor(condition, timeOutInSeconds);
        } catch (Exception e) {
            log.debug("waitForTrue", e);
            return false;
        }
    }

    public static boolean waitForFalse(ExpectedCondition<Boolean> condition, long timeOutInSeconds) {
        try {
            return !waitFor(condition, timeOutInSeconds);
        } catch (Exception e) {
            log.debug("waitForFalse", e);
            return true;
        }
    }
}