package popo.atlas.app.element;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import ru.yandex.qatools.allure.annotations.Description;

public interface UserCard extends AtlasWebElement<UserCard> {

    @Description("User Name")
    @FindBy(".//em")
    AtlasWebElement username();

}
