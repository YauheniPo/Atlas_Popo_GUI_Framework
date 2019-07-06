package popo.atlas.app.step;

import io.qameta.atlas.webdriver.WebPage;
import lombok.extern.log4j.Log4j2;
import popo.atlas.app.element.Header;
import popo.atlas.app.layout.WithHeader;
import popo.atlas.app.page.SearchPage;
import popo.atlas.framework.base.BaseTest;
import ru.yandex.qatools.allure.annotations.Step;

@Log4j2
public class CommonStep extends BaseTest {

    @Step("Searching GitHub content: {1}")
    protected <T extends WebPage & WithHeader> SearchPage search(Class<T> page, String repoTitleText) {
        WebPage webPage = onPage(page);
        Header header = ((WithHeader)webPage).header();
        try {
            header
                    .searchInput()
                    .wait(60000L);
        } catch (InterruptedException e) {
            log.fatal(e);
            e.printStackTrace();
        }
        header
                .searchInput()
                .sendKeys(repoTitleText);
        header
                .searchInput()
                .submit();
        return onPage(SearchPage.class);
    }
}
