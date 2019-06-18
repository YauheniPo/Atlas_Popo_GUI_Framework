package popo.atlas.app;

import org.testng.annotations.Test;
import popo.atlas.app.element.Header;
import popo.atlas.app.page.MainPage;
import popo.atlas.app.page.SearchPage;
import popo.atlas.framework.base.BaseTest;

import static org.hamcrest.Matchers.hasSize;

public class FindRepositoryByNameTest extends BaseTest {

    @Test
    public void simpleTest() {
        MainPage mainPage = onPage(MainPage.class);
        Header header = mainPage.header();
        header.searchInput().sendKeys("Atlas");
        header.searchInput().submit();
        SearchPage searchPage = onPage(SearchPage.class);

        searchPage.repositories().waitUntil(hasSize(10));
    }
}
