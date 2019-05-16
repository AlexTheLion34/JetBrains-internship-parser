package project.model;

import org.jetbrains.annotations.NotNull;

public class GeneralIssue {

    @NotNull
    private String summary;

    @NotNull
    private Assignee assignee;

    public GeneralIssue(@NotNull String summary, @NotNull Assignee assignee) {
        this.summary = summary;
        this.assignee = assignee;
    }

    @Override
    public String toString() {
        return "GeneralIssue{" +
                "summary='" + summary + '\'' +
                ", assignee=" + assignee +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralIssue issue = (GeneralIssue) o;
        return summary.equals(issue.summary) &&
                assignee.equals(issue.assignee);
    }
}
