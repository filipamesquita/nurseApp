package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class ShiftAssignmentIdTest {

    @Test
    void test_constructor_validUuid_createsShiftAssignmentId() {
        // Arrange
        UUID value = UUID.randomUUID();

        // SUT
        ShiftAssignmentId shiftAssignmentId = new ShiftAssignmentId(value);

        // Assert
        assertEquals(value, shiftAssignmentId.value());
    }

    @Test
    void test_constructor_nullUuid_throwsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new ShiftAssignmentId(null));
    }

    @Test
    void test_generate_returnsNonNullShiftAssignmentId() {
        // SUT
        ShiftAssignmentId shiftAssignmentId = ShiftAssignmentId.generate();

        // Assert
        assertNotNull(shiftAssignmentId.value());
    }

    @Test
    void test_generate_returnsDistinctShiftAssignmentIds() {
        // SUT
        ShiftAssignmentId shiftAssignmentIdA = ShiftAssignmentId.generate();
        ShiftAssignmentId shiftAssignmentIdB = ShiftAssignmentId.generate();

        // Assert
        assertNotEquals(shiftAssignmentIdA, shiftAssignmentIdB);
    }

    @Test
    void test_equals_sameValue_returnsTrue() {
        // Arrange
        UUID value = UUID.randomUUID();
        ShiftAssignmentId shiftAssignmentIdA = new ShiftAssignmentId(value);
        ShiftAssignmentId shiftAssignmentIdB = new ShiftAssignmentId(value);

        // Assert
        assertEquals(shiftAssignmentIdA, shiftAssignmentIdB);
    }

    @Test
    void test_equals_differentValue_returnsFalse() {
        // Arrange
        ShiftAssignmentId shiftAssignmentIdA = new ShiftAssignmentId(UUID.randomUUID());
        ShiftAssignmentId shiftAssignmentIdB = new ShiftAssignmentId(UUID.randomUUID());

        // Assert
        assertNotEquals(shiftAssignmentIdA, shiftAssignmentIdB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        ShiftAssignmentId shiftAssignmentId = new ShiftAssignmentId(UUID.randomUUID());

        // Assert
        assertTrue(shiftAssignmentId.equals(shiftAssignmentId));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        ShiftAssignmentId shiftAssignmentId = new ShiftAssignmentId(UUID.randomUUID());
        String notAShiftAssignmentId = "not-a-shift-id";

        // Assert
        assertFalse(shiftAssignmentId.equals(notAShiftAssignmentId));
    }

    @Test
    void test_hashCode_sameValue_returnsSameHashCode() {
        // Arrange
        UUID value = UUID.randomUUID();
        ShiftAssignmentId shiftAssignmentIdA = new ShiftAssignmentId(value);
        ShiftAssignmentId shiftAssignmentIdB = new ShiftAssignmentId(value);

        // Assert
        assertEquals(shiftAssignmentIdA.hashCode(), shiftAssignmentIdB.hashCode());
    }
}
