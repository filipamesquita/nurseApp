package com.example.demo.domain.model;

import com.example.demo.domain.kernel.AggregateRoot;
import java.util.Objects;

public final class Shift implements AggregateRoot<ShiftId> {

    private final ShiftId shiftId;
    private final ShiftWindow window;
    private final ShiftStatus status;

    Shift(ShiftId shiftId, ShiftWindow window, ShiftStatus status) {
        if (shiftId == null) {
            throw new IllegalArgumentException("shiftId is required");
        }
        if (window == null) {
            throw new IllegalArgumentException("window is required");
        }
        if (status == null) {
            throw new IllegalArgumentException("status is required");
        }
        this.shiftId = shiftId;
        this.window = window;
        this.status = status;
    }

    @Override
    public ShiftId identity() {
        return shiftId;
    }

    public ShiftWindow window() {
        return window;
    }

    public ShiftStatus status() {
        return status;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Shift)) {
            return false;
        }
        Shift that = (Shift) other;
        return window.equals(that.window) && status.equals(that.status);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Shift)) {
            return false;
        }
        Shift that = (Shift) other;
        return shiftId.equals(that.shiftId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shiftId);
    }
}
