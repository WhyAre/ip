import java.util.ArrayList;

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
}