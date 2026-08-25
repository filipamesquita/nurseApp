package com.example.demo.domain.model;

import com.example.demo.domain.kernel.AggregateRoot;
import java.util.Objects;

public final class ShiftAssignment implements AggregateRoot<ShiftAssignmentId> {

    private final ShiftAssignmentId shiftAssignmentId;
    private final ShiftId shiftId;
    private final RoomId roomId;
    private final UserId nurseId;
    private final UserId assignedById;

    ShiftAssignment(
            ShiftAssignmentId shiftAssignmentId,
            ShiftId shiftId,
            RoomId roomId,
            UserId nurseId,
            UserId assignedById) {
        if (shiftAssignmentId == null) {
            throw new IllegalArgumentException("shiftAssignmentId is required");
        }
        if (shiftId == null) {
            throw new IllegalArgumentException("shiftId is required");
        }
        if (roomId == null) {
            throw new IllegalArgumentException("roomId is required");
        }
        if (nurseId == null) {
            throw new IllegalArgumentException("nurseId is required");
        }
        if (assignedById == null) {
            throw new IllegalArgumentException("assignedById is required");
        }
        this.shiftAssignmentId = shiftAssignmentId;
        this.shiftId = shiftId;
        this.roomId = roomId;
        this.nurseId = nurseId;
        this.assignedById = assignedById;
    }

    @Override
    public ShiftAssignmentId identity() {
        return shiftAssignmentId;
    }

    public ShiftId shiftId() {
        return shiftId;
    }

    public RoomId roomId() {
        return roomId;
    }

    public UserId nurseId() {
        return nurseId;
    }

    public UserId assignedById() {
        return assignedById;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof ShiftAssignment)) {
            return false;
        }
        ShiftAssignment that = (ShiftAssignment) other;
        return shiftId.equals(that.shiftId)
                && roomId.equals(that.roomId)
                && nurseId.equals(that.nurseId)
                && assignedById.equals(that.assignedById);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShiftAssignment)) {
            return false;
        }
        ShiftAssignment that = (ShiftAssignment) other;
        return shiftAssignmentId.equals(that.shiftAssignmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shiftAssignmentId);
    }
}
