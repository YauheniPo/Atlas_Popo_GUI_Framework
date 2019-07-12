package popo.atlas.app.step;

import io.qameta.atlas.webdriver.WebPage;
import popo.atlas.app.element.Header;
import popo.atlas.app.layout.WithHeader;
import popo.atlas.app.page.SearchPage;
import popo.atlas.framework.base.BaseTest;
import ru.yandex.qatools.allure.annotations.Step;

import static ru.yandex.qatools.matchers.webdriver.EnabledMatcher.enabled;
import static ru.yandex.qatools.matchers.webdriver.ExistsMatcher.exists;

public class CommonStep extends BaseTest {

    @Step("Searching GitHub content: {1}")
    protected <T extends WebPage & WithHeader> SearchPage search(Class<T> page, String repoTitleText) {
        WebPage webPage = onPage(page);
        Header header = ((WithHeader)webPage).header();
        header
                .searchInput()
                .waitUntil(exists());
        header
                .searchInput().waitUntil(enabled())
                .sendKeys(repoTitleText);
        header
                .searchInput()
                .submit();
        return onPage(SearchPage.class);
    }
}
