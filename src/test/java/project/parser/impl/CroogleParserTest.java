package project.parser.impl;

import org.junit.Assert;
import org.junit.Test;
import project.model.Assignee;
import project.model.GeneralIssue;

import java.util.ArrayList;
import java.util.List;

public class CroogleParserTest {

    private final CroogleParser parser = new CroogleParser();

    @Test
    public void testParseWithOneIssue() {

        String croogleIssue =
                "## Issue 5\n"+
                "Assignee: Ivan";

        GeneralIssue expectedIssue = new GeneralIssue("Issue 5", new Assignee("Ivan", null));

        Assert.assertEquals(expectedIssue, parser.parse(croogleIssue).get(0));
    }

    @Test
    public void testParseWithMultipleIssue() {

        String croogleIssues = "## Issue 6\n"+
                "Assignee: Matvey\n"+
                "\n" +
                "## Issue 7\n"+
                "Assignee: Kirill\n+" +
                "\n" +
                "## Issue 8\n" +
                "Assignee: Nikita";

        GeneralIssue firstExpectedIssue = new GeneralIssue("Issue 6", new Assignee("Matvey", null));
        GeneralIssue secondExpectedIssue = new GeneralIssue("Issue 7", new Assignee("Kirill", null));
        GeneralIssue thirdExpectedIssue = new GeneralIssue("Issue 8", new Assignee("Nikita", null));

        List<GeneralIssue> expectedIssues = new ArrayList<>();
        expectedIssues.add(firstExpectedIssue);
        expectedIssues.add(secondExpectedIssue);
        expectedIssues.add(thirdExpectedIssue);

        Assert.assertEquals(expectedIssues, parser.parse(croogleIssues));
    }
}