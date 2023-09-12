package apitrello;

import com.telerikacademy.testframework.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class TrelloAPI {

    private RequestSpecification getRestAssured() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("trello.api.baseUrl"))
                .queryParam("key", PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("trello.users.user.apiKey"))
                .queryParam("token", PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("trello.users.user.apiToken"));
    }

    // Authenticate
    public void authenticateClient() {

    }

    public List<String> getUserBoards() {
        return getRestAssured()
                .get("members/me")
                .thenReturn()
                .jsonPath()
                .get("idBoards"); // taka polyàvame List ot boards i moje da izpolzvame za trieneto na bordovete;
    }

    // API: Create a board
    public BoardModel createBoard(String name, String boardDesc) {
        return getRestAssured()
                .queryParam("name", name)
                .queryParam("desc", boardDesc)
                .log().all()
                .when().post("board")
                .then()
                .extract()
                .response()
                .as(BoardModel.class);
    }

    // API: Delete board
    public Response deleteBoard(String boardId) {
        return getRestAssured()
                .log().all()
                .when()
                .delete("boards/" + boardId)
                .then()
                .extract()
                .response();
    }

    // API: Create 2 lists
    public ListModel createListInBoard(String boardId, String firstListName) {
        return getRestAssured()
                .queryParam("name", firstListName)
                .log().all()
                .post("boards/" + boardId + "/lists")
                .then()
                .extract()
                .response()
                .as(ListModel.class);
    }

    // API: Create a card
    public CardModel createCardInList(String idList, String cardName)
    {
        return getRestAssured()
                .queryParam("idList", idList)
                .queryParam("name", cardName)
                .log().all()
                .post("cards")
                .then()
                .extract()
                .response()
                .as(CardModel.class);
    }
}
