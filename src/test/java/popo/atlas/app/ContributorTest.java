package popo.atlas.app;

import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Test;
import popo.atlas.framework.base.BaseTest;
import popo.atlas.framework.base.driver.Browser;

import static org.hamcrest.Matchers.hasSize;

public class ContributorTest extends BaseTest {

    @Video
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
