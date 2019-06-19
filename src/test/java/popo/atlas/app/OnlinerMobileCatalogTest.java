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

    }
}
