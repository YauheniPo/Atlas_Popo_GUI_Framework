package popo.atlas.app;

import org.testng.annotations.Test;
import popo.atlas.app.page.SearchPage;
import popo.atlas.app.step.CommonStep;

import static org.hamcrest.Matchers.is;
import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;

public class FindUserTest extends CommonStep {

    @Test(groups = {TestGroup.GIT_GROUP})
    public void testFindUserByName() {
        final String username = "YauheniPo";

        search(username);
        SearchPage searchPage = onPage(SearchPage.class);
        searchPage.menu().waitUntil(displayed(), 10).item("Users").click();
        searchPage.usersWithWait(1).get(0).username()
                .should(String.format("Every repository does not contains '%s'", username), is(text(username)));
    }
}
