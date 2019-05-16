package project.parser.impl;

import org.junit.Assert;
import org.junit.Test;
import project.model.Assignee;
import project.model.GeneralIssue;

import java.util.ArrayList;
import java.util.List;

public class IRecordParserTest {

    private final IRecordParser parser = new IRecordParser();

    @Test
    public void testParseWithOneIssue() {

        String iRecordIssue = "<Issues>" +
                "<Issue> <summary> Issue 3 </summary> <assignee> Yaroslav </assignee> </Issue>" +
                "</Issues>";

        GeneralIssue expectedIssue = new GeneralIssue("Issue 3", new Assignee("Yaroslav", null));

        Assert.assertEquals(expectedIssue, parser.parse(iRecordIssue).get(0));
    }

    @Test
    public void testParseWithMultiplyIssues() {

        String iRecordIssues = "<Issues>" +
                "<Issue> <summary> Issue 3 </summary> <assignee> Yaroslav </assignee> </Issue>" +
                "<Issue> <summary> Issue 4 </summary> <assignee> Maksim </assignee> </Issue>" +
                "<Issue> <summary> Issue 5 </summary> <assignee> Oleg </assignee> </Issue>" +
                "</Issues>";

        GeneralIssue firstExpectedIssue = new GeneralIssue("Issue 3", new Assignee("Yaroslav", null));
        GeneralIssue secondExpectedIssue = new GeneralIssue("Issue 4", new Assignee("Maksim", null));
        GeneralIssue thirdExpectedIssue = new GeneralIssue("Issue 5", new Assignee("Oleg", null));

        List<GeneralIssue> expectedIssues = new ArrayList<>();
        expectedIssues.add(firstExpectedIssue);
        expectedIssues.add(secondExpectedIssue);
        expectedIssues.add(thirdExpectedIssue);

        Assert.assertEquals(expectedIssues, parser.parse(iRecordIssues));
    }
}