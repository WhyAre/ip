package jank.command;

import java.time.LocalDateTime;
import java.util.Arrays;

import jank.JankBotException;

/**
 * Handles event commands
 *
 * @param desc description
 * @param from start date and time of event
 * @param to   end date and time of event
 */
public record EventCommand(String desc, LocalDateTime from, LocalDateTime to) implements Command {
    public static EventCommand parse(String[] line) throws JankBotException {
        CommandUtils.checkHasArgs(line, "Usage: event <desc> /from <date> /to <date>");

        var flags = new String[]{"/from", "/to"};

        var parts = CommandUtils.parseFlags(Arrays.copyOfRange(line, 1, line.length), flags);

        var from = CommandUtils.parseDate(parts.get("/from"));
        var to = CommandUtils.parseDate(parts.get("/to"));

        return new EventCommand(parts.get(""), from, to);
    }
}
