package popo.atlas.app.layout;

import io.qameta.atlas.webdriver.extension.FindBy;
import popo.atlas.app.element.UserNavBar;
import ru.yandex.qatools.allure.annotations.Description;

public interface WithUserNavBar {

    @Description("User Nav Bar")
    @FindBy(".//*[@class='UnderlineNav-body']")
    UserNavBar userNavBar();

}
