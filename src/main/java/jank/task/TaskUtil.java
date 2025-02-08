package jank.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Helper functions for tasks
 */
class TaskUtil {
    static String formatDate(LocalDateTime dateTime) {
        var formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");
        return dateTime.format(formatter);
    }
}
