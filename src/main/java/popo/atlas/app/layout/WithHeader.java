package popo.atlas.app.layout;

import io.qameta.atlas.core.api.Retry;
import popo.atlas.app.element.Header;
import io.qameta.atlas.webdriver.extension.FindBy;
import ru.yandex.qatools.allure.annotations.Description;

public interface WithHeader {

    @Description("Header")
    @Retry
    @FindBy(".//header[contains(@class, 'Header')]")
    Header header();

}
