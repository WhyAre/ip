package jank.task;

import java.time.LocalDateTime;

/**
 * Task with start and end date time
 */
public class EventTask extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs new EventTask
     *
     * @param desc description
     * @param from start date and time of event
     * @param to   end date and time of event
     */
    public EventTask(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.from = from;
        this.to = to;
    }


    @Override
    public String toString() {
        return "[E]%s (from: %s to: %s)".formatted(super.toString(), TaskUtil.formatDate(from),
                TaskUtil.formatDate(to));
    }


}
