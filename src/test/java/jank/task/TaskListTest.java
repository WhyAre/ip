package jank.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


public class TaskListTest {
    @Test
    public void testAddTask() {
        var tasklist = new TaskList();
        var task = new TodoTask("title");
        tasklist.add(task);

        assertEquals(1, tasklist.size(), "Check that size is 1 after insertion");
        assertEquals(task, tasklist.remove(0), "Check that task is the same");
    }

    @Test
    public void testRemoveTask() {
        var task1 = new TodoTask("task 1");
        var task2 = new TodoTask("task 2");
        var tasklist = new TaskList(new ArrayList<>(List.of(task1, task2)));

        assertThrows(IndexOutOfBoundsException.class, () -> tasklist.remove(-1),
                "Remove task with negative index");
        assertThrows(IndexOutOfBoundsException.class, () -> tasklist.remove(2),
                "Remove tasks with out-of-bound index");
        assertEquals(task1, tasklist.remove(0), "Remove first task");
        assertEquals(task2, tasklist.remove(0), "Remove second task");

        var tasklist1 = new TaskList(new ArrayList<>(List.of(task1, task2)));
        assertEquals(task2, tasklist1.remove(1), "Remove second task");
    }

    @Test
    public void testMarkTask() {
        var task1 = new TodoTask("task 1");
        var task2 = new TodoTask("task 2");
        var tasklist = new TaskList(new ArrayList<>(List.of(task1, task2)));

        tasklist.mark(1);
    }

    @Test
    public void remind() {
        var date = LocalDateTime.of(2025, 2, 14, 10, 0, 0);
        var tasks = new ArrayList<Task>();
        tasks.add(new DeadlineTask("deadline1", date));
        tasks.add(new DeadlineTask("deadline2", date.plusHours(1)));
        tasks.add(new TodoTask("todo1"));
        tasks.add(new EventTask("event1", date, date.plusHours(1)));

        var taskList = new TaskList(tasks);

        taskList.remind(date.plusHours(1)).forEach(task -> {
            var deadlineTask = assertInstanceOf(DeadlineTask.class, task);

            assertTrue(deadlineTask.isBeforeOrEqual(date.plusHours(1)));
        });
    }
}
