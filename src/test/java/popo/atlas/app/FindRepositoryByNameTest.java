package popo.atlas.app;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import popo.atlas.app.element.Header;
import popo.atlas.app.page.MainPage;
import popo.atlas.app.page.SearchPage;
import popo.atlas.framework.base.BaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class FindRepositoryByNameTest extends BaseTest {

    @Test
    public void simpleTest() {
        final String repoTitleText = "Atlas";

        MainPage mainPage = onPage(MainPage.class);
        Header header = mainPage.header();
        header.searchInput().sendKeys(repoTitleText);
        header.searchInput().submit();
        SearchPage searchPage = onPage(SearchPage.class);

        boolean isTitlesContainsText = searchPage.repositories().waitUntil(hasSize(10)).stream()
                .allMatch(repo -> StringUtils.containsIgnoreCase(repo.title().getText(), repoTitleText));
        assertThat(String.format("Titles of repositories does not exist '%s'", repoTitleText), isTitlesContainsText, is(true));
    }
}
