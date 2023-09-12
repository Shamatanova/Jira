package test.cases.trello;

import apitrello.BoardModel;
import apitrello.CardModel;
import apitrello.ListModel;
import apitrello.TrelloAPI;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.trello.BoardPage;
import pages.trello.BoardsPage;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static com.telerikacademy.testframework.Utils.getWebDriver;

public class BoardTest extends BaseTest {

    private final TrelloAPI trelloAPI = new TrelloAPI();
    private BoardModel createdBoardResponse;
    private ListModel createdListResponse;
    private CardModel createdCardResponse;

    @Before
    public void beforeTest()
    {
        createdBoardResponse = trelloAPI.createBoard(getUIMappingByKey("trello.api.nameBoard"), getUIMappingByKey("trello.api.descriptionBoard"));
    }

    @After
    public void afterTest()
    {
        Response deletionBoardResponse = trelloAPI.deleteBoard(createdBoardResponse.id);
    }
    @Test
    public void createBoardWhenCreateBoardClicked() {

        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.createBoard();

        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.assertListExists("To Do");
        boardPage.deleteExistingBoard();
      //  logout();
    }

    @Test
    public void createNewCardInExistingBoardWhenCreateCardClicked() {

    }

    @Test
    public void moveCardBetweenStatesWhenDragAndDropIsUsed() {
        var responseListFrom = trelloAPI.createListInBoard(createdBoardResponse.id, getUIMappingByKey("trello.firstListName"));
        var responseListTo = trelloAPI.createListInBoard(createdBoardResponse.id, getUIMappingByKey("trello.secondListName"));
        var createdCardResponse = trelloAPI.createCardInList(responseListFrom.id, getUIMappingByKey("trello.cardName"));

        login();
        var boardsPage = new BoardsPage(getWebDriver());
        boardsPage.clickOnBoard(getUIMappingByKey("trello.api.nameBoard"));
        actions.dragAndDropElement(createdCardResponse.name, responseListTo.name);
        var t = 1;
    }

    @Test
    public void deleteBoardWhenDeleteButtonIsClicked() {
        var boards = trelloAPI.getUserBoards();
        Assert.assertFalse(boards.isEmpty());
    }

}
