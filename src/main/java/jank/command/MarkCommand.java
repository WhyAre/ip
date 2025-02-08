package jank.command;

import jank.JankBotException;

/**
 * Handles the mark and unmark commands
 *
 * @param index    index to mark or unmark
 * @param isMarked whether to mark or not
 */
public record MarkCommand(int index, boolean isMarked) implements Command {
    /**
     * Parses input line into MarkCommand
     *
     * @param line input line
     * @return MarkCommand
     * @throws JankBotException
     */
    public static MarkCommand parse(String[] line) throws JankBotException {
        boolean isMarked = line[0].equalsIgnoreCase("mark");
        CommandUtils.checkHasArgs(line, "Usage: %s <index>".formatted((isMarked) ? "mark" : "unmark"));

        int index;
        try {
            index = Integer.parseInt(line[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JankBotException("Invalid index");
        }

        return new MarkCommand(index, isMarked);
    }
}
