package jank.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    @Test
    public void isBeforeOrEqual() {
        var d1 = LocalDateTime.of(2025, 2, 14, 10, 31, 0);

        var deadlineTask = new DeadlineTask("desc", d1);

        assertTrue(deadlineTask.isBeforeOrEqual(d1));
        assertTrue(deadlineTask.isBeforeOrEqual(d1.plusSeconds(1)));
        assertFalse(deadlineTask.isBeforeOrEqual(d1.minusSeconds(1)));
    }
}
