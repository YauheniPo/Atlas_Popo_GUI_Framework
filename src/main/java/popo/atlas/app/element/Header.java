package popo.atlas.app.element;

import io.qameta.atlas.webdriver.AtlasWebElement;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import popo.atlas.framework.atlas.extention.ContainsClass;
import popo.atlas.framework.base.driver.Browser;
import ru.yandex.qatools.allure.annotations.Description;

import java.util.NoSuchElementException;

public interface Header extends AtlasWebElement {

    @Description("Search Input")
    @ContainsClass(tag = "input", classValue = "header-search-input")
    AtlasWebElement searchInput();

    default AtlasWebElement waitUntilAllElementsAreVisible() {
        FluentWait<WebDriver> wait = new WebDriverWait(Browser.getDriver(), Browser.TIMEOUT)
                .ignoring(ElementNotInteractableException.class, StaleElementReferenceException.class);
        return wait.until(driver -> searchInput());
    }
}
