package jank.task;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Task superclass
 */
public class Task implements Serializable, Comparable<Task> {
    private final String title;
    private boolean isMarked;
    private LocalDateTime datetime;

    Task(String title) {
        this(title, null, false);
    }

    Task(String title, LocalDateTime datetime) {
        this(title, datetime, false);
    }

    Task(String title, LocalDateTime datetime, boolean isMarked) {
        this.title = title;
        this.datetime = datetime;
        this.isMarked = isMarked;
    }

    /**
     * Checks whether input is in the title
     *
     * @param query Input query
     * @return boolean whether the input appears in the title
     */
    boolean contains(String query) {
        return title.contains(query);
    }

    /**
     * Marks/Unmarks task
     *
     * @param isMarked whether or not to mark the task
     */
    void setMark(boolean isMarked) {
        this.isMarked = isMarked;
    }

    private char getStatusIcon() {
        return (this.isMarked ? 'X' : ' ');
    }

    @Override
    public int compareTo(Task task) {
        if (this.datetime == null) {
            return -1;
        }
        if (task.datetime == null) {
            return 1;
        }

        return this.datetime.compareTo(task.datetime);
    }

    @Override
    public String toString() {
        return "[%s] %s".formatted(this.getStatusIcon(), this.title);
    }
}
