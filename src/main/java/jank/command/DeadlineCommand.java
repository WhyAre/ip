package jank.command;

import java.time.LocalDateTime;
import java.util.Arrays;

import jank.JankBotException;

/**
 * Describes deadline command
 *
 * @param desc description
 * @param by   due date
 */
public record DeadlineCommand(String desc, LocalDateTime by) implements Command {
    /**
     * Parses command string into Deadline Command
     *
     * @param line command string as an array
     * @return DeadlineCommand object with parameters parsed
     * @throws JankBotException
     */
    public static DeadlineCommand parse(String[] line) throws JankBotException {
        CommandUtils.checkHasArgs(line, "Usage: deadline <desc> /by <date>");

        var flags = new String[]{"/by"};
        var parts = CommandUtils.parseFlags(Arrays.copyOfRange(line, 1, line.length), flags);

        LocalDateTime by = CommandUtils.parseDate(parts.get("/by"));

        return new DeadlineCommand(parts.get(""), by);
    }
}
