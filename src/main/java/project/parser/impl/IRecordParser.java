package project.parser.impl;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import project.model.Assignee;
import project.model.GeneralIssue;
import project.parser.MyParser;

import java.util.ArrayList;
import java.util.List;

public class IRecordParser implements MyParser {

    private final List<GeneralIssue> issueList = new ArrayList<>();

    @Override
    public List<GeneralIssue> parse(String toParse) {

        try {

            Document document = DocumentHelper.parseText(toParse);
            List<Node> nodes = document.selectNodes("/Issues/Issue");

            for (Node node : nodes) {
                issueList.add(new GeneralIssue(node.selectSingleNode("summary").getText().trim(),
                              new Assignee(node.selectSingleNode("assignee").getText().trim(), null)));
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return issueList;
    }
}
