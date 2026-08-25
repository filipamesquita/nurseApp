package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void test_create_validArguments_createsUser() {
        // Arrange
        UserId userId = UserId.generate();
        String name = "Jane Doe";
        Role role = Role.NURSE;

        // SUT
        User user = UserFactory.create(userId, name, role);

        // Assert
        assertEquals(userId, user.identity());
    }

    @Test
    void test_create_nullUserId_throwsException() {
        // Arrange
        String name = "Jane Doe";
        Role role = Role.NURSE;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> UserFactory.create(null, name, role));
    }

    @Test
    void test_create_nullName_throwsException() {
        // Arrange
        UserId userId = UserId.generate();
        Role role = Role.NURSE;

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class, () -> UserFactory.create(userId, null, role));
    }

    @Test
    void test_create_blankName_throwsException() {
        // Arrange
        UserId userId = UserId.generate();
        Role role = Role.NURSE;

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class, () -> UserFactory.create(userId, "   ", role));
    }

    @Test
    void test_create_nullRole_throwsException() {
        // Arrange
        UserId userId = UserId.generate();
        String name = "Jane Doe";

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class, () -> UserFactory.create(userId, name, null));
    }

    @Test
    void test_identity_returnsUserId() {
        // Arrange
        UserId userId = UserId.generate();
        User user = UserFactory.create(userId, "Jane Doe", Role.NURSE);

        // SUT
        UserId identity = user.identity();

        // Assert
        assertEquals(userId, identity);
    }

    @Test
    void test_equals_sameUserId_returnsTrue_evenWithDifferentValues() {
        // Arrange
        UserId userId = UserId.generate();
        User userA = UserFactory.create(userId, "Jane Doe", Role.NURSE);
        User userB = UserFactory.create(userId, "Janet Doe", Role.ADMIN);

        // Assert
        assertEquals(userA, userB);
    }

    @Test
    void test_equals_differentUserId_returnsFalse() {
        // Arrange
        User userA = UserFactory.create(UserId.generate(), "Jane Doe", Role.NURSE);
        User userB = UserFactory.create(UserId.generate(), "Jane Doe", Role.NURSE);

        // Assert
        assertNotEquals(userA, userB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        User user = UserFactory.create(UserId.generate(), "Jane Doe", Role.NURSE);

        // Assert
        assertTrue(user.equals(user));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        User user = UserFactory.create(UserId.generate(), "Jane Doe", Role.NURSE);
        String notAUser = "Jane Doe";

        // Assert
        assertFalse(user.equals(notAUser));
    }

    @Test
    void test_hashCode_sameUserId_returnsSameHashCode() {
        // Arrange
        UserId userId = UserId.generate();
        User userA = UserFactory.create(userId, "Jane Doe", Role.NURSE);
        User userB = UserFactory.create(userId, "Janet Doe", Role.ADMIN);

        // Assert
        assertEquals(userA.hashCode(), userB.hashCode());
    }

    @Test
    void test_sameAs_sameValues_returnsTrue() {
        // Arrange
        String name = "Jane Doe";
        Role role = Role.NURSE;
        User userA = UserFactory.create(UserId.generate(), name, role);
        User userB = UserFactory.create(UserId.generate(), name, role);

        // SUT
        boolean result = userA.sameAs(userB);

        // Assert
        assertTrue(result);
    }

    @Test
    void test_sameAs_differentName_returnsFalse() {
        // Arrange
        Role role = Role.NURSE;
        User userA = UserFactory.create(UserId.generate(), "Jane Doe", role);
        User userB = UserFactory.create(UserId.generate(), "John Doe", role);

        // SUT
        boolean result = userA.sameAs(userB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentRole_returnsFalse() {
        // Arrange
        String name = "Jane Doe";
        User userA = UserFactory.create(UserId.generate(), name, Role.NURSE);
        User userB = UserFactory.create(UserId.generate(), name, Role.ADMIN);

        // SUT
        boolean result = userA.sameAs(userB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentType_returnsFalse() {
        // Arrange
        User user = UserFactory.create(UserId.generate(), "Jane Doe", Role.NURSE);
        String notAUser = "Jane Doe";

        // SUT
        boolean result = user.sameAs(notAUser);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_null_returnsFalse() {
        // Arrange
        User user = UserFactory.create(UserId.generate(), "Jane Doe", Role.NURSE);

        // SUT
        boolean result = user.sameAs(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_getters_returnConstructedValues() {
        // Arrange
        String name = "Jane Doe";
        Role role = Role.HEAD_NURSE;
        User user = UserFactory.create(UserId.generate(), name, role);

        // Assert
        assertEquals(name, user.name());
        assertEquals(role, user.role());
    }
}
