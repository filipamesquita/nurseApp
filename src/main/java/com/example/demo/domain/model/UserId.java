package com.example.demo.domain.model;

import com.example.demo.domain.kernel.DomainId;
import java.util.Objects;
import java.util.UUID;

public final class UserId implements DomainId {

    private final UUID value;

    public UserId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("value is required");
        }
        this.value = value;
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    public UUID value() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserId)) {
            return false;
        }
        UserId that = (UserId) other;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
