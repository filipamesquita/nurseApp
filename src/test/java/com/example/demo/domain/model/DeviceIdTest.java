package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class DeviceIdTest {

    @Test
    void test_constructor_validUuid_createsDeviceId() {
        // Arrange
        UUID value = UUID.randomUUID();

        // SUT
        DeviceId deviceId = new DeviceId(value);

        // Assert
        assertEquals(value, deviceId.value());
    }

    @Test
    void test_constructor_nullUuid_throwsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DeviceId(null));
    }

    @Test
    void test_generate_returnsNonNullDeviceId() {
        // SUT
        DeviceId deviceId = DeviceId.generate();

        // Assert
        assertNotNull(deviceId.value());
    }

    @Test
    void test_generate_returnsDistinctDeviceIds() {
        // SUT
        DeviceId deviceIdA = DeviceId.generate();
        DeviceId deviceIdB = DeviceId.generate();

        // Assert
        assertNotEquals(deviceIdA, deviceIdB);
    }

    @Test
    void test_equals_sameValue_returnsTrue() {
        // Arrange
        UUID value = UUID.randomUUID();
        DeviceId deviceIdA = new DeviceId(value);
        DeviceId deviceIdB = new DeviceId(value);

        // Assert
        assertEquals(deviceIdA, deviceIdB);
    }

    @Test
    void test_equals_differentValue_returnsFalse() {
        // Arrange
        DeviceId deviceIdA = new DeviceId(UUID.randomUUID());
        DeviceId deviceIdB = new DeviceId(UUID.randomUUID());

        // Assert
        assertNotEquals(deviceIdA, deviceIdB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        DeviceId deviceId = new DeviceId(UUID.randomUUID());

        // Assert
        assertTrue(deviceId.equals(deviceId));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        DeviceId deviceId = new DeviceId(UUID.randomUUID());
        String notADeviceId = "not-a-device-id";

        // Assert
        assertFalse(deviceId.equals(notADeviceId));
    }

    @Test
    void test_hashCode_sameValue_returnsSameHashCode() {
        // Arrange
        UUID value = UUID.randomUUID();
        DeviceId deviceIdA = new DeviceId(value);
        DeviceId deviceIdB = new DeviceId(value);

        // Assert
        assertEquals(deviceIdA.hashCode(), deviceIdB.hashCode());
    }
}
