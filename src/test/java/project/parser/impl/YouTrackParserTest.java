package project.parser.impl;

import org.junit.Assert;
import org.junit.Test;
import project.model.Assignee;
import project.model.GeneralIssue;

import java.util.ArrayList;
import java.util.List;

public class YouTrackParserTest {

    private final YouTrackParser parser = new YouTrackParser();

    @Test
    public void testParseWithStringAssignee() {

        String youtrackIssue = "[{" +
                "\"summary\": \"Issue 1\"," +
                "\"assignee\": \"Aleksey\"" +
                "}]";

        GeneralIssue expectedIssue = new GeneralIssue("Issue 1", new Assignee("Aleksey", null));

        Assert.assertEquals(expectedIssue, parser.parse(youtrackIssue).get(0));
    }

    @Test
    public void testParseWithAssigneeAsObject() {

        String youtrackIssue = "[{" +
                "\"summary\": \"Issue 2\"," +
                "\"assignee\": {\"name\": \"Aleksey\", \"position\": \"Intern\"}" +
                "}]";

        GeneralIssue expectedIssue = new GeneralIssue("Issue 2", new Assignee("Aleksey", "Intern"));

        Assert.assertEquals(expectedIssue, parser.parse(youtrackIssue).get(0));
    }

    @Test
    public void testParseWithMultipleIssues() {

        String youtrackIssues = "[{" +
                "\"summary\": \"Issue 1\"," +
                "\"assignee\": \"Ivan\"" +
                "}," +
                "{" +
                "\"summary\": \"Issue 2\"," +
                "\"assignee\": {\"name\": \"Petr\", \"position\": \"Developer\"}" +
                "}," +
                "{" +
                "\"summary\": \"Issue 3\"," +
                "\"assignee\": {\"name\": \"Aleksey\", \"position\": \"Intern\"}" +
                "}," +
                "{" +
                "\"summary\": \"Issue 4\"," +
                "\"assignee\": \"Andrey\"" +
                "}]";

        GeneralIssue firstExpectedIssue = new GeneralIssue("Issue 1", new Assignee("Ivan", null));
        GeneralIssue secondExpectedIssue = new GeneralIssue("Issue 2", new Assignee("Petr", "Developer"));
        GeneralIssue thirdExpectedIssue = new GeneralIssue("Issue 3", new Assignee("Aleksey", "Intern"));
        GeneralIssue fourthExpectedIssue = new GeneralIssue("Issue 4", new Assignee("Andrey", null));

        List<GeneralIssue> expectedIssues = new ArrayList<>();
        expectedIssues.add(firstExpectedIssue);
        expectedIssues.add(secondExpectedIssue);
        expectedIssues.add(thirdExpectedIssue);
        expectedIssues.add(fourthExpectedIssue);

        Assert.assertEquals(expectedIssues, parser.parse(youtrackIssues));
    }
}