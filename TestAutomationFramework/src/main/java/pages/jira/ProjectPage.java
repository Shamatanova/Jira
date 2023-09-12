package pages.jira;

import org.openqa.selenium.WebDriver;

public class ProjectPage extends BaseJiraPage {

    public ProjectPage(WebDriver driver) {
        super(driver, "jira.projectPage");
    }

    public void createStory(String summary, String description, String severity) {
        clickCreateIssueButton();
        selectStoryAsIssueType();
        fillSummary(summary);
        fillDescription(description);
        selectHighPriority(severity);
        clickCreateButton();
    }

    public void navigateToIssuesOptions() {
        actions.waitForElementVisible("jira.boardPage.issuesList.button");
        actions.waitForElementClickable("jira.boardPage.issuesList.button");
        actions.clickElement("jira.boardPage.issuesList.button");
    }

    public void createBug(String summary, String description, String priority) {

        clickCreateIssueButton();
        selectBugAsIssueType();
        fillSummary(summary);
        fillDescription(description);
        selectHighPriority(priority);
        clickCreateButton();
    }

    private void clickCreateIssueButton() {
        actions.waitForElementVisible("jira.boardPage.createButton");
        actions.waitForElementClickable("jira.boardPage.createButton");
        actions.clickElement("jira.boardPage.createButton");
    }

    private void selectBugAsIssueType() {
        actions.waitForElementVisible("jira.createIssue.issueTypeDropDown");
        actions.waitForElementClickable("jira.createIssue.issueTypeDropDown");
        actions.clickElement("jira.createIssue.issueTypeDropDown");

        actions.waitForElementVisible("jira.createIssue.issueOptions");
        actions.waitForElementClickable("jira.createIssue.issueOptions.bug");
        actions.selectIssueType("Bug");
    }

    private void selectStoryAsIssueType() {
        actions.waitForElementClickable("jira.createIssue.issueTypeDropDown");
        actions.clickElement("jira.createIssue.issueTypeDropDown");

        actions.waitForElementVisible("jira.createIssue.issueOptions");
        actions.waitForElementClickable("jira.createIssue.issueOptions.story");
        actions.selectIssueType("Story");
    }

    private void selectHighPriority(String priority) {
        //  actions.scrollDown("jira.createIssue.priorityDropDown");
        actions.waitForElementPresent("jira.createIssue.priorityDropDown");
        actions.clickElement("jira.createIssue.priorityDropDown");

        //actions.waitForElementVisible("jira.createIssue.priorityOptions");
        actions.waitForElementClickable("jira.createIssue.priorityOptions.High");
        actions.selectPriority(priority);
    }

    private void fillSummary(String textSummary) {
        actions.waitForElementClickable("jira.createIssue.summaryField");
        actions.typeValueInField(textSummary, "jira.createIssue.summaryField");

    }

    private void fillDescription(String description)
    {
        actions.waitForElementClickable("jira.boardPage.descriptionSection");
        actions.clickElement("jira.boardPage.descriptionWrapper");
        actions.typeValueInField(description, "jira.boardPage.descriptionWrapper");
    }

    private void clickCreateButton() {
        actions.waitForElementClickable("jira.createIssue.createButton");
        actions.clickElement("jira.createIssue.createButton");
    }

    public void assertSuccessfulMessageIsVisible() {
        actions.waitForElementPresent("jira.createdIssue.message");
        actions.assertElementPresent("jira.createdIssue.message");
    }

//    public void assertResultIsPresent(String resultTitle) {
//        actions.assertElementPresent("jira.createdIssue.message");
//        String textActual = actions.getElement("jira.createdIssue.message").getText();
//        actions.assertElementAttribute("jira.createdIssue.message", "innerText", resultTitle);
//    }
}
