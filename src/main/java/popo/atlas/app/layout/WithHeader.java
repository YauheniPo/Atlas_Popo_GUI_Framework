package popo.atlas.app.layout;

import popo.atlas.app.element.Header;
import popo.atlas.framework.atlas.extention.ContainsClass;
import ru.yandex.qatools.allure.annotations.Description;

public interface WithHeader {

    @Description("Header")
    @ContainsClass(tag = "header", classValue = "Header")
    Header header();

}
