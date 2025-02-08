package jank.command;

import java.util.Arrays;
import java.util.stream.Collectors;

import jank.JankBotException;

/**
 * Handles find command
 *
 * @param query string to search for
 */
public record FindCommand(String query) implements Command {
    /**
     * Parses input line into FindCommand
     *
     * @param line input line
     * @return FindCommand
     * @throws JankBotException
     */
    public static FindCommand parse(String[] line) throws JankBotException {
        CommandUtils.checkHasArgs(line, "Usage: find <query>");

        String query = Arrays.stream(line).skip(1).collect(Collectors.joining(" "));
        return new FindCommand(query);
    }
}
