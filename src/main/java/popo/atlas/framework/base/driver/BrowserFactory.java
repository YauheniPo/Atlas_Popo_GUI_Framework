package popo.atlas.framework.base.driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import popo.atlas.framework.utils.configurations.BrowserConfiguration;
import popo.atlas.framework.utils.listener.EventHandler;

import javax.naming.NamingException;
import java.util.Arrays;

/**
 * The class-initializer-based browser string parameter.
 */
@Log4j2
final public class BrowserFactory {

    private final static String KEY_BROWSER_HEADLESS = "browser.headless";

    private BrowserFactory() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Setting up Driver
     *
     * @param type BrowserOld type
     * @return RemoteWebDriver
     */
    public static EventFiringWebDriver setUp(final Browser.BrowserType type) {
        return getWebDriver(type);
    }

    /**
     * Setting up Driver
     *
     * @param type BrowserOld type
     * @return RemoteWebDriver
     * @throws NamingException NamingException
     */
    public static EventFiringWebDriver setUp(final String type) throws NamingException {
        for (Browser.BrowserType browserType : Browser.BrowserType.values()) {
            if (browserType.getValue().equalsIgnoreCase(type)) {
                return setUp(browserType);
            }
        }
        throw new NamingException("Wrong Browser Name: " + Arrays.toString(Browser.BrowserType.values()));
    }

    private static EventFiringWebDriver getWebDriver(final Browser.BrowserType type) {
        log.info(String.format("WebDriver %s initialization", type));
        WebDriver webDriver;
        switch (type) {
            case FIREFOX:
                FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                webDriver = new FirefoxDriver();
                break;
            default:
                log.warn("Default WebDriver");
            case CHROME:
                ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                webDriver = getChromeDriver();
        }
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(webDriver);
        EventHandler handler = new EventHandler();
        eventDriver.register(handler);
        return eventDriver;
    }

    private static RemoteWebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        if (BrowserConfiguration.getInstance().getResourcePropertiesManager().getBooleanProperties(KEY_BROWSER_HEADLESS)) {
            options.addArguments("headless");
        }
        return new ChromeDriver(options);
    }
}
