package popo.atlas.app.page;

import popo.atlas.app.element.RepositoryCard;
import popo.atlas.app.layout.WithHeader;
import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import ru.yandex.qatools.allure.annotations.Description;

public interface SearchPage extends WebPage, WithHeader {

    @Description("Repositories")
    @FindBy(".//ul[contains(@class, 'repo-list')]//*[contains(@class, 'repo-list-item')]")
    ElementsCollection<RepositoryCard> repositories();

}
