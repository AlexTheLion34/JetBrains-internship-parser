package project.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Assignee {

    @NotNull
    private String name;

    @Nullable
    private String position;

    public Assignee(@NotNull String name, @Nullable String position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Assignee{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignee assignee = (Assignee) o;
        return name.equals(assignee.name) &&
                Objects.equals(position, assignee.position);
    }
}
