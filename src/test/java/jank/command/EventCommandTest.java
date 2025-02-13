package jank.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class EventCommandTest {
    @Test
    public void testEventCommand() {
        assertDoesNotThrow(() -> EventCommand.parse(
                "event this is an event /from 2025-01-25 1538 /to 2025-01-25 1600".split(" ")));
    }
}
