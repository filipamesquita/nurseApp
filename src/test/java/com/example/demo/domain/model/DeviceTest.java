package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DeviceTest {

    @Test
    void test_create_validArguments_createsDevice() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        String name = "Infusion Pump #1";
        DeviceType deviceType = new DeviceType("Infusion Pump");
        Periodicity periodicity = new Periodicity(30);
        LocalDate lastReplacedAt = LocalDate.of(2026, 1, 1);

        // SUT
        Device device =
                DeviceFactory.create(deviceId, name, deviceType, periodicity, lastReplacedAt);

        // Assert
        assertEquals(deviceId, device.identity());
    }

    @Test
    void test_create_nullDeviceId_throwsException() {
        // Arrange
        String name = "Infusion Pump #1";
        DeviceType deviceType = new DeviceType("Infusion Pump");
        Periodicity periodicity = new Periodicity(30);
        LocalDate lastReplacedAt = LocalDate.of(2026, 1, 1);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> DeviceFactory.create(null, name, deviceType, periodicity, lastReplacedAt));
    }

    @Test
    void test_create_nullName_throwsException() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        DeviceType deviceType = new DeviceType("Infusion Pump");
        Periodicity periodicity = new Periodicity(30);
        LocalDate lastReplacedAt = LocalDate.of(2026, 1, 1);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        DeviceFactory.create(
                                deviceId, null, deviceType, periodicity, lastReplacedAt));
    }

    @Test
    void test_create_blankName_throwsException() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        DeviceType deviceType = new DeviceType("Infusion Pump");
        Periodicity periodicity = new Periodicity(30);
        LocalDate lastReplacedAt = LocalDate.of(2026, 1, 1);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        DeviceFactory.create(
                                deviceId, "   ", deviceType, periodicity, lastReplacedAt));
    }

    @Test
    void test_create_nullDeviceType_throwsException() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        String name = "Infusion Pump #1";
        Periodicity periodicity = new Periodicity(30);
        LocalDate lastReplacedAt = LocalDate.of(2026, 1, 1);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> DeviceFactory.create(deviceId, name, null, periodicity, lastReplacedAt));
    }

    @Test
    void test_create_nullPeriodicity_throwsException() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        String name = "Infusion Pump #1";
        DeviceType deviceType = new DeviceType("Infusion Pump");
        LocalDate lastReplacedAt = LocalDate.of(2026, 1, 1);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> DeviceFactory.create(deviceId, name, deviceType, null, lastReplacedAt));
    }

    @Test
    void test_create_nullLastReplacedAt_throwsException() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        String name = "Infusion Pump #1";
        DeviceType deviceType = new DeviceType("Infusion Pump");
        Periodicity periodicity = new Periodicity(30);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> DeviceFactory.create(deviceId, name, deviceType, periodicity, null));
    }

    @Test
    void test_identity_returnsDeviceId() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        Device device =
                DeviceFactory.create(
                        deviceId,
                        "Infusion Pump #1",
                        new DeviceType("Infusion Pump"),
                        new Periodicity(30),
                        LocalDate.of(2026, 1, 1));

        // SUT
        DeviceId identity = device.identity();

        // Assert
        assertEquals(deviceId, identity);
    }

    @Test
    void test_equals_sameDeviceId_returnsTrue_evenWithDifferentValues() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        Device deviceA =
                DeviceFactory.create(
                        deviceId,
                        "Infusion Pump #1",
                        new DeviceType("Infusion Pump"),
                        new Periodicity(30),
                        LocalDate.of(2026, 1, 1));
        Device deviceB =
                DeviceFactory.create(
                        deviceId,
                        "Infusion Pump #2 (renamed)",
                        new DeviceType("Ventilator"),
                        new Periodicity(60),
                        LocalDate.of(2026, 2, 1));

        // Assert
        assertEquals(deviceA, deviceB);
    }

    @Test
    void test_equals_differentDeviceId_returnsFalse() {
        // Arrange
        Device deviceA =
                DeviceFactory.create(
                        DeviceId.generate(),
                        "Infusion Pump #1",
                        new DeviceType("Infusion Pump"),
                        new Periodicity(30),
                        LocalDate.of(2026, 1, 1));
        Device deviceB =
                DeviceFactory.create(
                        DeviceId.generate(),
                        "Infusion Pump #1",
                        new DeviceType("Infusion Pump"),
                        new Periodicity(30),
                        LocalDate.of(2026, 1, 1));

        // Assert
        assertNotEquals(deviceA, deviceB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        Device device =
                DeviceFactory.create(
                        DeviceId.generate(),
                        "Infusion Pump #1",
                        new DeviceType("Infusion Pump"),
                        new Periodicity(30),
                        LocalDate.of(2026, 1, 1));

        // Assert
        assertTrue(device.equals(device));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        Device device =
                DeviceFactory.create(
                        DeviceId.generate(),
                        "Infusion Pump #1",
                        new DeviceType("Infusion Pump"),
                        new Periodicity(30),
                        LocalDate.of(2026, 1, 1));
        String notADevice = "Infusion Pump #1";

        // Assert
        assertFalse(device.equals(notADevice));
    }

    @Test
    void test_hashCode_sameDeviceId_returnsSameHashCode() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        Device deviceA =
                DeviceFactory.create(
                        deviceId,
                        "Infusion Pump #1",
                        new DeviceType("Infusion Pump"),
                        new Periodicity(30),
                        LocalDate.of(2026, 1, 1));
        Device deviceB =
                DeviceFactory.create(
                        deviceId,
                        "Infusion Pump #2",
                        new DeviceType("Ventilator"),
                        new Periodicity(60),
                        LocalDate.of(2026, 2, 1));

        // Assert
        assertEquals(deviceA.hashCode(), deviceB.hashCode());
    }

    @Test
    void test_sameAs_sameValues_returnsTrue() {
        // Arrange
        String name = "Infusion Pump #1";
        DeviceType deviceType = new DeviceType("Infusion Pump");
        Periodicity periodicity = new Periodicity(30);
        LocalDate lastReplacedAt = LocalDate.of(2026, 1, 1);
        Device deviceA =
                DeviceFactory.create(
                        DeviceId.generate(), name, deviceType, periodicity, lastReplacedAt);
        Device deviceB =
                DeviceFactory.create(
                        DeviceId.generate(), name, deviceType, periodicity, lastReplacedAt);

        // SUT
        boolean result = deviceA.sameAs(deviceB);

        // Assert
        assertTrue(result);
    }

    @Test
    void test_sameAs_differentName_returnsFalse() {
        // Arrange
        DeviceType deviceType = new DeviceType("Infusion Pump");
        Periodicity periodicity = new Periodicity(30);
        LocalDate lastReplacedAt = LocalDate.of(2026, 1, 1);
        Device deviceA =
                DeviceFactory.create(
                        DeviceId.generate(),
                        "Infusion Pump #1",
                        deviceType,
                        periodicity,
                        lastReplacedAt);
        Device deviceB =
                DeviceFactory.create(
                        DeviceId.generate(),
                        "Infusion Pump #2",
                        deviceType,
                        periodicity,
                        lastReplacedAt);

        // SUT
        boolean result = deviceA.sameAs(deviceB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentDeviceType_returnsFalse() {
        // Arrange
        String name = "Infusion Pump #1";
        Periodicity periodicity = new Periodicity(30);
        LocalDate lastReplacedAt = LocalDate.of(2026, 1, 1);
        Device deviceA =
                DeviceFactory.create(
                        DeviceId.generate(),
                        name,
                        new DeviceType("Infusion Pump"),
                        periodicity,
                        lastReplacedAt);
        Device deviceB =
                DeviceFactory.create(
                        DeviceId.generate(),
                        name,
                        new DeviceType("Ventilator"),
                        periodicity,
                        lastReplacedAt);

        // SUT
        boolean result = deviceA.sameAs(deviceB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentPeriodicity_returnsFalse() {
        // Arrange
        String name = "Infusion Pump #1";
        DeviceType deviceType = new DeviceType("Infusion Pump");
        LocalDate lastReplacedAt = LocalDate.of(2026, 1, 1);
        Device deviceA =
                DeviceFactory.create(
                        DeviceId.generate(), name, deviceType, new Periodicity(30), lastReplacedAt);
        Device deviceB =
                DeviceFactory.create(
                        DeviceId.generate(), name, deviceType, new Periodicity(60), lastReplacedAt);

        // SUT
        boolean result = deviceA.sameAs(deviceB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentLastReplacedAt_returnsFalse() {
        // Arrange
        String name = "Infusion Pump #1";
        DeviceType deviceType = new DeviceType("Infusion Pump");
        Periodicity periodicity = new Periodicity(30);
        Device deviceA =
                DeviceFactory.create(
                        DeviceId.generate(),
                        name,
                        deviceType,
                        periodicity,
                        LocalDate.of(2026, 1, 1));
        Device deviceB =
                DeviceFactory.create(
                        DeviceId.generate(),
                        name,
                        deviceType,
                        periodicity,
                        LocalDate.of(2026, 2, 1));

        // SUT
        boolean result = deviceA.sameAs(deviceB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentType_returnsFalse() {
        // Arrange
        Device device =
                DeviceFactory.create(
                        DeviceId.generate(),
                        "Infusion Pump #1",
                        new DeviceType("Infusion Pump"),
                        new Periodicity(30),
                        LocalDate.of(2026, 1, 1));
        String notADevice = "Infusion Pump #1";

        // SUT
        boolean result = device.sameAs(notADevice);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_null_returnsFalse() {
        // Arrange
        Device device =
                DeviceFactory.create(
                        DeviceId.generate(),
                        "Infusion Pump #1",
                        new DeviceType("Infusion Pump"),
                        new Periodicity(30),
                        LocalDate.of(2026, 1, 1));

        // SUT
        boolean result = device.sameAs(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_getters_returnConstructedValues() {
        // Arrange
        String name = "Infusion Pump #1";
        DeviceType deviceType = new DeviceType("Infusion Pump");
        Periodicity periodicity = new Periodicity(30);
        LocalDate lastReplacedAt = LocalDate.of(2026, 1, 1);
        Device device =
                DeviceFactory.create(
                        DeviceId.generate(), name, deviceType, periodicity, lastReplacedAt);

        // Assert
        assertEquals(name, device.name());
        assertEquals(deviceType, device.deviceType());
        assertEquals(periodicity, device.periodicity());
        assertEquals(lastReplacedAt, device.lastReplacedAt());
    }
}
