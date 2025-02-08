package jank.command;

import java.util.Arrays;
import java.util.stream.Collectors;

import jank.JankBotException;


/**
 * Handles todo command
 *
 * @param desc description
 */
public record TodoCommand(String desc) implements Command {
    /**
     * Parses input line into TodoCommand
     *
     * @param line input
     * @return TodoCommand
     * @throws JankBotException
     */
    public static TodoCommand parse(String[] line) throws JankBotException {
        CommandUtils.checkHasArgs(line, "Usage: todo <desc>");

        String desc = Arrays.stream(line).skip(1).collect(Collectors.joining(" "));

        return new TodoCommand(desc);
    }
}
