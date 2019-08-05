package popo.atlas.framework.base.driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import popo.atlas.framework.utils.configurations.BrowserConfiguration;

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
    public static RemoteWebDriver setUp(final Browser.BrowserType type) {
        return getWebDriver(type);
    }

    /**
     * Setting up Driver
     *
     * @param type BrowserOld type
     * @return RemoteWebDriver
     * @throws NamingException NamingException
     */
    public static RemoteWebDriver setUp(final String type) throws NamingException {
        for (Browser.BrowserType browserType : Browser.BrowserType.values()) {
            if (browserType.getValue().equalsIgnoreCase(type)) {
                return setUp(browserType);
            }
        }
        throw new NamingException("Wrong Browser Name: " + Arrays.toString(Browser.BrowserType.values()));
    }

    private static RemoteWebDriver getWebDriver(final Browser.BrowserType type) {
        switch (type) {
            case CHROME:
                ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                return getChromeDriver();
            default:
                return null;
        }
    }

    private static RemoteWebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        if (BrowserConfiguration.getInstance().getResourcePropertiesManager().getBooleanProperties(KEY_BROWSER_HEADLESS)) {
            options.addArguments("headless");
        }
        return new ChromeDriver(options);
    }
}
