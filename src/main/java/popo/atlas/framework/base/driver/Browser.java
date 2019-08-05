package popo.atlas.framework.base.driver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import popo.atlas.framework.base.SmartWait;
import popo.atlas.framework.utils.ResourcePropertiesManager;
import popo.atlas.framework.utils.configurations.BrowserConfiguration;

import javax.naming.NamingException;
import java.util.concurrent.TimeUnit;

@Log4j2
public final class Browser {

    private static final BrowserConfiguration BROWSER_CONFIGURATION = BrowserConfiguration.getInstance();
    private static final BrowserType BROWSER_BY_DEFAULT = BrowserType.CHROME;
    private static final String BROWSER_PROP = "browser";
    private static final ResourcePropertiesManager PROPERTIES_RESOURCE_MANAGER = BROWSER_CONFIGURATION.getResourcePropertiesManager();
    private static final String currentBrowser = System.getProperty(BROWSER_PROP,
            PROPERTIES_RESOURCE_MANAGER.getStringProperty(BROWSER_PROP, BROWSER_BY_DEFAULT.getValue()));
    private static ThreadLocal<RemoteWebDriver> driverHolder = ThreadLocal.withInitial(Browser::getNewDriver);
    private static Browser instance = new Browser();

    private Browser() {
        log.info(String.format("Init Browser '%s'", currentBrowser));
    }

    /**
     * Gets thread safe instance of Browser
     *
     * @return browser instance
     */
    public static Browser getInstance() {
        if (instance == null) {
            synchronized (Browser.class) {
                if (instance == null) {
                    instance = new Browser();
                }
            }
        }
        return instance;
    }

    public static RemoteWebDriver getDriver() {
        if (driverHolder.get() == null) {
            driverHolder.set(getNewDriver());
        }
        return driverHolder.get();
    }

    public void exit() {
        try {
            getDriver().quit();
            log.info("WebDriver quit");
        } catch (Exception e) {
            log.error(this, e);
        } finally {
            if (isBrowserAlive()) {
                driverHolder.set(null);
            }
        }
    }

    private boolean isBrowserAlive() {
        return driverHolder.get() != null;
    }

    public void openStartPage(final String url) {
        navigate(url);
        windowMaximise();
    }

    public void navigate(final String url) {
        getDriver().navigate().to(url);
    }

    public void windowMaximise() {
        getDriver().manage().window().maximize();
    }

    public void waitForPageToLoad() {
        log.debug("Waiting for page to load");
        ExpectedCondition<Boolean> condition = d ->
                (Boolean) executeJSScript("return document['readyState'] ? 'complete' == document.readyState : true");
        SmartWait.waitUntil(condition, BROWSER_CONFIGURATION.getDefaultTimeoutToLoadPages());
    }

    public Object executeJSScript(final String script) {
        return ((JavascriptExecutor) getDriver()).executeScript(script);
    }

    public Object executeJSScript(final String script, final WebElement element) {
        return ((JavascriptExecutor) getDriver()).executeScript(script, element);
    }

    public void back() {
        getDriver().navigate().back();
        log.info("Return to previous page");
    }

    @AllArgsConstructor()
    public enum BrowserType {
        FIREFOX("firefox"),
        CHROME("chrome"),
        EDGE("edge"),
        IE("ie"),
        DEFAULT("default");

        @Getter
        private final String value;
    }

    private static RemoteWebDriver getNewDriver() {
        try {
            RemoteWebDriver driver = BrowserFactory.setUp(currentBrowser);
            driver.manage().timeouts().implicitlyWait(BROWSER_CONFIGURATION.getImplicitlyWait(), TimeUnit.SECONDS);
            log.info("getNewDriver");
            return driver;
        } catch (NamingException e) {
            log.error("getNewDriver", e);
        }
        return null;
    }
}
