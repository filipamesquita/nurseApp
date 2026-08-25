package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ShiftWindowTest {

    @Test
    void test_constructor_validPeriod_createsShiftWindow() {
        // Arrange
        ShiftWindow.Period period = ShiftWindow.Period.MORNING;

        // SUT
        ShiftWindow window = new ShiftWindow(period);

        // Assert
        assertEquals(period, window.period());
    }

    @Test
    void test_constructor_nullPeriod_throwsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new ShiftWindow(null));
    }

    @Test
    void test_equals_samePeriod_returnsTrue() {
        // Arrange
        ShiftWindow windowA = new ShiftWindow(ShiftWindow.Period.AFTERNOON);
        ShiftWindow windowB = new ShiftWindow(ShiftWindow.Period.AFTERNOON);

        // Assert
        assertEquals(windowA, windowB);
    }

    @Test
    void test_equals_differentPeriod_returnsFalse() {
        // Arrange
        ShiftWindow windowA = new ShiftWindow(ShiftWindow.Period.MORNING);
        ShiftWindow windowB = new ShiftWindow(ShiftWindow.Period.NIGHT);

        // Assert
        assertNotEquals(windowA, windowB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        ShiftWindow window = new ShiftWindow(ShiftWindow.Period.NIGHT);

        // Assert
        assertTrue(window.equals(window));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        ShiftWindow window = new ShiftWindow(ShiftWindow.Period.NIGHT);
        String notAShiftWindow = "not-a-shift-window";

        // Assert
        assertFalse(window.equals(notAShiftWindow));
    }

    @Test
    void test_hashCode_samePeriod_returnsSameHashCode() {
        // Arrange
        ShiftWindow windowA = new ShiftWindow(ShiftWindow.Period.MORNING);
        ShiftWindow windowB = new ShiftWindow(ShiftWindow.Period.MORNING);

        // Assert
        assertEquals(windowA.hashCode(), windowB.hashCode());
    }
}
