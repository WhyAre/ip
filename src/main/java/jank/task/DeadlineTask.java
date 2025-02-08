package jank.task;

import java.time.LocalDateTime;

/**
 * Task with due date
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    public DeadlineTask(String title, LocalDateTime deadline) {
        super(title);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]%s (by: %s)".formatted(super.toString(), TaskUtil.formatDate(deadline));
    }
}
