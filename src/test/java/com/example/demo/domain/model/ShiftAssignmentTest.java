package com.example.demo.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ShiftAssignmentTest {

    @Test
    void test_create_validArguments_createsShiftAssignment() {
        // Arrange
        ShiftAssignmentId shiftAssignmentId = ShiftAssignmentId.generate();
        ShiftId shiftId = ShiftId.generate();
        RoomId roomId = RoomId.generate();
        UserId nurseId = UserId.generate();
        UserId assignedById = UserId.generate();

        // SUT
        ShiftAssignment assignment =
                ShiftAssignmentFactory.create(shiftAssignmentId, shiftId, roomId, nurseId, assignedById);

        // Assert
        assertEquals(shiftAssignmentId, assignment.identity());
    }

    @Test
    void test_create_nullShiftAssignmentId_throwsException() {
        // Arrange
        ShiftId shiftId = ShiftId.generate();
        RoomId roomId = RoomId.generate();
        UserId nurseId = UserId.generate();
        UserId assignedById = UserId.generate();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> ShiftAssignmentFactory.create(null, shiftId, roomId, nurseId, assignedById));
    }

    @Test
    void test_create_nullShiftId_throwsException() {
        // Arrange
        ShiftAssignmentId shiftAssignmentId = ShiftAssignmentId.generate();
        RoomId roomId = RoomId.generate();
        UserId nurseId = UserId.generate();
        UserId assignedById = UserId.generate();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        ShiftAssignmentFactory.create(
                                shiftAssignmentId, null, roomId, nurseId, assignedById));
    }

    @Test
    void test_create_nullRoomId_throwsException() {
        // Arrange
        ShiftAssignmentId shiftAssignmentId = ShiftAssignmentId.generate();
        ShiftId shiftId = ShiftId.generate();
        UserId nurseId = UserId.generate();
        UserId assignedById = UserId.generate();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        ShiftAssignmentFactory.create(
                                shiftAssignmentId, shiftId, null, nurseId, assignedById));
    }

    @Test
    void test_create_nullNurseId_throwsException() {
        // Arrange
        ShiftAssignmentId shiftAssignmentId = ShiftAssignmentId.generate();
        ShiftId shiftId = ShiftId.generate();
        RoomId roomId = RoomId.generate();
        UserId assignedById = UserId.generate();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () ->
                        ShiftAssignmentFactory.create(
                                shiftAssignmentId, shiftId, roomId, null, assignedById));
    }

    @Test
    void test_create_nullAssignedById_throwsException() {
        // Arrange
        ShiftAssignmentId shiftAssignmentId = ShiftAssignmentId.generate();
        ShiftId shiftId = ShiftId.generate();
        RoomId roomId = RoomId.generate();
        UserId nurseId = UserId.generate();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> ShiftAssignmentFactory.create(shiftAssignmentId, shiftId, roomId, nurseId, null));
    }

    @Test
    void test_identity_returnsShiftAssignmentId() {
        // Arrange
        ShiftAssignmentId shiftAssignmentId = ShiftAssignmentId.generate();
        ShiftAssignment assignment =
                ShiftAssignmentFactory.create(
                        shiftAssignmentId,
                        ShiftId.generate(),
                        RoomId.generate(),
                        UserId.generate(),
                        UserId.generate());

        // SUT
        ShiftAssignmentId identity = assignment.identity();

        // Assert
        assertEquals(shiftAssignmentId, identity);
    }

    @Test
    void test_shiftId_returnsConstructedValue() {
        // Arrange
        ShiftId shiftId = ShiftId.generate();
        ShiftAssignment assignment =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(),
                        shiftId,
                        RoomId.generate(),
                        UserId.generate(),
                        UserId.generate());

        // SUT
        ShiftId result = assignment.shiftId();

        // Assert
        assertEquals(shiftId, result);
    }

    @Test
    void test_roomId_returnsConstructedValue() {
        // Arrange
        RoomId roomId = RoomId.generate();
        ShiftAssignment assignment =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(),
                        ShiftId.generate(),
                        roomId,
                        UserId.generate(),
                        UserId.generate());

        // SUT
        RoomId result = assignment.roomId();

        // Assert
        assertEquals(roomId, result);
    }

    @Test
    void test_nurseId_returnsConstructedValue() {
        // Arrange
        UserId nurseId = UserId.generate();
        ShiftAssignment assignment =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(),
                        ShiftId.generate(),
                        RoomId.generate(),
                        nurseId,
                        UserId.generate());

        // SUT
        UserId result = assignment.nurseId();

        // Assert
        assertEquals(nurseId, result);
    }

    @Test
    void test_assignedById_returnsConstructedValue() {
        // Arrange
        UserId assignedById = UserId.generate();
        ShiftAssignment assignment =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(),
                        ShiftId.generate(),
                        RoomId.generate(),
                        UserId.generate(),
                        assignedById);

        // SUT
        UserId result = assignment.assignedById();

        // Assert
        assertEquals(assignedById, result);
    }

    @Test
    void test_equals_sameShiftAssignmentId_returnsTrue_evenWithDifferentValues() {
        // Arrange
        ShiftAssignmentId shiftAssignmentId = ShiftAssignmentId.generate();
        ShiftAssignment assignmentA =
                ShiftAssignmentFactory.create(
                        shiftAssignmentId,
                        ShiftId.generate(),
                        RoomId.generate(),
                        UserId.generate(),
                        UserId.generate());
        ShiftAssignment assignmentB =
                ShiftAssignmentFactory.create(
                        shiftAssignmentId,
                        ShiftId.generate(),
                        RoomId.generate(),
                        UserId.generate(),
                        UserId.generate());

        // Assert
        assertEquals(assignmentA, assignmentB);
    }

    @Test
    void test_equals_differentShiftAssignmentId_returnsFalse() {
        // Arrange
        ShiftId shiftId = ShiftId.generate();
        RoomId roomId = RoomId.generate();
        UserId nurseId = UserId.generate();
        UserId assignedById = UserId.generate();
        ShiftAssignment assignmentA =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(), shiftId, roomId, nurseId, assignedById);
        ShiftAssignment assignmentB =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(), shiftId, roomId, nurseId, assignedById);

        // Assert
        assertNotEquals(assignmentA, assignmentB);
    }

    @Test
    void test_equals_sameReference_returnsTrue() {
        // Arrange
        ShiftAssignment assignment =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(),
                        ShiftId.generate(),
                        RoomId.generate(),
                        UserId.generate(),
                        UserId.generate());

        // Assert
        assertTrue(assignment.equals(assignment));
    }

    @Test
    void test_equals_differentType_returnsFalse() {
        // Arrange
        ShiftAssignment assignment =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(),
                        ShiftId.generate(),
                        RoomId.generate(),
                        UserId.generate(),
                        UserId.generate());
        String notAShiftAssignment = "not-a-shift-assignment";

        // Assert
        assertFalse(assignment.equals(notAShiftAssignment));
    }

    @Test
    void test_hashCode_sameShiftAssignmentId_returnsSameHashCode() {
        // Arrange
        ShiftAssignmentId shiftAssignmentId = ShiftAssignmentId.generate();
        ShiftAssignment assignmentA =
                ShiftAssignmentFactory.create(
                        shiftAssignmentId,
                        ShiftId.generate(),
                        RoomId.generate(),
                        UserId.generate(),
                        UserId.generate());
        ShiftAssignment assignmentB =
                ShiftAssignmentFactory.create(
                        shiftAssignmentId,
                        ShiftId.generate(),
                        RoomId.generate(),
                        UserId.generate(),
                        UserId.generate());

        // Assert
        assertEquals(assignmentA.hashCode(), assignmentB.hashCode());
    }

    @Test
    void test_sameAs_sameValues_returnsTrue() {
        // Arrange
        ShiftId shiftId = ShiftId.generate();
        RoomId roomId = RoomId.generate();
        UserId nurseId = UserId.generate();
        UserId assignedById = UserId.generate();
        ShiftAssignment assignmentA =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(), shiftId, roomId, nurseId, assignedById);
        ShiftAssignment assignmentB =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(), shiftId, roomId, nurseId, assignedById);

        // SUT
        boolean result = assignmentA.sameAs(assignmentB);

        // Assert
        assertTrue(result);
    }

    @Test
    void test_sameAs_differentShiftId_returnsFalse() {
        // Arrange
        RoomId roomId = RoomId.generate();
        UserId nurseId = UserId.generate();
        UserId assignedById = UserId.generate();
        ShiftAssignment assignmentA =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(), ShiftId.generate(), roomId, nurseId, assignedById);
        ShiftAssignment assignmentB =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(), ShiftId.generate(), roomId, nurseId, assignedById);

        // SUT
        boolean result = assignmentA.sameAs(assignmentB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentRoomId_returnsFalse() {
        // Arrange
        ShiftId shiftId = ShiftId.generate();
        UserId nurseId = UserId.generate();
        UserId assignedById = UserId.generate();
        ShiftAssignment assignmentA =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(), shiftId, RoomId.generate(), nurseId, assignedById);
        ShiftAssignment assignmentB =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(), shiftId, RoomId.generate(), nurseId, assignedById);

        // SUT
        boolean result = assignmentA.sameAs(assignmentB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentNurseId_returnsFalse() {
        // Arrange
        ShiftId shiftId = ShiftId.generate();
        RoomId roomId = RoomId.generate();
        UserId assignedById = UserId.generate();
        ShiftAssignment assignmentA =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(), shiftId, roomId, UserId.generate(), assignedById);
        ShiftAssignment assignmentB =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(), shiftId, roomId, UserId.generate(), assignedById);

        // SUT
        boolean result = assignmentA.sameAs(assignmentB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentAssignedById_returnsFalse() {
        // Arrange
        ShiftId shiftId = ShiftId.generate();
        RoomId roomId = RoomId.generate();
        UserId nurseId = UserId.generate();
        ShiftAssignment assignmentA =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(), shiftId, roomId, nurseId, UserId.generate());
        ShiftAssignment assignmentB =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(), shiftId, roomId, nurseId, UserId.generate());

        // SUT
        boolean result = assignmentA.sameAs(assignmentB);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_differentType_returnsFalse() {
        // Arrange
        ShiftAssignment assignment =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(),
                        ShiftId.generate(),
                        RoomId.generate(),
                        UserId.generate(),
                        UserId.generate());
        String notAShiftAssignment = "not-a-shift-assignment";

        // SUT
        boolean result = assignment.sameAs(notAShiftAssignment);

        // Assert
        assertFalse(result);
    }

    @Test
    void test_sameAs_null_returnsFalse() {
        // Arrange
        ShiftAssignment assignment =
                ShiftAssignmentFactory.create(
                        ShiftAssignmentId.generate(),
                        ShiftId.generate(),
                        RoomId.generate(),
                        UserId.generate(),
                        UserId.generate());

        // SUT
        boolean result = assignment.sameAs(null);

        // Assert
        assertFalse(result);
    }
}
