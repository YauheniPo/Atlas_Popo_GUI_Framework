package popo.atlas.framework.utils.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import popo.atlas.framework.utils.ResourcePropertiesManager;

@Component
@PropertySource(ignoreResourceNotFound = true, value = "classpath:stage.properties")
public class StageConfiguration {

    private static final ResourcePropertiesManager RESOURCE_PROPERTIES_MANAGER = new ResourcePropertiesManager("stage.properties");

    @Getter @NonNull
    @Value("${stage}")
    private String stage;

    @Bean
    public String getStageUrl() {
        return RESOURCE_PROPERTIES_MANAGER.getStringProperty(String.format("stage.%s.url", stage));
    }
}
