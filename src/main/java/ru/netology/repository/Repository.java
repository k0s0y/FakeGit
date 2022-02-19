package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<Issue> issues = new ArrayList<>();

    public void addNewIssue(Issue issue) {
        issues.add(issue);
    }

    public List<Issue> findAllIssues() {
        return issues;
    }
}
