package popo.atlas.framework.utils.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@PropertySource(ignoreResourceNotFound = true, value = "classpath:${test.env:alpha}-stage.properties")
public class StageConfiguration {

    @Getter @NonNull
    @Value("${stage.url}")
    private String stage;
}
