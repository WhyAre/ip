package jank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TaskImpl {
    static Map<String, String> split(String[] line, String[] delims) {
        var map = Arrays.stream(delims)
                .collect(Collectors.toMap(Function.identity(), x -> new StringBuffer(),
                        (left, right) -> right,
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

        return map.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> x.getValue().toString().strip()));
    }

    static LocalDateTime parseDate(String dateStr) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateStr, formatter);
    }

    static String formatDate(LocalDateTime dateTime) {
        var formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");
        return dateTime.format(formatter);
    }
}
