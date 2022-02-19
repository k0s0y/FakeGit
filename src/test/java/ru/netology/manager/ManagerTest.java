package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.NotFoundException;
import ru.netology.repository.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    Repository repo = new Repository();
    Manager manager = new Manager(repo);

    private Issue i1 = new Issue(0, "Issue1", "Boris Britva", 2021_01_28, new HashSet<>(Arrays.asList("Label 1", "Label 2", "Label 3")), new HashSet<>(Arrays.asList("Kirill", "Semen", "Volodya")), true);
    private Issue i2 = new Issue(1, "Issue 2", "Semen", 2021_01_27, new HashSet<>(Collections.singletonList("Label 1")), new HashSet<>(Arrays.asList("Kirill", "Volodya")), true);
    private Issue i3 = new Issue(2, "Issue 3", "Boris Britva", 2021_01_28, new HashSet<>(Arrays.asList("Label 2", "Label 5")), new HashSet<>(Arrays.asList("Volodya")), false);
    private Issue i4 = new Issue(3, "Issue 4", "Volodya", 2021_01_30, new HashSet<>(Arrays.asList("Label 5")), new HashSet<>(Arrays.asList("Volodya")), true);
    private Issue i5 = new Issue(4, "Issue 5", "Volodya", 2021_02_28, new HashSet<>(), new HashSet<>(), true);

    @BeforeEach
    void setUp() {
        repo.addNewIssue(i1);
        repo.addNewIssue(i2);
        repo.addNewIssue(i3);
        repo.addNewIssue(i4);
    }

    @Test
    void add() {
        manager.add(i5);
        assertEquals(Arrays.asList(i1, i2, i3, i4, i5), manager.findAll());
    }

    @Test
    void findAll() {
        assertEquals(Arrays.asList(i1, i2, i3, i4), manager.findAll());
    }

    @Test
    void shouldFilterByOpened() {
        assertEquals(Arrays.asList(i1, i2, i4), manager.filterByStatus(true));
    }

    @Test
    void shouldFindOpen() {
        assertEquals(Arrays.asList(i1, i2, i4), manager.findOpened());
    }

    @Test
    void shouldFilterByClosed() {
        assertEquals(Arrays.asList(i3), manager.filterByStatus(false));
    }

    @Test
    void shouldFindByClosed() {
        assertEquals(Arrays.asList(i3), manager.findClosed());
    }

    @Test
    void shouldFindByAuthor() {
        assertEquals(Arrays.asList(i1, i3), manager.filterByAuthor("Boris Britva"));
    }

    @Test
    void shouldByLabel() {
        assertEquals(Arrays.asList(i3, i4), manager.filterByLabel("Label 5"));
    }

    @Test
    void shouldFindByAssign(){
        assertEquals(Arrays.asList(i1,i2), manager.filterByAssign("Kirill"));
    }

    @Test
    void shouldFindByDate(){
        assertEquals(Arrays.asList(i1,i3), manager.filterByDate(2021_01_28));
    }

    @Test
    void shouldSortByNewest(){
        assertEquals(Arrays.asList(i2, i1,i3,i4), manager.sortByNew());
    }

    @Test
    void shouldSortByOldest(){
        assertEquals(Arrays.asList(i4,i1,i3,i2), manager.sortByOld());
    }

    @Test
    void shouldCheckStatus(){
        assertTrue(manager.checkStatus(1));
    }
    @Test
    void shouldChangeStatusToClose(){
        manager.changeStatusById(0);
        assertFalse(manager.checkStatus(0));
    }
    @Test
    void shouldChangeStatusToOpen(){
        manager.changeStatusById(2);
        assertTrue(manager.checkStatus(2));
    }

    @Test
    void shouldGiveExceptionIfWrongId(){
        assertThrows(NotFoundException.class,()->{
            manager.checkStatus(9);
        });
    }
}
