package project.parser;

import project.model.GeneralIssue;

import java.util.List;

public interface MyParser {

    List<GeneralIssue> parse(String toParse);
}
