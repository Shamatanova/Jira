package test.cases.jira;

import apijira.IssueModel;
import apijira.JiraAPI;
import apijira.StoryModel;
import org.junit.Test;
import pages.jira.IssuePage;
import pages.jira.MyWorkPage;
import pages.jira.ProjectPage;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;


public class LinkTests extends BaseTest {
    private final JiraAPI jiraAPI = new JiraAPI();
    private IssueModel createIssueResponse;
    private StoryModel createStoryResponse;

    @Test
    public void createIssueWhenFillCorrectData()
    {
        createIssueResponse = jiraAPI.createIssue();
        createStoryResponse = jiraAPI.createStory();
        String issueKey = createIssueResponse.key;
        String storyKey = createStoryResponse.key;

        login();
        MyWorkPage workPage = new MyWorkPage(actions.getDriver());
        workPage.clickOnBoard();
        workPage.searchByKey(storyKey);

        String issuePageUrl = String.format(getUIMappingByKey("jira.issuePage"), storyKey);
        IssuePage issuePage = new IssuePage(actions.getDriver(), issuePageUrl);
        issuePage.linkBugToStory(issueKey);

        issuePage.assertLinkedBugToStory(issueKey);
    }
}
