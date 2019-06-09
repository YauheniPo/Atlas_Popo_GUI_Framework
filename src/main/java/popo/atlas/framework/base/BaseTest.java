package popo.atlas.framework.base;

import io.qameta.atlas.webdriver.WebPage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BaseTest extends BaseEntity {

    protected <T extends WebPage> T onPage(Class<T> page) {
        log.info(String.format("Go to %s page", page.getName()));
        return super.atlas.create(getWebDriver(), page);
    }
}
