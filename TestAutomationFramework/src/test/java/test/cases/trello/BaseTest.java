package test.cases.trello;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.trello.LoginPage;

import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest {

    UserActions actions = new UserActions();

    public void login() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser("user");
    }

    public  void logout(){
        actions.waitForElementClickable("trello.logout.menuButton");
        actions.clickElement("trello.logout.menuButton");
        actions.waitForElementClickable("trello.logout.logoutButton");
        actions.clickElement("trello.logout.logoutButton");
        actions.waitForElementClickable("trello.logoutButton");
        actions.clickElement("trello.logoutButton");
    }

    @BeforeClass
    public static void setUp() {

        UserActions.loadBrowser("trello.homePage");
    }

    @AfterClass
    public static void tearDown() {

        UserActions.quitDriver();
    }
}
