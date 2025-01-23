package jank;

public class TodoTask extends Task {
    TodoTask(String title) {
        super(title);
    }

    static Task parse(String[] line) {
        return new TodoTask(String.join(" ", line));
    }

    @Override
    public String toString() {
        return "[T]%s".formatted(super.toString());
    }
}
