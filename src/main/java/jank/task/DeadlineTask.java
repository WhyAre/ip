package jank.task;

import java.time.LocalDateTime;

/**
 * Task with due date
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a new DeadlineTask
     *
     * @param desc     description
     * @param deadline deadline of the task
     */
    public DeadlineTask(String desc, LocalDateTime deadline) {
        super(desc, deadline);
        this.deadline = deadline;
    }

    boolean isBeforeOrEqual(LocalDateTime end) {
        return !deadline.isAfter(end);
    }

    @Override
    public String toString() {
        return "[D]%s (by: %s)".formatted(super.toString(), TaskUtil.formatDate(deadline));
    }
}
