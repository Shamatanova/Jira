package pages.jira;

import org.openqa.selenium.WebDriver;

public class IssuePage extends BaseJiraPage{

    public IssuePage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }

    public void linkBugToStory(String bugKey){
        actions.waitForElementClickable("jira.issuePage.linkButton");
        actions.clickElement("jira.issuePage.linkButton");
        actions.waitForElementVisible("jira.issuePage.linkButton.linkIssue");
        actions.waitForElementClickable("jira.issuePage.linkButton.linkIssue");
        actions.clickElement("jira.issuePage.linkButton.linkIssue");

        actions.waitForElementClickable("jira.issuePage.linkIssue.type");
        actions.clickElement("jira.issuePage.linkIssue.type");
        actions.waitForElementVisible("jira.issuePage.linkIssue.resultMenu");
        actions.waitForElementClickable("jira.issuePage.linkIssue.blockedResult");
        actions.clickElement("jira.issuePage.linkIssue.blockedResult");

        actions.waitForElementClickable("jira.issuePage.linkIssue.searchField");
        actions.clickElement("jira.issuePage.linkIssue.searchField");
        actions.typeValueInField(bugKey, "jira.issuePage.linkIssue.searchIssueInputField");

        actions.waitForElementVisible("jira.issuePage.linkIssue.searchMenu");
        actions.waitForElementClickable("jira.issuePage.linkIssue.resultOptions", bugKey);
        actions.clickElement("jira.issuePage.linkIssue.resultOptions", bugKey);

        actions.waitForElementVisible("jira.issuePage.LinkIssue.linkButton");
        actions.waitForElementClickable("jira.issuePage.LinkIssue.linkButton");
        actions.clickElement("jira.issuePage.LinkIssue.linkButton");
    }

    public void assertLinkedBugToStory(String key)
    {
        actions.waitForElementPresent("jira.issuePage.LinkIssue.linkedText", key);
    }
}
