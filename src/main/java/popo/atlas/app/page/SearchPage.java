package popo.atlas.app.page;

import popo.atlas.app.element.RepositoryCard;
import popo.atlas.app.element.SearchingMenu;
import popo.atlas.app.element.UserCard;
import popo.atlas.app.layout.WithHeader;
import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import popo.atlas.framework.base.driver.Browser;
import ru.yandex.qatools.allure.annotations.Description;

import static org.hamcrest.Matchers.*;

public interface SearchPage extends WebPage, WithHeader {

    @Description("Repositories")
    @FindBy(".//ul[contains(@class, 'repo-list')]//*[contains(@class, 'repo-list-item')]")
    ElementsCollection<RepositoryCard> repositories();

    default ElementsCollection<RepositoryCard> repositoriesWithWait(int size) {
        return repositories().waitUntil(hasSize(size), Browser.TIMEOUT);
    }

    @Description("Repositories")
    @FindBy(".//div[contains(@class, 'user-list')]//*[contains(@class, 'user-list-item')]")
    ElementsCollection<UserCard> users();

    default ElementsCollection<UserCard> usersWithWait(int size) {
        return users().waitUntil(hasSize(size), Browser.TIMEOUT);
    }

    @Description("Searching Menu")
    @FindBy(".//nav[contains(@class, 'menu')]/ancestor::div[1]")
    SearchingMenu menu();

}
