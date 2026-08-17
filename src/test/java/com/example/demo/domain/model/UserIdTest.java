package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserIdTest {

    @Test
    void test_constructor_validUuid_createsUserId() {
        // Arrange
        UUID value = UUID.randomUUID();

        // SUT
        UserId userId = new UserId(value);

        // Assert
        assertEquals(value, userId.value());
    }

    @Test
    void test_constructor_nullUuid_throwsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new UserId(null));
    }

    @Test
    void test_generate_returnsNonNullUserId() {
        // SUT
        UserId userId = UserId.generate();

        // Assert
        assertNotNull(userId.value());
    }

    @Test
    void test_generate_returnsDistinctUserIds() {
        // SUT
        UserId userIdA = UserId.generate();
        UserId userIdB = UserId.generate();

        // Assert
        assertNotEquals(userIdA, userIdB);
    }

    @Test
    void test_equals_sameValue_returnsTrue() {
        // Arrange
        UUID value = UUID.randomUUID();
        UserId userIdA = new UserId(value);
        UserId userIdB = new UserId(value);

        // Assert
        assertEquals(userIdA, userIdB);
    }

    @Test
    void test_equals_differentValue_returnsFalse() {
        // Arrange
        UserId userIdA = new UserId(UUID.randomUUID());
        UserId userIdB = new UserId(UUID.randomUUID());

        // Assert
        assertNotEquals(userIdA, userIdB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        UserId userId = new UserId(UUID.randomUUID());

        // Assert
        assertTrue(userId.equals(userId));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        UserId userId = new UserId(UUID.randomUUID());
        String notAUserId = "not-a-user-id";

        // Assert
        assertFalse(userId.equals(notAUserId));
    }

    @Test
    void test_hashCode_sameValue_returnsSameHashCode() {
        // Arrange
        UUID value = UUID.randomUUID();
        UserId userIdA = new UserId(value);
        UserId userIdB = new UserId(value);

        // Assert
        assertEquals(userIdA.hashCode(), userIdB.hashCode());
    }
}
