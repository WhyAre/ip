package jank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Helper functions for tasks
 */
public class TaskImpl {
    /**
     * Separate parts of the input string by flags
     *
     * @param line  Input line
     * @param flags array of mandatory flags
     * @return map of flag to the value associated to the flag
     * @throws JankBotException Throws an error when not all the flags are present in the input string
     */
    static Map<String, String> parseFlags(String[] line, String[] flags) throws JankBotException {
        var map = Arrays.stream(flags)
                        .collect(Collectors.toMap(Function.identity(), x -> new StringBuffer(), (left, right) -> right,
                                HashMap<String, StringBuffer>::new));
        map.put("", new StringBuffer());

        var curKey = "";
        for (var word : line) {
            if (map.containsKey(word)) {
                curKey = word;
            } else {
                map.computeIfPresent(curKey, (k, val) -> val.append(word).append(" "));
            }
        }

        var mapping = map.entrySet()
                         .stream()
                         .collect(Collectors.toMap(Map.Entry::getKey, x -> x.getValue().toString().strip()));

        var missingFlags = Arrays.stream(flags)
                                 .filter(f -> !mapping.containsKey(f) || mapping.get(f).isEmpty())
                                 .collect(Collectors.joining(", "));
        if (!missingFlags.isEmpty()) {
            throw new JankBotException("Missing flags: %s".formatted(missingFlags));
        }

        return mapping;
    }

    /**
     * Converts date string into LocalDateTime object
     *
     * @param dateStr Date as a string
     * @return LocalDateTime Object
     * @throws JankBotException Throws if date format is wrong
     */
    static LocalDateTime parseDate(String dateStr) throws JankBotException {
        var expectedFormat = "yyyy-MM-dd HHmm";
        var formatter = DateTimeFormatter.ofPattern(expectedFormat);
        try {
            return LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new JankBotException("Invalid date format. Required format is %s".formatted(expectedFormat));
        }
    }

    static String formatDate(LocalDateTime dateTime) {
        var formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");
        return dateTime.format(formatter);
    }
}
