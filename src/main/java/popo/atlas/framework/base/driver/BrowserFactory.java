package popo.atlas.framework.base.driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import popo.atlas.framework.utils.configurations.BrowserConfiguration;

import javax.naming.NamingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

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
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable("browser", Level.ALL);
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", System.getProperty("user.dir"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        if (BrowserConfiguration.getRESOURCE_PROPERTIES_MANAGER().getBooleanProperties(KEY_BROWSER_HEADLESS)) {
            options.addArguments("headless");
        }
        options.addArguments("window-size=1920x1080");
        DesiredCapabilities caps1 = DesiredCapabilities.chrome();
        caps1.setCapability(ChromeOptions.CAPABILITY, options);
        caps1.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        caps1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        caps1.setCapability("chrome.switches", Collections.singletonList("--disable-popup-blocking"));
        options.merge(caps1);
        return new ChromeDriver(options);
    }
}
