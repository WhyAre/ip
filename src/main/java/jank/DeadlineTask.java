package jank;

import java.time.LocalDateTime;

/**
 * Task with due date
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    DeadlineTask(String title, LocalDateTime deadline) {
        super(title);
        this.deadline = deadline;
    }

    static Task parse(String[] line) {
        var parts = TaskImpl.split(line, new String[]{"/by"});
        var dateTime = TaskImpl.parseDate(parts.get("/by"));
        return new DeadlineTask(parts.get(""), dateTime);
    }

    @Override
    public String toString() {
        return "[D]%s (by: %s)".formatted(super.toString(), TaskImpl.formatDate(deadline));
    }
}
