package popo.atlas.app.step;

import io.qameta.atlas.webdriver.WebPage;
import popo.atlas.app.element.Header;
import popo.atlas.app.layout.WithHeader;
import popo.atlas.app.page.SearchPage;
import popo.atlas.framework.base.BaseTest;
import ru.yandex.qatools.allure.annotations.Step;

public class CommonStep extends BaseTest {

    @Step("Searching GitHub content: {1}")
    protected <T extends WebPage & WithHeader> SearchPage search(Class<T> page, String searchText) {
        WebPage webPage = onPage(page);
        Header header = ((WithHeader)webPage).header();
        header
                .searchInput()
                .sendKeys(searchText);
        header
                .searchInput()
                .submit();
        return onPage(SearchPage.class);
    }
}
