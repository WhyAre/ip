import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {
    private final ArrayList<Task> tasks;

    TaskList() {
        this(new ArrayList<>());
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    void add(Task task) {
        tasks.add(task);
    }

    void mark(int index) {
        tasks.get(index).setMark(true);
    }

    void unmark(int index) {
        tasks.get(index).setMark(false);
    }

    void print() {
        String out = IntStream.iterate(1, x -> x + 1)
                .limit(tasks.size())
                .mapToObj(i -> "%d. %s".formatted(i, tasks.get(i - 1)))
                .collect(Collectors.joining("\n"));
        System.out.println(out);
    }
}