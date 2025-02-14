package jank.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Helper functions for tasks
 */
public class TaskUtil {
    /**
     * Formats tasks into a printable format
     *
     * @param tasks list of tasks
     * @return string of formatted tasks
     */
    public static String tasksToPrintableFormat(List<Task> tasks) {
        return IntStream.iterate(1, x -> x + 1)
                        .limit(tasks.size())
                        .mapToObj(i -> "%d. %s".formatted(i, tasks.get(i - 1)))
                        .collect(Collectors.joining("\n"));
    }

    static String formatDate(LocalDateTime dateTime) {
        var formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");
        return dateTime.format(formatter);
    }
}
