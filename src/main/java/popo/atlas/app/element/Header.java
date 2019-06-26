package popo.atlas.app.element;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import ru.yandex.qatools.allure.annotations.Description;

public interface Header extends AtlasWebElement<Header> {

    @Description("Search Input")
    @FindBy(".//input[contains(@class, 'header-search-input')]")
    AtlasWebElement<Header> searchInput();
}
