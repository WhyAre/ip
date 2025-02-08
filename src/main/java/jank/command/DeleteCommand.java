package jank.command;

import jank.JankBotException;

/**
 * Handles delete command
 *
 * @param index index to delete
 */
public record DeleteCommand(int index) implements Command {
    public static DeleteCommand parse(String[] line) throws JankBotException {
        CommandUtils.checkHasArgs(line, "Usage: delete <index>");
        int index;
        try {
            index = Integer.parseInt(line[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JankBotException("Invalid index");
        }
        return new DeleteCommand(index);
    }
}
