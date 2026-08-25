package com.example.demo.domain.model;

public final class ShiftAssignmentFactory {

    private ShiftAssignmentFactory() {}

    public static ShiftAssignment create(
            ShiftAssignmentId shiftAssignmentId,
            ShiftId shiftId,
            RoomId roomId,
            UserId nurseId,
            UserId assignedById) {
        return new ShiftAssignment(shiftAssignmentId, shiftId, roomId, nurseId, assignedById);
    }
}
