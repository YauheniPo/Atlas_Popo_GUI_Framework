package popo.atlas.app;

import org.testng.annotations.Test;
import popo.atlas.app.page.MainPage;
import popo.atlas.app.page.SearchPage;
import popo.atlas.app.step.CommonStep;

import static org.hamcrest.Matchers.*;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;

public class FindRepositoryTest extends CommonStep {

    @Test(groups = {TestGroup.GIT_GROUP})
    public void testFindRepositoryByName() {
        final String repoTitleText = "YauheniPo";

        SearchPage searchPage = search(MainPage.class, repoTitleText);

        searchPage.repositories()
                .waitUntil(String.format("Any repository does not contains '%s'", repoTitleText),
                        contains(text(anyOf(containsString(repoTitleText), containsString(repoTitleText.toLowerCase())))));
    }
}
