package popo.atlas.framework.utils.configurations;

import lombok.Getter;
import popo.atlas.framework.base.driver.BrowserFactory;
import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

import java.util.concurrent.TimeUnit;

@Resource.Classpath("browser.properties")
public class BrowserProperties {

    @Getter
    @Property("browser")
    private String browser = BrowserFactory.BrowserType.CHROME.name();

    @Getter
    @Property("gridServerUrl")
    private String gridServerUrl = "localhost:4444";

    @Getter
    @Property("gridHub")
    private String gridHub = String.format("http://%s/wd/hub", gridServerUrl);

    @Getter
    @Property("browser.headless")
    private boolean isHeadless = false;

    @Getter
    @Property("browser.isRemoteDriver")
    private boolean isRemoteDriver = false;

    @Getter
    @Property("browser.defaultConditionTimeout")
    private int defaultConditionTimeoutMillis = 30000;

    @Getter
    @Property("browser.defaultImplicitlyWait")
    private long defaultImplicitlyWait = 4;

    @Getter
    @Property("browser.defaultPageLoadTimeout")
    private long defaultPageLoadTimeout = 30;

    @Getter
    @Property("browser.defaultPollingInterval")
    private long defaultPollingInterval = 150;

    private static BrowserProperties configuration = new BrowserProperties();

    private BrowserProperties() {
        PropertyLoader.populate(this);
    }

    public static BrowserProperties getInstance() {
        if (configuration == null) {
            synchronized (BrowserProperties.class) {
                if (configuration == null) {
                    configuration = new BrowserProperties();
                }
            }
        }
        return configuration;
    }

    public int getDefaultConditionTimeoutSeconds() {
        return (int) TimeUnit.MILLISECONDS.toSeconds(defaultConditionTimeoutMillis);
    }
}