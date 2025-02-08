package jank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Helper functions for tasks
 */
public class TaskImpl {

    static String formatDate(LocalDateTime dateTime) {
        var formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");
        return dateTime.format(formatter);
    }
}
