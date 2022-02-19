package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Issue {
    private int id;
    private String name;
    private String author;
    private int date;
    private Set<String> label = new HashSet<>();
    private Set<String> assignee = new HashSet<>();
    private boolean opened;

    public void addLabel(String labels) {
        label.add(labels);
    }

    public void addAssignee(String assignee) {
        label.add(assignee);
    }
}
