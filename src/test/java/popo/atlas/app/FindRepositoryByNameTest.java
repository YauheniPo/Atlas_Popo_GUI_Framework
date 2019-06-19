package popo.atlas.app;

import org.testng.annotations.Test;
import popo.atlas.app.element.Header;
import popo.atlas.app.page.MainPage;
import popo.atlas.app.page.SearchPage;
import popo.atlas.framework.base.BaseTest;

import static org.hamcrest.Matchers.*;
import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;

public class FindRepositoryByNameTest extends BaseTest {

    @Test
    public void simpleTest() {
        final String repoTitleText = "Atlas";

        MainPage mainPage = onPage(MainPage.class);
        Header header = mainPage.header().waitUntil(displayed());
        header.searchInput().waitUntil(displayed());
        header.searchInput().sendKeys(repoTitleText);
        mainPage.header().searchInput().submit();
        SearchPage searchPage = onPage(SearchPage.class);

        searchPage.repositories().waitUntil(hasSize(10))
                .should(everyItem(text(anyOf(containsString(repoTitleText), containsString(repoTitleText.toLowerCase())))));
    }
}
