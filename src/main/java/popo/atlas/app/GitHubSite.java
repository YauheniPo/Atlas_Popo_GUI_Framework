package popo.atlas.app;

import io.qameta.atlas.webdriver.WebSite;
import io.qameta.atlas.webdriver.extension.Page;
import io.qameta.atlas.webdriver.extension.Path;
import io.qameta.atlas.webdriver.extension.Query;
import popo.atlas.app.page.ContributorsPage;
import popo.atlas.app.page.MainPage;
import popo.atlas.app.page.ProjectPage;
import popo.atlas.app.page.SearchPage;

public interface GitHubSite extends WebSite {

    @Page
    MainPage onMainPage();

    @Page(url = "search")
    SearchPage onSearchPage(@Query("q") String value, @Query("type") String user);

    @Page(url = "{profile}/{project}/tree/master/")
    ProjectPage onProjectPage(@Path("profile") String profile, @Path("project") String project);

    @Page
    ContributorsPage onContributorsPage();
}
