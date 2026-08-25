package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class DevicePlacementIdTest {

    @Test
    void test_constructor_validUuid_createsDevicePlacementId() {
        // Arrange
        UUID value = UUID.randomUUID();

        // SUT
        DevicePlacementId devicePlacementId = new DevicePlacementId(value);

        // Assert
        assertEquals(value, devicePlacementId.value());
    }

    @Test
    void test_constructor_nullUuid_throwsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DevicePlacementId(null));
    }

    @Test
    void test_generate_returnsNonNullDevicePlacementId() {
        // SUT
        DevicePlacementId devicePlacementId = DevicePlacementId.generate();

        // Assert
        assertNotNull(devicePlacementId.value());
    }

    @Test
    void test_generate_returnsDistinctDevicePlacementIds() {
        // SUT
        DevicePlacementId devicePlacementIdA = DevicePlacementId.generate();
        DevicePlacementId devicePlacementIdB = DevicePlacementId.generate();

        // Assert
        assertNotEquals(devicePlacementIdA, devicePlacementIdB);
    }

    @Test
    void test_equals_sameValue_returnsTrue() {
        // Arrange
        UUID value = UUID.randomUUID();
        DevicePlacementId devicePlacementIdA = new DevicePlacementId(value);
        DevicePlacementId devicePlacementIdB = new DevicePlacementId(value);

        // Assert
        assertEquals(devicePlacementIdA, devicePlacementIdB);
    }

    @Test
    void test_equals_differentValue_returnsFalse() {
        // Arrange
        DevicePlacementId devicePlacementIdA = new DevicePlacementId(UUID.randomUUID());
        DevicePlacementId devicePlacementIdB = new DevicePlacementId(UUID.randomUUID());

        // Assert
        assertNotEquals(devicePlacementIdA, devicePlacementIdB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        DevicePlacementId devicePlacementId = new DevicePlacementId(UUID.randomUUID());

        // Assert
        assertTrue(devicePlacementId.equals(devicePlacementId));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        DevicePlacementId devicePlacementId = new DevicePlacementId(UUID.randomUUID());
        String notADevicePlacementId = "not-a-device-placement-id";

        // Assert
        assertFalse(devicePlacementId.equals(notADevicePlacementId));
    }

    @Test
    void test_hashCode_sameValue_returnsSameHashCode() {
        // Arrange
        UUID value = UUID.randomUUID();
        DevicePlacementId devicePlacementIdA = new DevicePlacementId(value);
        DevicePlacementId devicePlacementIdB = new DevicePlacementId(value);

        // Assert
        assertEquals(devicePlacementIdA.hashCode(), devicePlacementIdB.hashCode());
    }
}
