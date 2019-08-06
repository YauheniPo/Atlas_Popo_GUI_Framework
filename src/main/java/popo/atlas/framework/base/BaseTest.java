package popo.atlas.framework.base;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WrapsDriver;

@Log4j2
public class BaseTest extends BaseEntity {

    protected <T extends WrapsDriver & SearchContext> T onPage(Class<T> page) {
        log.info(String.format("Go to %s page", page.getName()));
        return AtlasHelper.getAtlas().create(getWebDriver(), page);
    }
}
