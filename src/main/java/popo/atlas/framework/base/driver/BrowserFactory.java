package popo.atlas.framework.base.driver;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import popo.atlas.framework.utils.configurations.BrowserProperties;
import popo.atlas.framework.utils.listener.EventHandler;

import javax.naming.NamingException;
import java.util.EnumMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The class-initializer-based browser string parameter.
 */
@Log4j2
final public class BrowserFactory {

    public enum BrowserType {

        CHROME {
            @Override
            public WebDriver getWebDriver() {
                ChromeOptions options = new ChromeOptions();
                if (BROWSER_PROPERTIES.isHeadless()) {
                    options.addArguments("headless");
                }
                return new ChromeDriver(options);
            }
        },
        FIREFOX {
            @Override
            public WebDriver getWebDriver() {
                return new FirefoxDriver();
            }
        },
        DEFAULT {
            @Override
            public WebDriver getWebDriver() {
                return CHROME.getWebDriver();
            }
        };

        public abstract WebDriver getWebDriver();
    }

    private static final BrowserProperties BROWSER_PROPERTIES = BrowserProperties.getInstance();
    private static final EnumMap<BrowserType, DriverManagerType> driverManagerMap = new EnumMap<BrowserType, DriverManagerType>(BrowserType.class) {{
        put(BrowserType.CHROME, DriverManagerType.CHROME);
        put(BrowserType.FIREFOX, DriverManagerType.FIREFOX);
        put(BrowserType.DEFAULT, DriverManagerType.CHROME);
    }};

    private BrowserFactory() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Setting up Driver
     *
     * @param type BrowserOld type
     * @return RemoteWebDriver
     * @throws NamingException NamingException
     */
    public static EventFiringWebDriver setUp(final String type) throws NamingException {
        Set<String> driverNames = driverManagerMap.keySet().stream().map(Enum::name).collect(Collectors.toSet());
        if (driverNames.contains(type)) {
            BrowserType browserType = BrowserType.valueOf(type);
            WebDriverManager.getInstance(driverManagerMap.get(browserType)).setup();
            EventFiringWebDriver eventDriver = new EventFiringWebDriver(browserType.getWebDriver());
            EventHandler handler = new EventHandler();
            eventDriver.register(handler);
            return eventDriver;
        }
        throw new NamingException(String.format("Wrong Browser Name: %s", type));
    }
}
