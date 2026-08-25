package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DevicePlacementTest {

    @Test
    void test_create_validArguments_createsDevicePlacement() {
        // Arrange
        DevicePlacementId devicePlacementId = DevicePlacementId.generate();
        DeviceId deviceId = DeviceId.generate();
        RoomId roomId = RoomId.generate();
        LocalDate since = LocalDate.of(2026, 8, 16);

        // SUT
        DevicePlacement devicePlacement =
                DevicePlacementFactory.create(devicePlacementId, deviceId, roomId, since);

        // Assert
        assertEquals(devicePlacementId, devicePlacement.identity());
    }

    @Test
    void test_create_nullDevicePlacementId_throwsException() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        RoomId roomId = RoomId.generate();
        LocalDate since = LocalDate.of(2026, 8, 16);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> DevicePlacementFactory.create(null, deviceId, roomId, since));
    }

    @Test
    void test_create_nullDeviceId_throwsException() {
        // Arrange
        DevicePlacementId devicePlacementId = DevicePlacementId.generate();
        RoomId roomId = RoomId.generate();
        LocalDate since = LocalDate.of(2026, 8, 16);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> DevicePlacementFactory.create(devicePlacementId, null, roomId, since));
    }

    @Test
    void test_create_nullRoomId_throwsException() {
        // Arrange
        DevicePlacementId devicePlacementId = DevicePlacementId.generate();
        DeviceId deviceId = DeviceId.generate();
        LocalDate since = LocalDate.of(2026, 8, 16);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> DevicePlacementFactory.create(devicePlacementId, deviceId, null, since));
    }

    @Test
    void test_create_nullSince_throwsException() {
        // Arrange
        DevicePlacementId devicePlacementId = DevicePlacementId.generate();
        DeviceId deviceId = DeviceId.generate();
        RoomId roomId = RoomId.generate();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> DevicePlacementFactory.create(devicePlacementId, deviceId, roomId, null));
    }

    @Test
    void test_identity_returnsDevicePlacementId() {
        // Arrange
        DevicePlacementId devicePlacementId = DevicePlacementId.generate();
        DevicePlacement devicePlacement =
                DevicePlacementFactory.create(
                        devicePlacementId,
                        DeviceId.generate(),
                        RoomId.generate(),
                        LocalDate.of(2026, 8, 16));

        // SUT
        DevicePlacementId identity = devicePlacement.identity();

        // Assert
        assertEquals(devicePlacementId, identity);
    }

    @Test
    void test_deviceId_returnsConstructedValue() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        DevicePlacement devicePlacement =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(),
                        deviceId,
                        RoomId.generate(),
                        LocalDate.of(2026, 8, 16));

        // SUT
        DeviceId result = devicePlacement.deviceId();

        // Assert
        assertEquals(deviceId, result);
    }

    @Test
    void test_roomId_returnsConstructedValue() {
        // Arrange
        RoomId roomId = RoomId.generate();
        DevicePlacement devicePlacement =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(),
                        DeviceId.generate(),
                        roomId,
                        LocalDate.of(2026, 8, 16));

        // SUT
        RoomId result = devicePlacement.roomId();

        // Assert
        assertEquals(roomId, result);
    }

    @Test
    void test_since_returnsConstructedValue() {
        // Arrange
        LocalDate since = LocalDate.of(2026, 8, 16);
        DevicePlacement devicePlacement =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(), DeviceId.generate(), RoomId.generate(), since);

        // SUT
        LocalDate result = devicePlacement.since();

        // Assert
        assertEquals(since, result);
    }

    @Test
    void test_equals_sameDevicePlacementId_returnsTrue_evenWithDifferentValues() {
        // Arrange
        DevicePlacementId devicePlacementId = DevicePlacementId.generate();
        DevicePlacement placementA =
                DevicePlacementFactory.create(
                        devicePlacementId,
                        DeviceId.generate(),
                        RoomId.generate(),
                        LocalDate.of(2026, 8, 16));
        DevicePlacement placementB =
                DevicePlacementFactory.create(
                        devicePlacementId,
                        DeviceId.generate(),
                        RoomId.generate(),
                        LocalDate.of(2026, 1, 1));

        // Assert
        assertEquals(placementA, placementB);
    }

    @Test
    void test_equals_differentDevicePlacementId_returnsFalse() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        RoomId roomId = RoomId.generate();
        LocalDate since = LocalDate.of(2026, 8, 16);
        DevicePlacement placementA =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(), deviceId, roomId, since);
        DevicePlacement placementB =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(), deviceId, roomId, since);

        // Assert
        assertNotEquals(placementA, placementB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        DevicePlacement devicePlacement =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(),
                        DeviceId.generate(),
                        RoomId.generate(),
                        LocalDate.of(2026, 8, 16));

        // Assert
        assertTrue(devicePlacement.equals(devicePlacement));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        DevicePlacement devicePlacement =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(),
                        DeviceId.generate(),
                        RoomId.generate(),
                        LocalDate.of(2026, 8, 16));
        String notADevicePlacement = "not-a-device-placement";

        // Assert
        assertFalse(devicePlacement.equals(notADevicePlacement));
    }

    @Test
    void test_hashCode_sameDevicePlacementId_returnsSameHashCode() {
        // Arrange
        DevicePlacementId devicePlacementId = DevicePlacementId.generate();
        DevicePlacement placementA =
                DevicePlacementFactory.create(
                        devicePlacementId,
                        DeviceId.generate(),
                        RoomId.generate(),
                        LocalDate.of(2026, 8, 16));
        DevicePlacement placementB =
                DevicePlacementFactory.create(
                        devicePlacementId,
                        DeviceId.generate(),
                        RoomId.generate(),
                        LocalDate.of(2026, 1, 1));

        // Assert
        assertEquals(placementA.hashCode(), placementB.hashCode());
    }

    @Test
    void test_sameAs_sameValues_returnsTrue() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        RoomId roomId = RoomId.generate();
        LocalDate since = LocalDate.of(2026, 8, 16);
        DevicePlacement placementA =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(), deviceId, roomId, since);
        DevicePlacement placementB =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(), deviceId, roomId, since);

        // SUT
        boolean result = placementA.sameAs(placementB);

        // Assert
        assertTrue(result);
    }

    @Test
    void test_sameAs_differentDeviceId_returnsFalse() {
        // Arrange
        RoomId roomId = RoomId.generate();
        LocalDate since = LocalDate.of(2026, 8, 16);
        DevicePlacement placementA =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(), DeviceId.generate(), roomId, since);
        DevicePlacement placementB =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(), DeviceId.generate(), roomId, since);

        // SUT
        boolean result = placementA.sameAs(placementB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentRoomId_returnsFalse() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        LocalDate since = LocalDate.of(2026, 8, 16);
        DevicePlacement placementA =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(), deviceId, RoomId.generate(), since);
        DevicePlacement placementB =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(), deviceId, RoomId.generate(), since);

        // SUT
        boolean result = placementA.sameAs(placementB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentSince_returnsFalse() {
        // Arrange
        DeviceId deviceId = DeviceId.generate();
        RoomId roomId = RoomId.generate();
        DevicePlacement placementA =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(), deviceId, roomId, LocalDate.of(2026, 8, 16));
        DevicePlacement placementB =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(), deviceId, roomId, LocalDate.of(2026, 1, 1));

        // SUT
        boolean result = placementA.sameAs(placementB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentType_returnsFalse() {
        // Arrange
        DevicePlacement devicePlacement =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(),
                        DeviceId.generate(),
                        RoomId.generate(),
                        LocalDate.of(2026, 8, 16));
        String notADevicePlacement = "not-a-device-placement";

        // SUT
        boolean result = devicePlacement.sameAs(notADevicePlacement);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_null_returnsFalse() {
        // Arrange
        DevicePlacement devicePlacement =
                DevicePlacementFactory.create(
                        DevicePlacementId.generate(),
                        DeviceId.generate(),
                        RoomId.generate(),
                        LocalDate.of(2026, 8, 16));

        // SUT
        boolean result = devicePlacement.sameAs(null);

        // Assert
        assertFalse(result);
    }
}
