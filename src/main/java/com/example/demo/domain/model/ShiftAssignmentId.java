package com.example.demo.domain.model;

import com.example.demo.domain.kernel.DomainId;
import java.util.Objects;
import java.util.UUID;

public final class ShiftAssignmentId implements DomainId {

    private final UUID value;

    public ShiftAssignmentId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("value is required");
        }
        this.value = value;
    }

    public static ShiftAssignmentId generate() {
        return new ShiftAssignmentId(UUID.randomUUID());
    }

    public UUID value() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShiftAssignmentId)) {
            return false;
        }
        ShiftAssignmentId that = (ShiftAssignmentId) other;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
