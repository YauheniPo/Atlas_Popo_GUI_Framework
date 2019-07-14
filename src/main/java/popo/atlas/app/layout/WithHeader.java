package popo.atlas.app.layout;

import io.qameta.atlas.core.api.Retry;
import popo.atlas.app.element.Header;
import popo.atlas.framework.atlas.extention.ContainsClass;
import ru.yandex.qatools.allure.annotations.Description;

public interface WithHeader {

    @Description("Header")
    @ContainsClass(tag = "header", classValue = "Header")
    @Retry(timeout = 100000L)
    Header header();

}
