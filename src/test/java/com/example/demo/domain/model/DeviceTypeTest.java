package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DeviceTypeTest {

    @Test
    void test_constructor_validLabel_createsDeviceType() {
        // Arrange
        String label = "Infusion Pump";

        // SUT
        DeviceType deviceType = new DeviceType(label);

        // Assert
        assertEquals(label, deviceType.label());
    }

    @Test
    void test_constructor_nullLabel_throwsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DeviceType(null));
    }

    @Test
    void test_constructor_emptyLabel_throwsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DeviceType(""));
    }

    @Test
    void test_constructor_blankLabel_throwsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DeviceType("   "));
    }

    @Test
    void test_equals_sameLabel_returnsTrue() {
        // Arrange
        DeviceType deviceTypeA = new DeviceType("Ventilator");
        DeviceType deviceTypeB = new DeviceType("Ventilator");

        // Assert
        assertEquals(deviceTypeA, deviceTypeB);
    }

    @Test
    void test_equals_differentLabel_returnsFalse() {
        // Arrange
        DeviceType deviceTypeA = new DeviceType("Ventilator");
        DeviceType deviceTypeB = new DeviceType("Infusion Pump");

        // Assert
        assertNotEquals(deviceTypeA, deviceTypeB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        DeviceType deviceType = new DeviceType("Ventilator");

        // Assert
        assertTrue(deviceType.equals(deviceType));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        DeviceType deviceType = new DeviceType("Ventilator");
        String notADeviceType = "Ventilator";

        // Assert
        assertFalse(deviceType.equals(notADeviceType));
    }

    @Test
    void test_hashCode_sameLabel_returnsSameHashCode() {
        // Arrange
        DeviceType deviceTypeA = new DeviceType("Ventilator");
        DeviceType deviceTypeB = new DeviceType("Ventilator");

        // Assert
        assertEquals(deviceTypeA.hashCode(), deviceTypeB.hashCode());
    }
}
