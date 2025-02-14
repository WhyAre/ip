package jank.command;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import jank.JankBotException;

/**
 * Represents a RemindCommand
 *
 * @param end Max date of deadline to show
 */
public record RemindCommand(LocalDateTime end) implements Command {
    /**
     * Parses command into RemindCommand
     *
     * @param input user input
     * @return RemindCommand object
     * @throws JankBotException
     */
    public static RemindCommand parse(String[] input) throws JankBotException {
        var durationString = Arrays.stream(input).skip(1).collect(Collectors.joining(" "));

        if (durationString.isEmpty()) {
            throw new JankBotException("Usage: remind <duration>");
        }

        var duration = CommandUtils.parseDuration(durationString);

        var now = LocalDateTime.now();

        return new RemindCommand(now.plus(duration));
    }
}
