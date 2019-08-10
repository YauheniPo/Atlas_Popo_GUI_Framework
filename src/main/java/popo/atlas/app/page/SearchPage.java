package popo.atlas.app.page;

import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import popo.atlas.app.element.RepositoryCard;
import popo.atlas.app.element.SearchingMenu;
import popo.atlas.app.element.UserCard;
import popo.atlas.app.layout.WithHeader;
import ru.yandex.qatools.allure.annotations.Description;

public interface SearchPage extends WebPage, WithHeader, BasePage {

    @Description("Repositories")
    @FindBy(".//ul[contains(@class, 'repo-list')]//*[contains(@class, 'repo-list-item')]")
    ElementsCollection<RepositoryCard> repositories();

    @Description("User Cards")
    @FindBy(".//div[contains(@class, 'user-list')]//*[contains(@class, 'user-list-item')]")
    ElementsCollection<UserCard> users();

    @Description("Searching Menu")
    @FindBy(".//nav[contains(@class, 'menu')]/ancestor::div[1]")
    SearchingMenu menu();
}
