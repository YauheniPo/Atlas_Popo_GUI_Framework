package popo.atlas.app.element;

import io.qameta.atlas.core.api.Retry;
import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Param;
import ru.yandex.qatools.allure.annotations.Description;

public interface SearchingMenu extends AtlasWebElement {

    @Description("Searching Item '{{ name }}'")
    @Retry
    @FindBy(".//a[contains(@class, '-item') and not(contains(@class, 'UnderlineNav'))][contains(text(), '{{ name }}')]")
    AtlasWebElement item(@Param("name") String name);

}
