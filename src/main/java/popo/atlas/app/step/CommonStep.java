package popo.atlas.app.step;

import popo.atlas.app.element.Header;
import popo.atlas.app.page.MainPage;
import popo.atlas.framework.base.BaseTest;
import ru.yandex.qatools.allure.annotations.Step;

public class CommonStep extends BaseTest {

    @Step("Searching repositories")
    protected void search(String repoTitleText) {
        MainPage mainPage = onPage(MainPage.class);
        Header header = mainPage.header();
        header.searchInput().sendKeys(repoTitleText);
        mainPage.header().searchInput().submit();
    }
}
