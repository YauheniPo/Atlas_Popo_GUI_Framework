package popo.atlas.app;

import org.testng.annotations.Test;
import popo.atlas.app.element.SearchingMenu;
import popo.atlas.app.page.MainPage;
import popo.atlas.app.page.SearchPage;
import popo.atlas.app.step.CommonStep;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.StringContains.containsString;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;

public class FindUserTest extends CommonStep {

    private final String username = "YauheniPo";

    @Test(groups = {TestGroup.GIT_GROUP})
    public void testFindUserByName() {
        SearchPage searchPage = search(MainPage.class, username);

        searchPage.menu().item(SearchingMenu.USERS_ITEM).click();
        searchPage.users()
                .waitUntil(String.format("Any repository does not contains '%s'", username),
                        hasItem(text(containsString(username))));
    }

    @Test(groups = {TestGroup.GIT_GROUP})
    public void testFindUserByNameUrlParams() {
        GitHubSite gitHubSite = onPage(GitHubSite.class);

        gitHubSite.onSearchPage(username, SearchingMenu.USERS_ITEM).users()
                .filter(userCard -> userCard.username().getText().equals(username))
                .should(String.format("User '%s' does not exist", username), hasSize(1));
    }
}
