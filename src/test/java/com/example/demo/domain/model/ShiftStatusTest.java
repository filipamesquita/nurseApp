package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ShiftStatusTest {

    @Test
    void test_constructor_validState_createsShiftStatus() {
        // Arrange
        ShiftStatus.State state = ShiftStatus.State.PLANNED;

        // SUT
        ShiftStatus status = new ShiftStatus(state);

        // Assert
        assertEquals(state, status.state());
    }

    @Test
    void test_constructor_nullState_throwsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new ShiftStatus(null));
    }

    @Test
    void test_equals_sameState_returnsTrue() {
        // Arrange
        ShiftStatus statusA = new ShiftStatus(ShiftStatus.State.ACTIVE);
        ShiftStatus statusB = new ShiftStatus(ShiftStatus.State.ACTIVE);

        // Assert
        assertEquals(statusA, statusB);
    }

    @Test
    void test_equals_differentState_returnsFalse() {
        // Arrange
        ShiftStatus statusA = new ShiftStatus(ShiftStatus.State.PLANNED);
        ShiftStatus statusB = new ShiftStatus(ShiftStatus.State.CLOSED);

        // Assert
        assertNotEquals(statusA, statusB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        ShiftStatus status = new ShiftStatus(ShiftStatus.State.CLOSED);

        // Assert
        assertTrue(status.equals(status));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        ShiftStatus status = new ShiftStatus(ShiftStatus.State.CLOSED);
        String notAShiftStatus = "not-a-shift-status";

        // Assert
        assertFalse(status.equals(notAShiftStatus));
    }

    @Test
    void test_hashCode_sameState_returnsSameHashCode() {
        // Arrange
        ShiftStatus statusA = new ShiftStatus(ShiftStatus.State.PLANNED);
        ShiftStatus statusB = new ShiftStatus(ShiftStatus.State.PLANNED);

        // Assert
        assertEquals(statusA.hashCode(), statusB.hashCode());
    }
}
