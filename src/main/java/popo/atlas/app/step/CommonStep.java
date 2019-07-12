package popo.atlas.app.step;

import io.qameta.atlas.webdriver.WebPage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import popo.atlas.app.element.Header;
import popo.atlas.app.layout.WithHeader;
import popo.atlas.app.page.SearchPage;
import popo.atlas.framework.base.BaseTest;
import ru.yandex.qatools.allure.annotations.Step;

import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;
import static ru.yandex.qatools.matchers.webdriver.EnabledMatcher.enabled;
import static ru.yandex.qatools.matchers.webdriver.ExistsMatcher.exists;

@Log4j2
public class CommonStep extends BaseTest {

    @Step("Searching GitHub content: {1}")
    protected <T extends WebPage & WithHeader> SearchPage search(Class<T> page, String repoTitleText) {
        WebPage webPage = onPage(page);
        Header header = ((WithHeader)webPage).header();
        header
                .searchInput()
                .waitUntil(displayed(), 200).getText();
        log.info("displ " + header
                .waitUntilAllElementsAreVisible().getWrappedElement().isDisplayed());
        header
                .searchInput().getWrappedElement().sendKeys(repoTitleText);
        String text = header
                .searchInput().getText();
        log.info("text " + text);
//        log.info("page " + getWebDriver().getPageSource());
//        header.searchInput().sendKeys(repoTitleText);
//        log.info("page " + getWebDriver().getPageSource());

        header
                .searchInput()
                .submit();
        return onPage(SearchPage.class);
    }
}
