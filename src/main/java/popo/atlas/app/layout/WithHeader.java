package popo.atlas.app.layout;

import popo.atlas.app.element.Header;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface WithHeader {

    @FindBy("//header[contains(@class,'Header')]")
    Header header();

}
