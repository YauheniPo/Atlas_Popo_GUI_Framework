package popo.atlas.app.element;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Param;
import ru.yandex.qatools.allure.annotations.Description;

public interface SearchingMenu  extends AtlasWebElement {

    @Description("Searching Item '{{ item }}'")
    @FindBy(".//a[contains(@class, '-item') and not(contains(@class, 'UnderlineNav'))][contains(text(), '{{ item }}')]")
    AtlasWebElement clickSearchMenu(@Param("item") String item);

}
