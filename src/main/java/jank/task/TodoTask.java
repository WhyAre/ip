package jank.task;

/**
 * Task with only a title
 */
public class TodoTask extends Task {
    public TodoTask(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]%s".formatted(super.toString());
    }
}
