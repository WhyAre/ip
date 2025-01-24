package jank;

import java.time.LocalDateTime;

/**
 * Task with start and end date time
 */
public class EventTask extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    EventTask(String title, LocalDateTime from, LocalDateTime to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    static Task parse(String[] line) {
        var parts = TaskImpl.split(line, new String[]{"/from", "/to"});
        var from = TaskImpl.parseDate(parts.get("/from"));
        var to = TaskImpl.parseDate(parts.get("/to"));
        return new EventTask(parts.get(""), from, to);
    }

    @Override
    public String toString() {
        return "[E]%s (from: %s to: %s)".formatted(super.toString(), TaskImpl.formatDate(from),
                TaskImpl.formatDate(to));
    }


}
