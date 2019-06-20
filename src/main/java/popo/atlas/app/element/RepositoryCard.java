package popo.atlas.app.element;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import ru.yandex.qatools.allure.annotations.Description;

public interface RepositoryCard extends AtlasWebElement<RepositoryCard> {

    @Description("Repository Title")
    @FindBy(".//h3")
    AtlasWebElement title();

}
