package popo.atlas.app.element;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Param;
import ru.yandex.qatools.allure.annotations.Description;

public interface UserNavBar extends AtlasWebElement {

    String USER_NAV_BAR_ITEM_OVERVIEW = "Overview";

    @Description("User NavBar Item '{{ barItem }}'")
    @FindBy(".//a[contains(text(), '{{ barItem }}')]")
    AtlasWebElement item(@Param("barItem") String barItem);
}
