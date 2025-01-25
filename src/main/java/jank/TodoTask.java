package jank;

/**
 * Task with only a title
 */
public class TodoTask extends Task {
    TodoTask(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]%s".formatted(super.toString());
    }
}
