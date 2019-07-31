package popo.atlas.framework.utils.configurations;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import popo.atlas.framework.utils.ResourcePropertiesManager;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageConfiguration {

    private static final String PROPERTIES_FILE = "stage.properties";
    private static final String STAGE_URL = "url";
    @Getter private static final ResourcePropertiesManager RESOURCE_PROPERTIES_MANAGER = new ResourcePropertiesManager(PROPERTIES_FILE);
    private static StageConfiguration configuration = null;

    public static StageConfiguration getInstance() {
        if (configuration == null) {
            synchronized (StageConfiguration.class) {
                if (configuration == null) {
                    configuration = new StageConfiguration();
                }
            }
        }
        return configuration;
    }

    public String getStageUrl() {
        return RESOURCE_PROPERTIES_MANAGER.getStringProperty(STAGE_URL);
    }
}
