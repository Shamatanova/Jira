package pages.trello;

import com.telerikacademy.testframework.CustomWebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class BoardPage extends BaseTrelloPage {

    public BoardPage(WebDriver driver) {
        super(driver, "trello.boardPage");
    }

    public void addCardToList(String cardName) {
        actions.waitForElementClickable("trello.addCard.titleInput");
        actions.typeValueInField(cardName, "trello.addCard.titleInput");
        actions.waitForElementClickable("trello.addCard.submitButton");
        actions.clickElement("trello.addCard.submitButton");
    }

    public void assertListExists(String listName) {
        actions.waitForElementPresent("trello.boardPage.listByName", listName);
    }

    public void assertCardExists(String listName, String cardName) {
        actions.waitForElementPresent("trello.boardPage.listByName", listName, cardName);
    }

    public boolean assertCardIsMoved(String cardName, String listName){
        actions.getElement("trello.createdBoard.createdList.findByListName", listName);
        actions.clickElement("trello.boardPage.cardByName",listName, cardName);
        actions.waitForElementPresent("trello.createdList.listCardDetails");

        return cardName.equals(actions.getElement("trello.createdList.listCardDetails").getText());
    }

    public boolean assertBoardDeleted()
    {
        actions.waitForElementPresent("trello.deletedBoard.messageXpath");
        String message = actions.getElement("trello.deletedBoard.messageXpath").getText();
        String expectedMessage = getUIMappingByKey("trello.boardName") +" " + getUIMappingByKey("trello.deletedBoard.messageText");
        return message.equals(expectedMessage);
    }

    public void clickCloseButton()
    {
        actions.waitForElementClickable("trello.createdCard.closeButton");
        actions.clickElement("trello.createdCard.closeButton");
    }

    public void deleteExistingBoard()
    {
        actions.waitForElementPresent("trello.createdBoard.menuButton");
        actions.clickElement("trello.createdBoard.menuButton");

        actions.waitForElementVisible("trello.createdBoard.menuOptions");
        actions.clickElement("trello.createdBoard.menuOptions");

        actions.waitForElementVisible("trello.createdBoard.menuOptions.popup");
        actions.clickElement("trello.createdBoard.menuOptions.popup.close");

        actions.waitForElementPresent("trello.createdBoard.menuOptions.popup.message");
    }

}
