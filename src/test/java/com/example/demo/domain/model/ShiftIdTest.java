package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class ShiftIdTest {

    @Test
    void test_constructor_validUuid_createsShiftId() {
        // Arrange
        UUID value = UUID.randomUUID();

        // SUT
        ShiftId shiftId = new ShiftId(value);

        // Assert
        assertEquals(value, shiftId.value());
    }

    @Test
    void test_constructor_nullUuid_throwsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new ShiftId(null));
    }

    @Test
    void test_generate_returnsNonNullShiftId() {
        // SUT
        ShiftId shiftId = ShiftId.generate();

        // Assert
        assertNotNull(shiftId.value());
    }

    @Test
    void test_generate_returnsDistinctShiftIds() {
        // SUT
        ShiftId shiftIdA = ShiftId.generate();
        ShiftId shiftIdB = ShiftId.generate();

        // Assert
        assertNotEquals(shiftIdA, shiftIdB);
    }

    @Test
    void test_equals_sameValue_returnsTrue() {
        // Arrange
        UUID value = UUID.randomUUID();
        ShiftId shiftIdA = new ShiftId(value);
        ShiftId shiftIdB = new ShiftId(value);

        // Assert
        assertEquals(shiftIdA, shiftIdB);
    }

    @Test
    void test_equals_differentValue_returnsFalse() {
        // Arrange
        ShiftId shiftIdA = new ShiftId(UUID.randomUUID());
        ShiftId shiftIdB = new ShiftId(UUID.randomUUID());

        // Assert
        assertNotEquals(shiftIdA, shiftIdB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        ShiftId shiftId = new ShiftId(UUID.randomUUID());

        // Assert
        assertTrue(shiftId.equals(shiftId));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        ShiftId shiftId = new ShiftId(UUID.randomUUID());
        String notAShiftId = "not-a-shift-id";

        // Assert
        assertFalse(shiftId.equals(notAShiftId));
    }

    @Test
    void test_hashCode_sameValue_returnsSameHashCode() {
        // Arrange
        UUID value = UUID.randomUUID();
        ShiftId shiftIdA = new ShiftId(value);
        ShiftId shiftIdB = new ShiftId(value);

        // Assert
        assertEquals(shiftIdA.hashCode(), shiftIdB.hashCode());
    }
}
