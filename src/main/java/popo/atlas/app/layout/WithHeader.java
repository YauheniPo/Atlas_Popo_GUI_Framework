package popo.atlas.app.layout;

import popo.atlas.app.element.Header;
import io.qameta.atlas.webdriver.extension.FindBy;
import ru.yandex.qatools.allure.annotations.Description;

public interface WithHeader {

    @Description("Header")
    @FindBy(".//header[contains(@class, 'Header')]")
    Header header();

}
