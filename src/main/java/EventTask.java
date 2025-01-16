public class EventTask extends Task {
    private final String from;
    private final String to;

    EventTask(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    static Task parse(String[] line) {
        var parts = TaskImpl.split(line, new String[]{"/from", "/to"});
        return new EventTask(parts.get(""), parts.get("/from"), parts.get("/to"));
    }

    @Override
    public String toString() {
        return "[E]%s (from: %s to: %s)".formatted(super.toString(), from, to);
    }


}
