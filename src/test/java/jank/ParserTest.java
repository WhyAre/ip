package jank;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jank.command.DeadlineCommand;
import jank.command.EventCommand;
import jank.task.TaskUtil;

public class ParserTest {
    @Test
    public void testDeadlineCommand() {
        assertDoesNotThrow(() -> DeadlineCommand.parse("deadline this is a deadline /by 2025-01-25 1538".split(" ")));
    }

    @Test
    public void testEventCommand() {
        assertDoesNotThrow(() -> EventCommand.parse(
                "event this is an event /from 2025-01-25 1538 /to 2025-01-25 1600".split(" ")));
    }

    @Test
    public void testParseFlags() {
        assertDoesNotThrow(() -> TaskUtil.parseFlags("front part /from 2025-01-25 1300 /to 2025-01-24 1400".split(" "),
                new String[]{"/from", "/to"}));
        assertDoesNotThrow(() -> TaskUtil.parseFlags("front part /to 2025-01-25 1300 /from 2025-01-24 1400".split(" "),
                new String[]{"/from", "/to"}));

        var err = assertThrows(JankBotException.class, () ->
                TaskUtil.parseFlags("front part /from 2025-01-25 1300".split(" "), new String[]{"/from", "/to"}));
        assertEquals("Missing flags: /to", err.getMessage());
        err = assertThrows(JankBotException.class, () ->
                TaskUtil.parseFlags("front part".split(" "), new String[]{"/from", "/to"}));
        assertEquals("Missing flags: /from, /to", err.getMessage());
    }

    @Test
    public void testParseDate() {
        assertDoesNotThrow(() -> TaskUtil.parseDate("2025-01-25 1300"));
        var err = assertThrows(JankBotException.class, () -> TaskUtil.parseDate("2025-01-25 13:00"));
        assertTrue(err.getMessage().startsWith("Invalid date format."));

    }
}
