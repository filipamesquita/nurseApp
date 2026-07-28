package com.example.demo.domain.model;

import com.example.demo.domain.kernel.ValueObject;
import java.time.LocalDate;
import java.util.Objects;

public final class Periodicity implements ValueObject {

    private final int _intervalDays;

    public Periodicity(int intervalDays) {
        if (intervalDays <= 0) {
            throw new IllegalArgumentException("intervalDays must be positive");
        }
        this._intervalDays = intervalDays;
    }

    public int intervalDays() {
        return _intervalDays;
    }

    public LocalDate nextDueDateFrom(LocalDate lastReplacedAt) {
        if (lastReplacedAt == null) {
            throw new IllegalArgumentException("lastReplacedAt is required");
        }
        return lastReplacedAt.plusDays(_intervalDays);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Periodicity)) {
            return false;
        }
        Periodicity that = (Periodicity) other;
        return _intervalDays == that._intervalDays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_intervalDays);
    }
}
