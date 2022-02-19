package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    private Repository repo = new Repository();

    private Issue i1 = new Issue(0, "Issue1", "BorisBritva", 28052022, new HashSet<>(Arrays.asList("Label 1", "Label 2", "Label 3")), new HashSet<>(Arrays.asList("Kirill", "Semen", "Volodya")), true);
    private Issue i2 = new Issue(1, "Issue 2", "Semen", 28052022, new HashSet<>(Collections.singletonList("Label 1")), new HashSet<>(Arrays.asList("Kirill", "Volodya")), true);
    private Issue i3 = new Issue(2, "Issue 3", "BorisBritva", 28052022, new HashSet<>(Arrays.asList("Label 2", "Label 5")), new HashSet<>(Arrays.asList("Volodya")), false);
    private Issue i4 = new Issue(3, "Issue 4", "Volodya", 28052022, new HashSet<>(Arrays.asList("Label 5")), new HashSet<>(Arrays.asList("Volodya")), true);
    private Issue i5 = new Issue(4, "Issue 5", "Volodya", 28052022, new HashSet<>(), new HashSet<>(), true);

    @BeforeEach
    void setUp() {
        repo.addNewIssue(i1);
        repo.addNewIssue(i2);
        repo.addNewIssue(i3);
        repo.addNewIssue(i4);
    }

    @Test
    void addNew() {
        repo.addNewIssue(i5);
        assertEquals(Arrays.asList(i1, i2, i3, i4, i5), repo.findAllIssues());
    }

    @Test
    void addToEmpty() {
        Repository repo = new Repository();
        repo.addNewIssue(i5);
        assertEquals(Arrays.asList(i5), repo.findAllIssues());
    }

}