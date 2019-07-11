package popo.atlas.app;

import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Test;
import popo.atlas.app.element.SearchingMenu;
import popo.atlas.app.page.MainPage;
import popo.atlas.app.page.SearchPage;
import popo.atlas.app.step.CommonStep;

import static org.hamcrest.Matchers.*;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;

public class FindUserTest extends CommonStep {

    @Video
    @Test(groups = {TestGroup.GIT_GROUP})
    public void testFindUserByName() {
        final String username = "YauheniPo";

        SearchPage searchPage = search(MainPage.class, username);

        searchPage.menu().clickToItem(SearchingMenu.USERS_ITEM);
        searchPage.usersWithWait(1).get(0).username()
                .should(String.format("Every repository does not contains '%s'", username), is(text(username)));
    }

    @Video
    @Test(groups = {TestGroup.GIT_GROUP})
    public void testFindUserByNameUrlParams() {
        final String username = "YauheniPo";

        GitHubSite gitHubSite = onPage(GitHubSite.class);
        gitHubSite.onSearchPage(username, SearchingMenu.USERS_ITEM).usersWithWait(1).get(0).username()
                .should(String.format("Every repository does not contains '%s'", username), is(text(username)));
    }
}
