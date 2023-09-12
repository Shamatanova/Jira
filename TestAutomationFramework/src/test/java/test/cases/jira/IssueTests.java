package test.cases.jira;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.jira.MyWorkPage;
import pages.jira.ProjectPage;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class IssueTests extends BaseTest {

    @Before
    public void logIn()
    {
        login();
    }

    @After
    public void logOut()
    {
        logout();
    }

    @Test
    public void createBugWhenCreateIssueFieldsFilled() {
        String storySummary = "The Home page displays language inconsistency";
        String description = "Description: Not all information on the site is translated into Russian \n" +
                "Steps to reproduce: Navigate to https://phptravels.net/ru \n" +
                "Expected result: All information on the page should be in  Russian  \n" +
                "Actual result: Some elements are displayed in English \n" +
                "Notes: Reproducible in browsers: FireFox";

        MyWorkPage workPage = new MyWorkPage(actions.getDriver());
        workPage.clickOnBoard();

        ProjectPage projectPage = new ProjectPage(actions.getDriver());
        projectPage.navigateToIssuesOptions();
        projectPage.createBug(storySummary, description,"High");

        projectPage.assertSuccessfulMessageIsVisible();
    }

    @Test
    public void createStoryWhenCreateIssueFieldsFilled() {
        String storySummary = "Create JIRA story homework";
        String description = "Description : Use your free JIRA projects to practice testing Web Services\n" +
                "Task 1\n" +
                "Create a story in JIRA via JIRA API and Postman\n" +
                "Use Base Auth with using the JIRA API token\n" +
                "Follow the best principles in creating a story\n" +
                "Set priority based on the severity\n" +
                "Task 2\n" +
                "Create a bug in JIRA via JIRA API and Postman\n" +
                "Use Base Auth with using the JIRA API token\n" +
                "Follow the best principles in creating a bug\n" +
                "Set priority based on the severity\n" +
                "Task 3\n" +
                "Link the bug to the story in JIRA via JIRA API and Postman\n" +
                "Use Base Auth with using the JIRA API token\n" +
                "Use relation 'is blocked by'\n" +
                "Hints and what don't forget to have:*\n" +
                "Meaningful Title\n" +
                "Test steps/Steps to reproduce\n" +
                "Expected vs. Actual result\n" +
                "Severity/Priority\n" +
                "Prerequisites\n" +
                "Additional info\n" +
                "Classifications \n" +
                "Others as screenshots, an assignee, transitions and everything useful";

        MyWorkPage workPage = new MyWorkPage(actions.getDriver());
        workPage.clickOnResentBoard();

        ProjectPage projectPage = new ProjectPage(actions.getDriver());
        projectPage.navigateToIssuesOptions();
        projectPage.createStory(storySummary, description, "High");

        projectPage.assertSuccessfulMessageIsVisible();
    }
}
