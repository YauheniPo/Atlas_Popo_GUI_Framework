package popo.atlas.app;

import org.testng.annotations.Test;
import popo.atlas.app.page.MainPage;
import popo.atlas.app.page.SearchPage;
import popo.atlas.framework.base.BaseTest;

import static org.hamcrest.Matchers.hasSize;

public class FindRepositoryByNameTest extends BaseTest {

    @Test
    public void simpleTest() {
        MainPage mainPage = onPage(MainPage.class);
        mainPage.header().searchInput().sendKeys("Atlas");
        SearchPage searchPage = onPage(SearchPage.class);
        searchPage.header().searchInput().submit();

        searchPage.repositories().waitUntil(hasSize(10));
    }
}
