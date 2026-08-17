package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class OperatingRoomTest {

    @Test
    void test_create_validArguments_createsOperatingRoom() {
        // Arrange
        RoomId roomId = RoomId.generate();
        String name = "Coral";

        // SUT
        OperatingRoom operatingRoom = OperatingRoomFactory.create(roomId, name);

        // Assert
        assertEquals(roomId, operatingRoom.identity());
    }

    @Test
    void test_create_nullRoomId_throwsException() {
        // Arrange
        String name = "Coral";

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class, () -> OperatingRoomFactory.create(null, name));
    }

    @Test
    void test_create_nullName_throwsException() {
        // Arrange
        RoomId roomId = RoomId.generate();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class, () -> OperatingRoomFactory.create(roomId, null));
    }

    @Test
    void test_create_blankName_throwsException() {
        // Arrange
        RoomId roomId = RoomId.generate();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> OperatingRoomFactory.create(roomId, "   "));
    }

    @Test
    void test_identity_returnsRoomId() {
        // Arrange
        RoomId roomId = RoomId.generate();
        OperatingRoom operatingRoom = OperatingRoomFactory.create(roomId, "Coral");

        // SUT
        RoomId identity = operatingRoom.identity();

        // Assert
        assertEquals(roomId, identity);
    }

    @Test
    void test_name_returnsConstructedValue() {
        // Arrange
        String name = "Coral";
        OperatingRoom operatingRoom = OperatingRoomFactory.create(RoomId.generate(), name);

        // SUT
        String result = operatingRoom.name();

        // Assert
        assertEquals(name, result);
    }

    @Test
    void test_equals_sameRoomId_returnsTrue_evenWithDifferentValues() {
        // Arrange
        RoomId roomId = RoomId.generate();
        OperatingRoom roomA = OperatingRoomFactory.create(roomId, "Coral");
        OperatingRoom roomB = OperatingRoomFactory.create(roomId, "Coral (renamed)");

        // Assert
        assertEquals(roomA, roomB);
    }

    @Test
    void test_equals_differentRoomId_returnsFalse() {
        // Arrange
        OperatingRoom roomA = OperatingRoomFactory.create(RoomId.generate(), "Coral");
        OperatingRoom roomB = OperatingRoomFactory.create(RoomId.generate(), "Coral");

        // Assert
        assertNotEquals(roomA, roomB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        OperatingRoom operatingRoom = OperatingRoomFactory.create(RoomId.generate(), "Coral");

        // Assert
        assertTrue(operatingRoom.equals(operatingRoom));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        OperatingRoom operatingRoom = OperatingRoomFactory.create(RoomId.generate(), "Coral");
        String notAnOperatingRoom = "Coral";

        // Assert
        assertFalse(operatingRoom.equals(notAnOperatingRoom));
    }

    @Test
    void test_hashCode_sameRoomId_returnsSameHashCode() {
        // Arrange
        RoomId roomId = RoomId.generate();
        OperatingRoom roomA = OperatingRoomFactory.create(roomId, "Coral");
        OperatingRoom roomB = OperatingRoomFactory.create(roomId, "Coral (renamed)");

        // Assert
        assertEquals(roomA.hashCode(), roomB.hashCode());
    }

    @Test
    void test_sameAs_sameValues_returnsTrue() {
        // Arrange
        String name = "Coral";
        OperatingRoom roomA = OperatingRoomFactory.create(RoomId.generate(), name);
        OperatingRoom roomB = OperatingRoomFactory.create(RoomId.generate(), name);

        // SUT
        boolean result = roomA.sameAs(roomB);

        // Assert
        assertTrue(result);
    }

    @Test
    void test_sameAs_differentName_returnsFalse() {
        // Arrange
        OperatingRoom roomA = OperatingRoomFactory.create(RoomId.generate(), "Coral");
        OperatingRoom roomB = OperatingRoomFactory.create(RoomId.generate(), "Cobalt");

        // SUT
        boolean result = roomA.sameAs(roomB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentType_returnsFalse() {
        // Arrange
        OperatingRoom operatingRoom = OperatingRoomFactory.create(RoomId.generate(), "Coral");
        String notAnOperatingRoom = "Coral";

        // SUT
        boolean result = operatingRoom.sameAs(notAnOperatingRoom);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_null_returnsFalse() {
        // Arrange
        OperatingRoom operatingRoom = OperatingRoomFactory.create(RoomId.generate(), "Coral");

        // SUT
        boolean result = operatingRoom.sameAs(null);

        // Assert
        assertFalse(result);
    }
}
