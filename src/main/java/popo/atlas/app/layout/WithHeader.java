package popo.atlas.app.layout;

import io.qameta.atlas.core.api.Retry;
import popo.atlas.app.element.Header;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface WithHeader {

    @Retry
    @FindBy("//header[contains(@class,'Header')]")
    Header header();

}
