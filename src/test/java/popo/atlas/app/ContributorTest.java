package popo.atlas.app;

import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Test;
import popo.atlas.framework.base.BaseTest;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.StringContains.containsString;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;

public class ContributorTest extends BaseTest {

    private final String myProfile = "YauheniPo";

    @Video
    @Test(groups = {TestGroup.GIT_GROUP})
    public void testMyProjectContributor() {
        final String project = "RobotFramework_GUI_Tests";

        GitHubSite gitHubSite = onPage(GitHubSite.class);
        gitHubSite.onProjectPage(myProfile, project).contributors().click();
        gitHubSite.onContributorsPage().contribPersons()
                .waitUntil(String.format("Profile '%s' project '%s' does not have contributors", myProfile, project),
                        hasSize(1), BROWSER_CONFIGURATION.getTimeoutForCondition());
    }

    @Video
    @Test(groups = {TestGroup.GIT_GROUP})
    public void testRobotProjectContributor() {
        final String profile = "Hi-Fi";
        final String project = "robotframework-seleniumlibrary-java";

        GitHubSite gitHubSite = onPage(GitHubSite.class);
        gitHubSite.onProjectPage(profile, project).contributors().click();
        gitHubSite.onContributorsPage().contribPersons()
                .waitUntil(String.format("Profile '%s' project '%s' does not have contributor '%s'", profile, project, myProfile),
                        hasItem(text(containsString(myProfile))), BROWSER_CONFIGURATION.getTimeoutForCondition());
    }
}
