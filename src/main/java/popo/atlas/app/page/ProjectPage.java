package popo.atlas.app.page;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import popo.atlas.app.layout.WithHeader;

public interface ProjectPage extends WebPage, WithHeader, BasePage {

    @FindBy(".//a[contains(.,'contributor')]")
    AtlasWebElement contributors();
}
