package popo.atlas.framework.utils.configurations;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import popo.atlas.framework.utils.ResourcePropertiesManager;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FrameworkConfiguration {

    private static final String PROPERTIES_FILE = "framework.properties";
    private static final String CUSTOM_RETRY_ANALYZER_MAX_RERUN_COUNT = "framework.maxRerunCount";
    @Getter private static final ResourcePropertiesManager RESOURCE_PROPERTIES_MANAGER = new ResourcePropertiesManager(PROPERTIES_FILE);
    private static FrameworkConfiguration configuration = null;

    public static FrameworkConfiguration getInstance() {
        if (configuration == null) {
            synchronized (FrameworkConfiguration.class) {
                if (configuration == null) {
                    configuration = new FrameworkConfiguration();
                }
            }
        }
        return configuration;
    }

    public int getMaxRerunCount() {
        return RESOURCE_PROPERTIES_MANAGER.getIntProperties(CUSTOM_RETRY_ANALYZER_MAX_RERUN_COUNT);
    }
}
