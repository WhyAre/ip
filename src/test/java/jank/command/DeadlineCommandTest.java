package jank.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class DeadlineCommandTest {
    @Test
    public void testParse() {
        assertDoesNotThrow(() -> DeadlineCommand.parse("deadline this is a deadline /by 2025-01-25 1538".split(" ")));
    }
}
