package popo.atlas.framework.utils.configurations;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import popo.atlas.framework.utils.ResourcePropertiesManager;

import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrowserConfiguration {

    private static final String PROPERTIES_FILE = "browser.properties";
    private static final String DEFAULT_CONDITION_TIMEOUT = "defaultConditionTimeout";
    private static final String DEFAULT_PAGE_LOAD_TIMEOUT = "defaultPageLoadTimeout";
    private static final String IMPLICITLY_WAIT = "defaultImplicitlyWait";
    private static final String POLLING_INTERVAL = "defaultPollingInterval";
    @Getter private final ResourcePropertiesManager resourcePropertiesManager = new ResourcePropertiesManager(PROPERTIES_FILE);
    private static BrowserConfiguration configuration = null;

    public static BrowserConfiguration getInstance() {
        if (configuration == null) {
            synchronized (BrowserConfiguration.class) {
                if (configuration == null) {
                    configuration = new BrowserConfiguration();
                }
            }
        }
        return configuration;
    }

    public long getTimeoutForConditionInMellis() {
        return resourcePropertiesManager.getLongProperties(DEFAULT_CONDITION_TIMEOUT);
    }

    public int getTimeoutForConditionInSeconds() {
        return (int) TimeUnit.MILLISECONDS.toSeconds(resourcePropertiesManager.getIntProperties(DEFAULT_CONDITION_TIMEOUT));
    }

    public long getImplicitlyWait() {
        return resourcePropertiesManager.getLongProperties(IMPLICITLY_WAIT);
    }

    public long getPollingInterval() {
        return resourcePropertiesManager.getLongProperties(POLLING_INTERVAL);
    }

    public long getDefaultTimeoutToLoadPages() {
        return resourcePropertiesManager.getLongProperties(DEFAULT_PAGE_LOAD_TIMEOUT);
    }
}
