package popo.atlas.app.element;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Param;
import ru.yandex.qatools.allure.annotations.Description;

import static org.hamcrest.Matchers.containsString;
import static ru.yandex.qatools.matchers.webdriver.AttributeMatcher.className;

public interface SearchingMenu extends AtlasWebElement<SearchingMenu> {

    String USERS_ITEM = "Users";

    @Description("Searching Item '{{ name }}'")
    @FindBy(".//a[contains(@class, '-item') and not(contains(@class, 'UnderlineNav'))][contains(text(), '{{ name }}')]")
    AtlasWebElement item(@Param("name") String name);

    default void clickToItem(String name) {
        item(name).click();
        item(name).waitUntil(className(containsString("selected")));
    }

}
