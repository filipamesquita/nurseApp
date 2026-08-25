package com.example.demo.domain.model;

import com.example.demo.domain.kernel.ValueObject;
import java.util.Objects;

public final class ShiftWindow implements ValueObject {

    public enum Period {
        MORNING,
        AFTERNOON,
        NIGHT
    }

    private final Period period;

    public ShiftWindow(Period period) {
        if (period == null) {
            throw new IllegalArgumentException("period is required");
        }
        this.period = period;
    }

    public Period period() {
        return period;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShiftWindow)) {
            return false;
        }
        ShiftWindow that = (ShiftWindow) other;
        return period == that.period;
    }

    @Override
    public int hashCode() {
        return Objects.hash(period);
    }
}
