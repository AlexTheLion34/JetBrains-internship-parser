package project.parser.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import project.model.Assignee;
import project.model.GeneralIssue;
import project.parser.MyParser;

import java.util.ArrayList;
import java.util.List;

public class YouTrackParser implements MyParser {

    private final List<GeneralIssue> issueList = new ArrayList<>();

    @Override
    public List<GeneralIssue> parse(String toParse) {

        JsonArray jsonIssues = (JsonArray) new JsonParser().parse(toParse);

        for (JsonElement issue : jsonIssues) {
            JsonObject issueObject = issue.getAsJsonObject();
            if (issueObject.get("assignee").toString().contains("position")) {
                JsonObject assigneeObject = issueObject.get("assignee").getAsJsonObject();

                issueList.add(new GeneralIssue(issueObject.get("summary").getAsString(),
                              new Assignee(assigneeObject.get("name").getAsString(),
                                           assigneeObject.get("position").getAsString())));
            } else {
                issueList.add(new GeneralIssue(issueObject.get("summary").getAsString(),
                              new Assignee(issueObject.get("assignee").getAsString(), null)));
            }
        }
        return issueList;
    }
}
