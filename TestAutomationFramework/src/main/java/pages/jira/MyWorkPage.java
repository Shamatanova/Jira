package pages.jira;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class MyWorkPage extends BaseJiraPage {

    public MyWorkPage(WebDriver driver) {
        super(driver, "trello.boardsPage");
    }

    public void clickOnBoard() {

        actions.waitForElementVisible("jira.boardsPage.projectName");
        actions.waitForElementClickable("jira.boardsPage.projectName");
        actions.clickElement("jira.boardsPage.projectName");
    }

    public void clickOnResentBoard()
    {
        actions.waitForElementPresent("jira.yourWork.resentProject");
        actions.waitForElementClickable("jira.yourWork.resentProject");
        actions.clickElement("jira.yourWork.resentProject");
    }

    public void searchByKey(String key)
    {
        actions.waitForElementVisible("jira.searchInput");
        actions.waitForElementClickable("jira.searchInput");
        actions.clickElement("jira.searchInput");
        actions.typeValueInField(key,"jira.searchInput");

        actions.waitForElementVisible("jira.searchDialogWrapper");
        actions.waitForElementClickable("jira.searchResult", key);
        actions.clickElement("jira.searchResult", key);
    }

}

