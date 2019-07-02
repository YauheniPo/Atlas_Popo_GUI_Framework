package popo.atlas.app;

import org.testng.annotations.Test;
import popo.atlas.app.step.CommonStep;
import popo.atlas.framework.base.driver.Browser;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class ContributorTest extends CommonStep {

    @Test(groups = {TestGroup.GIT_GROUP})
    public void testContributor() {
        final String profile = "YauheniPo";
        final String project = "RobotFramework_GUI_Tests";

        GitHubSite gitHubSite = onPage(GitHubSite.class);
        gitHubSite.onProjectPage(profile, project).contributors().click();
        gitHubSite.onContributorsPage().contribPersons()
                .waitUntil("Profile '%s' project '%s' does not have contributors", hasSize(1), Browser.TIMEOUT);
    }
}
