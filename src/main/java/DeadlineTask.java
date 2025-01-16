public class DeadlineTask extends Task {
    String deadline;

    DeadlineTask(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    static Task parse(String[] line) {
        var parts = TaskImpl.split(line, new String[]{"/by"});
        return new DeadlineTask(parts.get(""), parts.get("/by"));
    }

    @Override
    public String toString() {
        return "[D]%s (by: %s)".formatted(super.toString(), deadline);
    }
}
