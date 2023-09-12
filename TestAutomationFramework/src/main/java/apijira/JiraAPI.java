package apijira;

import com.telerikacademy.testframework.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Base64;

import static apijira.Constants.*;
import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class JiraAPI {
    private RequestSpecification getRestAssured() {

        String authString = PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("jira.users.user.apiKey") +
                ":" + PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("jira.users.user.apiToken");
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(authString.getBytes());

        return RestAssured.given()
                .header("Authorization", authHeader)
                .contentType(ContentType.JSON)
                .baseUri(PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("jira.apiJira.baseUrl"));
    }

    //Create issue
    public IssueModel createIssue(){
        String issueBody = String.format(
                TEXT_BODY, ISSUE_SUMMARY, ISSUE_DESCRIPTION, ISSUE_TYPE_NAME, PRIORITY_NAME, getUIMappingByKey("jira.boardPage.boardAbreviature"));

        return getRestAssured()
                .body(issueBody)
                .when()
                .post("/issue")
                .then()
                .extract()
                .response()
                .as(IssueModel.class);
    }

    //Create story
    public StoryModel createStory(){
        String storyBody = String.format(
                TEXT_BODY, STORY_SUMMARY, STORY_DESCRIPTION,STORY_TYPE_NAME,PRIORITY_NAME, getUIMappingByKey("jira.boardPage.boardAbreviature"));

        return getRestAssured()
                .body(storyBody)
                .when()
                .post("/issue")
                .then()
                .extract()
                .response()
                .as(StoryModel.class);
    }

}
