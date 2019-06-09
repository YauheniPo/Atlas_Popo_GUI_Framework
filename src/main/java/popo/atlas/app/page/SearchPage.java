package popo.atlas.app.page;

import popo.atlas.app.element.RepositoryCard;
import popo.atlas.app.layout.WithHeader;
import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface SearchPage extends WebPage, WithHeader {

    @FindBy(".//ul[contains(@class, 'repo-list')]//*[contains(@class, 'repo-list-item')]")
    ElementsCollection<RepositoryCard> repositories();

}
