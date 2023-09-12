package test.cases.jira;

import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import pages.jira.LoginPage;

public class BaseTest {
    static UserActions actions = new UserActions();

    public void login() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser("user");

    }
    public void logout(){
        actions.waitForElementClickable("jira.yourProfile.Button");
        actions.clickElement("jira.yourProfile.Button");

        actions.waitForElementClickable("jira.yourProfile.logOutButton");
        actions.clickElement("jira.yourProfile.logOutButton");

        actions.waitForElementClickable("jira.logOut.button");
        actions.clickElement("jira.logOut.button");
    }

    @BeforeClass
    public static void setUp() {

        UserActions.loadBrowser("jira.homePage");
        actions.clickElement("jira.singInButton");
    }

    @AfterClass
    public static void tearDown() {

        UserActions.quitDriver();
    }
}
