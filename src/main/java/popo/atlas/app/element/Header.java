package popo.atlas.app.element;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface Header extends AtlasWebElement<Header> {

    @FindBy(".//input[contains(@class,'header-search-input')]")
    AtlasWebElement<Header> searchInput();

}
