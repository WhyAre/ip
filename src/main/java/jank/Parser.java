package jank;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

interface Command {
}

record DeleteCommand(int index) implements Command {
    static DeleteCommand parse(String[] line) throws JankBotException {
        Parser.checkHasArgs(line, "Usage: delete <index>");
        int index;
        try {
            index = Integer.parseInt(line[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JankBotException("Invalid index");
        }
        return new DeleteCommand(index);
    }
}

record MarkCommand(int index, boolean isMarked) implements Command {
    static MarkCommand parse(String[] line) throws JankBotException {
        boolean isMarked = line[0].equalsIgnoreCase("mark");
        Parser.checkHasArgs(line, "Usage: %s <index>".formatted((isMarked) ? "mark" : "unmark"));

        int index;
        try {
            index = Integer.parseInt(line[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JankBotException("Invalid index");
        }

        return new MarkCommand(index, isMarked);
    }
}

record TodoCommand(String desc) implements Command {
    static TodoCommand parse(String[] line) throws JankBotException {
        Parser.checkHasArgs(line, "Usage: todo <desc>");

        String desc = Arrays.stream(line).skip(1).collect(Collectors.joining(" "));

        return new TodoCommand(desc);
    }
}

record DeadlineCommand(String desc, LocalDateTime by) implements Command {
    static DeadlineCommand parse(String[] line) throws JankBotException {
        Parser.checkHasArgs(line, "Usage: deadline <desc> /by <date>");

        var flags = new String[]{"/by"};
        var parts = TaskImpl.parseFlags(Arrays.copyOfRange(line, 1, line.length), flags);

        LocalDateTime by = TaskImpl.parseDate(parts.get("/by"));

        return new DeadlineCommand(parts.get(""), by);
    }
}

record EventCommand(String desc, LocalDateTime from, LocalDateTime to) implements Command {
    static EventCommand parse(String[] line) throws JankBotException {
        Parser.checkHasArgs(line, "Usage: event <desc> /from <date> /to <date>");

        var flags = new String[]{"/from", "/to"};

        var parts = TaskImpl.parseFlags(Arrays.copyOfRange(line, 1, line.length), flags);

        var from = TaskImpl.parseDate(parts.get("/from"));
        var to = TaskImpl.parseDate(parts.get("/to"));

        return new EventCommand(parts.get(""), from, to);
    }
}

record FindCommand(String query) implements Command {
    static FindCommand parse(String[] line) throws JankBotException {
        Parser.checkHasArgs(line, "Usage: find <query>");

        String query = Arrays.stream(line).skip(1).collect(Collectors.joining(" "));
        return new FindCommand(query);
    }
}

/**
 * Handles the parsing of user input
 */
public class Parser {
    /**
     * Checks whether there is arguments in the cmd
     *
     * @param cmd command supplied
     * @param msg error message if the command has no arguments
     * @throws JankBotException
     */
    static void checkHasArgs(String[] cmd, String msg) throws JankBotException {
        if (cmd.length <= 1) {
            throw new JankBotException(msg);
        }
    }
}
