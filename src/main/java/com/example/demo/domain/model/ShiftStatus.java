package com.example.demo.domain.model;

import com.example.demo.domain.kernel.ValueObject;
import java.util.Objects;

public final class ShiftStatus implements ValueObject {

    public enum State {
        PLANNED,
        ACTIVE,
        CLOSED
    }

    private final State state;

    public ShiftStatus(State state) {
        if (state == null) {
            throw new IllegalArgumentException("state is required");
        }
        this.state = state;
    }

    public State state() {
        return state;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShiftStatus)) {
            return false;
        }
        ShiftStatus that = (ShiftStatus) other;
        return state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
