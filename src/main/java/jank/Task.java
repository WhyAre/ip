package jank;

import java.io.Serializable;

/**
 * Task superclass
 */
public abstract class Task implements Serializable {
    private final String title;
    private boolean isMarked;

    Task(String title) {
        this(title, false);
    }

    Task(String title, boolean isMarked) {
        this.title = title;
        this.isMarked = isMarked;
    }

    static Task parse(String[] line) {
        throw new UnsupportedOperationException();
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

    void setMark(boolean isMarked) {
        this.isMarked = isMarked;
    }

    private char getStatusIcon() {
        return (this.isMarked ? 'X' : ' ');
    }

    @Override
    public String toString() {
        return "[%s] %s".formatted(this.getStatusIcon(), this.title);
    }
}
