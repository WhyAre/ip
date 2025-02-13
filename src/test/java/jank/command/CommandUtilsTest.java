package jank.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jank.JankBotException;

public class CommandUtilsTest {
    @Test
    public void testParseFlags() {
        assertDoesNotThrow(() ->
                CommandUtils.parseFlags("front part /from 2025-01-25 1300 /to 2025-01-24 1400".split(" "),
                        new String[]{"/from", "/to"}));
        assertDoesNotThrow(() ->
                CommandUtils.parseFlags("front part /to 2025-01-25 1300 /from 2025-01-24 1400".split(" "),
                        new String[]{"/from", "/to"}));

        var err = assertThrows(JankBotException.class, () ->
                CommandUtils.parseFlags("front part /from 2025-01-25 1300".split(" "),
                        new String[]{"/from", "/to"}));
        assertEquals("Missing flags: /to", err.getMessage());

        err = assertThrows(JankBotException.class, () ->
                CommandUtils.parseFlags("front part".split(" "), new String[]{"/from", "/to"}));
        assertEquals("Missing flags: /from, /to", err.getMessage());
    }

    @Test
    public void testParseDate() {
        assertDoesNotThrow(() -> CommandUtils.parseDate("2025-01-25 1300"));
        var err = assertThrows(JankBotException.class, () -> CommandUtils.parseDate("2025-01-25 13:00"));
        assertTrue(err.getMessage().startsWith("Invalid date format."));

    }
}
