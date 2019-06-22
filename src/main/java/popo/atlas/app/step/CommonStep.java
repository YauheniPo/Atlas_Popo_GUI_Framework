package popo.atlas.app.step;

import popo.atlas.app.element.Header;
import popo.atlas.app.page.MainPage;
import popo.atlas.framework.base.BaseTest;
import popo.atlas.framework.base.driver.Browser;
import ru.yandex.qatools.allure.annotations.Step;

import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;

public class CommonStep extends BaseTest {

    @Step("Searching repositories")
    protected void search(String repoTitleText) {
        MainPage mainPage = onPage(MainPage.class);
        Header header = mainPage.header();
        header.searchInput().waitUntil(displayed(), Browser.TIMEOUT).sendKeys(repoTitleText);
        mainPage.header().searchInput().submit();
    }
}
