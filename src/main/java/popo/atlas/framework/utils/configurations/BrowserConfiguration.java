package popo.atlas.framework.utils.configurations;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import popo.atlas.framework.utils.ResourcePropertiesManager;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrowserConfiguration {

    private static final String PROPERTIES_FILE = "browser.properties";
    private static final String DEFAULT_CONDITION_TIMEOUT = "defaultConditionTimeout";
    private static final String DEFAULT_PAGE_LOAD_TIMEOUT = "defaultPageLoadTimeout";
    private static final String IMPLICITLY_WAIT = "defaultImplicitlyWait";
    private static final String POLLING_INTERVAL = "defaultPollingInterval";
    @Getter private static final ResourcePropertiesManager RESOURCE_PROPERTIES_MANAGER = new ResourcePropertiesManager(PROPERTIES_FILE);
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

    public int getTimeoutForCondition() {
        return RESOURCE_PROPERTIES_MANAGER.getIntProperties(DEFAULT_CONDITION_TIMEOUT);
    }

    public long getImplicitlyWait() {
        return RESOURCE_PROPERTIES_MANAGER.getLongProperties(IMPLICITLY_WAIT);
    }

    public long getPollingInterval() {
        return RESOURCE_PROPERTIES_MANAGER.getLongProperties(POLLING_INTERVAL);
    }

    public long getDefaultTimeoutToLoadPages() {
        return RESOURCE_PROPERTIES_MANAGER.getLongProperties(DEFAULT_PAGE_LOAD_TIMEOUT);
    }
}
