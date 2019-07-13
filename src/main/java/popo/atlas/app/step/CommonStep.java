package popo.atlas.app.step;

import io.qameta.atlas.webdriver.WebPage;
import lombok.extern.log4j.Log4j2;
import popo.atlas.app.element.Header;
import popo.atlas.app.layout.WithHeader;
import popo.atlas.app.page.SearchPage;
import popo.atlas.framework.base.BaseTest;
import popo.atlas.framework.base.driver.Browser;
import ru.yandex.qatools.allure.annotations.Step;

import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;

@Log4j2
public class CommonStep extends BaseTest {

    @Step("Searching GitHub content: {1}")
    protected <T extends WebPage & WithHeader> SearchPage search(Class<T> page, String searchText) {
        WebPage webPage = onPage(page);
        Header header = ((WithHeader)webPage).header();
        log.info(header.getWrappedElement());
        header
                .searchInput().waitUntil(displayed(), Browser.TIMEOUT)
                .sendKeys(searchText);
        header
                .searchInput()
                .submit();
        return onPage(SearchPage.class);
    }
}
