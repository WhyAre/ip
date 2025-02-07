package jank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class that wraps a list of tasks
 */
public class TaskList implements Serializable {
    private final List<Task> tasks;

    TaskList() {
        this(new ArrayList<>());
    }

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    Task add(Task task) {
        tasks.add(task);
        return task;
    }

    Task remove(int index) {
        return tasks.remove(index);
    }

    Task mark(int index) {
        var task = tasks.get(index);
        task.setMark(true);
        return task;
    }

    Task unmark(int index) {
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
    List<Task> find(String query) {
        return tasks.stream()
                    .filter(task -> task.contains(query))
                    .toList();
    }

    String getAllTasks() {
        if (tasks.isEmpty()) {
            return "There are no tasks";
        }
        return IntStream.iterate(1, x -> x + 1)
                        .limit(tasks.size())
                        .mapToObj(i -> "%d. %s".formatted(i, tasks.get(i - 1)))
                        .collect(Collectors.joining("\n"));
    }

    int size() {
        return tasks.size();
    }
}
