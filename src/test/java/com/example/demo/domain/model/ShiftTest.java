package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ShiftTest {

    @Test
    void test_create_validArguments_createsShift() {
        // Arrange
        ShiftId shiftId = ShiftId.generate();
        ShiftWindow window = new ShiftWindow(ShiftWindow.Period.MORNING);
        ShiftStatus status = new ShiftStatus(ShiftStatus.State.PLANNED);

        // SUT
        Shift shift = ShiftFactory.create(shiftId, window, status);

        // Assert
        assertEquals(shiftId, shift.identity());
    }

    @Test
    void test_create_nullShiftId_throwsException() {
        // Arrange
        ShiftWindow window = new ShiftWindow(ShiftWindow.Period.MORNING);
        ShiftStatus status = new ShiftStatus(ShiftStatus.State.PLANNED);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class, () -> ShiftFactory.create(null, window, status));
    }

    @Test
    void test_create_nullWindow_throwsException() {
        // Arrange
        ShiftId shiftId = ShiftId.generate();
        ShiftStatus status = new ShiftStatus(ShiftStatus.State.PLANNED);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class, () -> ShiftFactory.create(shiftId, null, status));
    }

    @Test
    void test_create_nullStatus_throwsException() {
        // Arrange
        ShiftId shiftId = ShiftId.generate();
        ShiftWindow window = new ShiftWindow(ShiftWindow.Period.MORNING);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class, () -> ShiftFactory.create(shiftId, window, null));
    }

    @Test
    void test_identity_returnsShiftId() {
        // Arrange
        ShiftId shiftId = ShiftId.generate();
        Shift shift =
                ShiftFactory.create(
                        shiftId,
                        new ShiftWindow(ShiftWindow.Period.MORNING),
                        new ShiftStatus(ShiftStatus.State.PLANNED));

        // SUT
        ShiftId identity = shift.identity();

        // Assert
        assertEquals(shiftId, identity);
    }

    @Test
    void test_window_returnsConstructedValue() {
        // Arrange
        ShiftWindow window = new ShiftWindow(ShiftWindow.Period.NIGHT);
        Shift shift =
                ShiftFactory.create(
                        ShiftId.generate(), window, new ShiftStatus(ShiftStatus.State.PLANNED));

        // SUT
        ShiftWindow result = shift.window();

        // Assert
        assertEquals(window, result);
    }

    @Test
    void test_status_returnsConstructedValue() {
        // Arrange
        ShiftStatus status = new ShiftStatus(ShiftStatus.State.ACTIVE);
        Shift shift =
                ShiftFactory.create(
                        ShiftId.generate(), new ShiftWindow(ShiftWindow.Period.MORNING), status);

        // SUT
        ShiftStatus result = shift.status();

        // Assert
        assertEquals(status, result);
    }

    @Test
    void test_equals_sameShiftId_returnsTrue_evenWithDifferentValues() {
        // Arrange
        ShiftId shiftId = ShiftId.generate();
        Shift shiftA =
                ShiftFactory.create(
                        shiftId,
                        new ShiftWindow(ShiftWindow.Period.MORNING),
                        new ShiftStatus(ShiftStatus.State.PLANNED));
        Shift shiftB =
                ShiftFactory.create(
                        shiftId,
                        new ShiftWindow(ShiftWindow.Period.NIGHT),
                        new ShiftStatus(ShiftStatus.State.CLOSED));

        // Assert
        assertEquals(shiftA, shiftB);
    }

    @Test
    void test_equals_differentShiftId_returnsFalse() {
        // Arrange
        ShiftWindow window = new ShiftWindow(ShiftWindow.Period.MORNING);
        ShiftStatus status = new ShiftStatus(ShiftStatus.State.PLANNED);
        Shift shiftA = ShiftFactory.create(ShiftId.generate(), window, status);
        Shift shiftB = ShiftFactory.create(ShiftId.generate(), window, status);

        // Assert
        assertNotEquals(shiftA, shiftB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        Shift shift =
                ShiftFactory.create(
                        ShiftId.generate(),
                        new ShiftWindow(ShiftWindow.Period.MORNING),
                        new ShiftStatus(ShiftStatus.State.PLANNED));

        // Assert
        assertTrue(shift.equals(shift));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        Shift shift =
                ShiftFactory.create(
                        ShiftId.generate(),
                        new ShiftWindow(ShiftWindow.Period.MORNING),
                        new ShiftStatus(ShiftStatus.State.PLANNED));
        String notAShift = "not-a-shift";

        // Assert
        assertFalse(shift.equals(notAShift));
    }

    @Test
    void test_hashCode_sameShiftId_returnsSameHashCode() {
        // Arrange
        ShiftId shiftId = ShiftId.generate();
        Shift shiftA =
                ShiftFactory.create(
                        shiftId,
                        new ShiftWindow(ShiftWindow.Period.MORNING),
                        new ShiftStatus(ShiftStatus.State.PLANNED));
        Shift shiftB =
                ShiftFactory.create(
                        shiftId,
                        new ShiftWindow(ShiftWindow.Period.NIGHT),
                        new ShiftStatus(ShiftStatus.State.CLOSED));

        // Assert
        assertEquals(shiftA.hashCode(), shiftB.hashCode());
    }

    @Test
    void test_sameAs_sameValues_returnsTrue() {
        // Arrange
        ShiftWindow window = new ShiftWindow(ShiftWindow.Period.MORNING);
        ShiftStatus status = new ShiftStatus(ShiftStatus.State.PLANNED);
        Shift shiftA = ShiftFactory.create(ShiftId.generate(), window, status);
        Shift shiftB = ShiftFactory.create(ShiftId.generate(), window, status);

        // SUT
        boolean result = shiftA.sameAs(shiftB);

        // Assert
        assertTrue(result);
    }

    @Test
    void test_sameAs_differentWindow_returnsFalse() {
        // Arrange
        ShiftStatus status = new ShiftStatus(ShiftStatus.State.PLANNED);
        Shift shiftA =
                ShiftFactory.create(
                        ShiftId.generate(), new ShiftWindow(ShiftWindow.Period.MORNING), status);
        Shift shiftB =
                ShiftFactory.create(
                        ShiftId.generate(), new ShiftWindow(ShiftWindow.Period.NIGHT), status);

        // SUT
        boolean result = shiftA.sameAs(shiftB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentStatus_returnsFalse() {
        // Arrange
        ShiftWindow window = new ShiftWindow(ShiftWindow.Period.MORNING);
        Shift shiftA =
                ShiftFactory.create(
                        ShiftId.generate(), window, new ShiftStatus(ShiftStatus.State.PLANNED));
        Shift shiftB =
                ShiftFactory.create(
                        ShiftId.generate(), window, new ShiftStatus(ShiftStatus.State.CLOSED));

        // SUT
        boolean result = shiftA.sameAs(shiftB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentType_returnsFalse() {
        // Arrange
        Shift shift =
                ShiftFactory.create(
                        ShiftId.generate(),
                        new ShiftWindow(ShiftWindow.Period.MORNING),
                        new ShiftStatus(ShiftStatus.State.PLANNED));
        String notAShift = "not-a-shift";

        // SUT
        boolean result = shift.sameAs(notAShift);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_null_returnsFalse() {
        // Arrange
        Shift shift =
                ShiftFactory.create(
                        ShiftId.generate(),
                        new ShiftWindow(ShiftWindow.Period.MORNING),
                        new ShiftStatus(ShiftStatus.State.PLANNED));

        // SUT
        boolean result = shift.sameAs(null);

        // Assert
        assertFalse(result);
    }
}
