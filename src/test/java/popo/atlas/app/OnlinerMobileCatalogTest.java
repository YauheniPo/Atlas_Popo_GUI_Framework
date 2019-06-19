package popo.atlas.app;

import org.testng.annotations.Test;
import popo.atlas.app.element.Header;
import popo.atlas.app.page.MainPage;
import popo.atlas.app.page.SearchPage;
import popo.atlas.framework.base.BaseTest;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static org.hamcrest.Matchers.hasSize;

public class OnlinerMobileCatalogTest extends BaseTest {

    @Description(value = "Validation of price")
    @Features(value = "Validation of average price")
    @Severity(value = SeverityLevel.NORMAL)
    @Test(groups = {TestGroup.ATLAS_GROUP})
    public void averagePriceTest() {
        MainPage mainPage = onPage(MainPage.class);
        Header header = mainPage.header();
        header.searchInput().sendKeys("Atlas");
        header.searchInput().submit();
        SearchPage searchPage = onPage(SearchPage.class);

        searchPage.repositories().waitUntil(hasSize(10));
        searchPage.repositories().forEach(item -> System.out.println(item.title().getText()));
    }
}
