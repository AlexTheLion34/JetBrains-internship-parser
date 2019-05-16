package project.parser.impl;

import project.model.Assignee;
import project.model.GeneralIssue;
import project.parser.MyParser;

import java.util.ArrayList;
import java.util.List;

public class CroogleParser implements MyParser {

    private final List<GeneralIssue> issueList = new ArrayList<>();

    @Override
    public List<GeneralIssue> parse(String toParse) {

        String[] issues = toParse.split("\n");
        List<String> summaries = new ArrayList<>();
        List<String> assignees = new ArrayList<>();

        for (String issue : issues) {
            String[] each = issue.split(" ", 2);
            if (each.length > 1) {
                if (each[0].equals("##")) {
                    summaries.add(each[1].trim());
                } else {
                    assignees.add(each[1].trim());
                }
            }
        }
        for (int i = 0; i < summaries.size(); i++) {
            issueList.add(new GeneralIssue(summaries.get(i), new Assignee(assignees.get(i), null)));
        }
        return issueList;
    }
}
