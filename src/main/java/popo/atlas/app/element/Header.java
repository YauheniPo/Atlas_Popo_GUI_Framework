package popo.atlas.app.element;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import popo.atlas.framework.base.driver.Browser;
import ru.yandex.qatools.allure.annotations.Description;

import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;

public interface Header extends AtlasWebElement<Header> {

    @Description("Search Input")
    @FindBy(".//input[contains(@class, 'header-search-input')]")
    AtlasWebElement<Header> searchInput();

    default AtlasWebElement searchInputWithWait() {
        return searchInput().waitUntil(displayed(), Browser.TIMEOUT);
    }

}
