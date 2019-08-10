package popo.atlas.app;

import org.testng.annotations.Test;
import popo.atlas.app.element.SearchingMenu;
import popo.atlas.app.page.MainPage;
import popo.atlas.app.page.SearchPage;
import popo.atlas.app.step.CommonStep;

import static org.hamcrest.Matchers.*;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;

public class FindUserTest extends CommonStep {

    private final String username = "YauheniPo";

    @Test(groups = {TestGroup.GIT_GROUP})
    public void testFindUserByName() {
        SearchPage searchPage = search(MainPage.class, username);

        searchPage.menu().item(SearchingMenu.USERS_ITEM).click();
        searchPage.users().get(0).username()
                .should(String.format("Every repository does not contains '%s'", username), is(text(username)));
    }

    @Test(groups = {TestGroup.GIT_GROUP})
    public void testFindUserByNameUrlParams() {
        GitHubSite gitHubSite = onPage(GitHubSite.class);
        gitHubSite.onSearchPage(username, SearchingMenu.USERS_ITEM).users().get(0).username()
                .should(String.format("Every repository does not contains '%s'", username), is(text(username)));
    }
}
