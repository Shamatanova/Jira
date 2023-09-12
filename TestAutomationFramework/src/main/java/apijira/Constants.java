package apijira;

public class Constants {

    public static final String ISSUE_SUMMARY =
            "The *Забронировать* button in the *Feature Tours* section is out of the borders";
    public static final String STORY_SUMMARY =
            "[Topic] Create new topic with valid title and valid description";
    public static final String STORY_DESCRIPTION = "*Description:*" +
            "\\n"+
            "Create a new topic with the correct title and correct description." +
            "\\n" +
            "*Preconditions:*" +
            "\\n" +
            "Log in with valid credentials. " +
            "\\n" +
            "*Test Steps:*" +
            "\\n" +
            "1. Navigate to the URL https://stage-forum.telerikacademy.com/" +
            "\\n" +
            "2. Click on the New Topic button" +
            "\\n" +
            "3. Fill fields in create a topic pop-up with:" +
            "\\n"+
            "- Discussion name - \\\"Test Discussion Dany\\\";" +
            "\\n" +
            "- Description text field - \\\"Create a new topic with all fields\\\";" +
            "\\n" +
            "4. Click on Create button"+
            "\\n"+
            "*Expected result:*" +
            "\\n" +
            "1. The user is redirected to the new topic page;" +
            "\\n" +
            "2. The new topic should be visible in the topics list" +
            "\\n";
    public static final String ISSUE_DESCRIPTION = "*Description:*" +
            "\\n"+
            "e.g. It's hard to separate where the different sections start and end " +
            "\\n"+
            "-> Destinations, check-in date, check-out date, guests" +
            "\\n" +
            "*Steps to reproduce:*" +
            "\\n" +
            "Navigate to https://phptravels.net/ru" +
            "\\n" +
            "*Expected result:*" +
            "\\n" +
            "It would be nice to have a visual separation between the different elements. " +
            "\\n" +
            "This will improve the user experience and site usability" +
            "\\n" +
            "*Actual result:*  It would be nice to have a visual separation between the different elements. " +
            "\\n" +
            "This will improve the user experience and site usability" +
            "\\n" +
            "*Notes:*" +
            "\\n" +
            "Reproducible in browsers: FireFox";
    public static final String LINK_BODY = "{\"type\": {\"id\": \"10000\"},\"inwardIssue\": {\"key\": \"%s\"}," +
            "\"outwardIssue\": {\"key\": \"%s\"}}";
    public static final String ISSUE_TYPE_NAME = "Bug";
    public static final String STORY_TYPE_NAME = "Story";
    public static final String PRIORITY_NAME = "Medium";
    public static  final  String TEXT_BODY = "{\"fields\":{" +
            "\"summary\":\"%s\"," +
            "\"description\":\"%s\"," +
            "\"issuetype\":{" +
            "\"name\":\"%s\"}," +
            "\"priority\":{" +
            "\"name\":\"%s\"}," +
            "\"project\":" +
            "{\"key\":\"%s\"}}}";

}
