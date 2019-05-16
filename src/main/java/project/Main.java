package project;

import project.model.GeneralIssue;
import project.parser.impl.CroogleParser;
import project.parser.impl.IRecordParser;
import project.parser.impl.YouTrackParser;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String youtrackIssues = "[{" +
                "\"summary\": \"Issue 1\"," +
                "\"assignee\": \"Ivan\"" +
                "}," +
                "{" +
                "\"summary\": \"Issue 2\"," +
                "\"assignee\": {\"name\": \"Petr\", \"position\": \"Developer\"}" +
                "}]";

        String iRecordIssues = "<Issues>" +
                "<Issue> <summary> Issue 3 </summary> <assignee> Yaroslav </assignee> </Issue>" +
                "</Issues>";

        String croogleIssues = "## Issue 4\n"+
                "Assignee: Oleg\n"+
                "\n" +
                "## Issue 5\n"+
                "Assignee: Ivan";


        List<GeneralIssue> issues = parseAllIssues(youtrackIssues, iRecordIssues, croogleIssues);

        issues.stream().forEach(System.out :: println);
    }

    private static List<GeneralIssue> parseAllIssues(String youtrackIssues, String iRecordIssues, String croogleIssues) {
        List<GeneralIssue> issueList = new ArrayList<>();
        issueList.addAll(new YouTrackParser().parse(youtrackIssues));
        issueList.addAll(new IRecordParser().parse(iRecordIssues));
        issueList.addAll(new CroogleParser().parse(croogleIssues));
        return issueList;
    }
}
