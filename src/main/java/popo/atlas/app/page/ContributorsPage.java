package popo.atlas.app.page;

import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import popo.atlas.app.element.RepositoryCard;
import popo.atlas.app.layout.WithHeader;

public interface ContributorsPage extends WebPage, WithHeader, BasePage {

    @FindBy(".//ol[contains(@class, 'contrib-data')]//li[contains(@class, 'contrib-person')]")
    ElementsCollection<RepositoryCard> contribPersons();
}
