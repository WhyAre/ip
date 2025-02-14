package jank.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that wraps a list of tasks
 */
public class TaskList implements Serializable {
    private final List<Task> tasks;

    public TaskList() {
        this(new ArrayList<>());
    }

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to tasklist
     *
     * @param task task to add
     * @return newly added task
     */
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Removes task at index
     *
     * @param index index to remove
     * @return task that was just deleted
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks task at specified index
     *
     * @param index index to mark
     * @return task that was marked
     */
    public Task mark(int index) {
        var task = tasks.get(index);
        task.setMark(true);
        return task;
    }

    /**
     * Unmarks a task at index
     *
     * @param index index to unmark
     * @return task that was unmarked
     */
    public Task unmark(int index) {
        var task = tasks.get(index);
        task.setMark(false);
        return task;
    }

    /**
     * Returns a list of task which has the query in the title
     *
     * @param query Query string
     * @return list of tasks
     */
    public List<Task> find(String query) {
        return tasks.stream()
                    .filter(task -> task.contains(query))
                    .toList();
    }

    /**
     * Lists all upcoming deadlines before date
     *
     * @param end maximum date of deadline
     * @return list of deadlines that's within the interval
     */
    public List<Task> remind(LocalDateTime end) {
        return tasks.stream()
                    .filter(DeadlineTask.class::isInstance)
                    .map(DeadlineTask.class::cast)
                    .filter(task -> task.isBeforeOrEqual(end))
                    .map(Task.class::cast)
                    .toList();
    }

    public String getAllTasks() {
        if (tasks.isEmpty()) {
            return "There are no tasks";
        }
        return TaskUtil.tasksToPrintableFormat(tasks);
    }

    public int size() {
        return tasks.size();
    }
}
