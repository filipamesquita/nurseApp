package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class RoomIdTest {

    @Test
    void test_constructor_validUuid_createsRoomId() {
        // Arrange
        UUID value = UUID.randomUUID();

        // SUT
        RoomId roomId = new RoomId(value);

        // Assert
        assertEquals(value, roomId.value());
    }

    @Test
    void test_constructor_nullUuid_throwsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new RoomId(null));
    }

    @Test
    void test_generate_returnsNonNullRoomId() {
        // SUT
        RoomId roomId = RoomId.generate();

        // Assert
        assertNotNull(roomId.value());
    }

    @Test
    void test_generate_returnsDistinctRoomIds() {
        // SUT
        RoomId roomIdA = RoomId.generate();
        RoomId roomIdB = RoomId.generate();

        // Assert
        assertNotEquals(roomIdA, roomIdB);
    }

    @Test
    void test_equals_sameValue_returnsTrue() {
        // Arrange
        UUID value = UUID.randomUUID();
        RoomId roomIdA = new RoomId(value);
        RoomId roomIdB = new RoomId(value);

        // Assert
        assertEquals(roomIdA, roomIdB);
    }

    @Test
    void test_equals_differentValue_returnsFalse() {
        // Arrange
        RoomId roomIdA = new RoomId(UUID.randomUUID());
        RoomId roomIdB = new RoomId(UUID.randomUUID());

        // Assert
        assertNotEquals(roomIdA, roomIdB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        RoomId roomId = new RoomId(UUID.randomUUID());

        // Assert
        assertTrue(roomId.equals(roomId));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        RoomId roomId = new RoomId(UUID.randomUUID());
        String notARoomId = "not-a-room-id";

        // Assert
        assertFalse(roomId.equals(notARoomId));
    }

    @Test
    void test_hashCode_sameValue_returnsSameHashCode() {
        // Arrange
        UUID value = UUID.randomUUID();
        RoomId roomIdA = new RoomId(value);
        RoomId roomIdB = new RoomId(value);

        // Assert
        assertEquals(roomIdA.hashCode(), roomIdB.hashCode());
    }
}
