package popo.atlas.app.element;

import io.qameta.atlas.core.api.Retry;
import io.qameta.atlas.webdriver.AtlasWebElement;
import org.openqa.selenium.ElementNotInteractableException;
import popo.atlas.framework.atlas.extention.ContainsClass;
import ru.yandex.qatools.allure.annotations.Description;

public interface Header extends AtlasWebElement {

    @Description("Search Input")
    @ContainsClass(tag = "input", classValue = "header-search-input")
    @Retry(ignoring = ElementNotInteractableException.class, timeout = 120000L)
    AtlasWebElement searchInput();
}
