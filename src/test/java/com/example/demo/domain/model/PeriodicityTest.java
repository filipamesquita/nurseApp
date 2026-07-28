package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class PeriodicityTest {

    @Test
    void test_constructor_positiveIntervalDays_createsPeriodicity() {
        // Arrange
        int intervalDays = 30;

        // SUT
        Periodicity periodicity = new Periodicity(intervalDays);

        // Assert
        assertEquals(intervalDays, periodicity.intervalDays());
    }

    @Test
    void test_constructor_zeroIntervalDays_throwsException() {
        // Arrange
        int intervalDays = 0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Periodicity(intervalDays));
    }

    @Test
    void test_constructor_negativeIntervalDays_throwsException() {
        // Arrange
        int intervalDays = -5;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Periodicity(intervalDays));
    }

    @Test
    void test_nextDueDateFrom_givenLastReplacedDate_returnsDatePlusInterval() {
        // Arrange
        Periodicity periodicity = new Periodicity(30);
        LocalDate lastReplacedAt = LocalDate.of(2026, 1, 1);

        // SUT
        LocalDate dueDate = periodicity.nextDueDateFrom(lastReplacedAt);

        // Assert
        assertEquals(LocalDate.of(2026, 1, 31), dueDate);
    }

    @Test
    void test_nextDueDateFrom_nullLastReplacedDate_throwsException() {
        // Arrange
        Periodicity periodicity = new Periodicity(30);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> periodicity.nextDueDateFrom(null));
    }

    @Test
    void test_equals_sameIntervalDays_returnsTrue() {
        // Arrange
        Periodicity periodicityA = new Periodicity(30);
        Periodicity periodicityB = new Periodicity(30);

        // Assert
        assertEquals(periodicityA, periodicityB);
    }

    @Test
    void test_equals_differentIntervalDays_returnsFalse() {
        // Arrange
        Periodicity periodicityA = new Periodicity(30);
        Periodicity periodicityB = new Periodicity(60);

        // Assert
        assertNotEquals(periodicityA, periodicityB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        Periodicity periodicity = new Periodicity(30);

        // Assert
        assertTrue(periodicity.equals(periodicity));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        Periodicity periodicity = new Periodicity(30);
        String notAPeriodicity = "30";

        // Assert
        assertFalse(periodicity.equals(notAPeriodicity));
    }

    @Test
    void test_hashCode_sameIntervalDays_returnsSameHashCode() {
        // Arrange
        Periodicity periodicityA = new Periodicity(30);
        Periodicity periodicityB = new Periodicity(30);

        // Assert
        assertEquals(periodicityA.hashCode(), periodicityB.hashCode());
    }
}
