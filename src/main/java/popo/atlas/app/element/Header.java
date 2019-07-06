package popo.atlas.app.element;

import io.qameta.atlas.core.api.Retry;
import io.qameta.atlas.webdriver.AtlasWebElement;
import popo.atlas.framework.atlas.extention.ContainsClass;
import ru.yandex.qatools.allure.annotations.Description;

public interface Header extends AtlasWebElement<Header> {

    @Description("Search Input")
    @ContainsClass(tag = "input", classValue = "header-search-input")
    @Retry(timeout = 60000L)
    AtlasWebElement<Header> searchInput();
}
