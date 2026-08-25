package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.domain.kernel.ValueObject;
import org.junit.jupiter.api.Test;

class RoleTest {

    @Test
    void test_values_containsExactlyThreeRoles() {
        // SUT
        Role[] roles = Role.values();

        // Assert
        assertEquals(3, roles.length);
    }

    @Test
    void test_values_containsNurse() {
        // SUT
        Role role = Role.valueOf("NURSE");

        // Assert
        assertNotNull(role);
    }

    @Test
    void test_values_containsHeadNurse() {
        // SUT
        Role role = Role.valueOf("HEAD_NURSE");

        // Assert
        assertNotNull(role);
    }

    @Test
    void test_values_containsAdmin() {
        // SUT
        Role role = Role.valueOf("ADMIN");

        // Assert
        assertNotNull(role);
    }

    @Test
    void test_role_isValueObject() {
        // Assert
        assertEquals(true, ValueObject.class.isAssignableFrom(Role.class));
    }

    @Test
    void test_equals_sameRole_returnsTrue() {
        // Arrange
        Role roleA = Role.NURSE;
        Role roleB = Role.NURSE;

        // Assert
        assertEquals(roleA, roleB);
    }

    @Test
    void test_equals_differentRole_returnsFalse() {
        // Arrange
        Role roleA = Role.NURSE;
        Role roleB = Role.ADMIN;

        // Assert
        assertNotEquals(roleA, roleB);
    }
}
