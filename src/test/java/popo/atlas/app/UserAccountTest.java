package popo.atlas.app;

import org.testng.annotations.Test;
import popo.atlas.app.element.UserNavBar;
import popo.atlas.app.page.AccountPage;
import popo.atlas.framework.base.BaseTest;

import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;

public class UserAccountTest extends BaseTest {

    private final String username = "YauheniPo";

    @Test(groups = {TestGroup.GIT_GROUP})
    public void testUserNavBar() {
        final String overviewNavBar = UserNavBar.USER_NAV_BAR_ITEM_OVERVIEW;

        GitHubSite gitHubSite = onPage(GitHubSite.class);

        AccountPage accountPage = gitHubSite.onAccountPage(username);
        accountPage.userNavBar().item(overviewNavBar)
                .should(String.format("Item '%s' in User Nav Bar does not displayed", overviewNavBar), displayed());
    }
}
