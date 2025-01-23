import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList implements Serializable {
    private final ArrayList<Task> tasks;

    TaskList() {
        this(new ArrayList<>());
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    Task add(Task task) {
        tasks.add(task);
        return task;
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

    void print() {
        String out = IntStream.iterate(1, x -> x + 1)
                .limit(tasks.size())
                .mapToObj(i -> "%d. %s".formatted(i, tasks.get(i - 1)))
                .collect(Collectors.joining("\n"));
        System.out.println(out);
    }
}