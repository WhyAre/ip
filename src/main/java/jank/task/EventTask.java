package jank.task;

import java.time.LocalDateTime;

/**
 * Task with start and end date time
 */
public class EventTask extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventTask(String title, LocalDateTime from, LocalDateTime to) {
        super(title);
        this.from = from;
        this.to = to;
    }


    @Override
    public String toString() {
        return "[E]%s (from: %s to: %s)".formatted(super.toString(), TaskUtil.formatDate(from),
                TaskUtil.formatDate(to));
    }


}
