package com.example.demo.domain.model;

import com.example.demo.domain.kernel.DomainId;
import java.util.Objects;
import java.util.UUID;

public final class DeviceId implements DomainId {

    private final UUID value;

    public DeviceId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("value is required");
        }
        this.value = value;
    }

    public static DeviceId generate() {
        return new DeviceId(UUID.randomUUID());
    }

    public UUID value() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceId)) {
            return false;
        }
        DeviceId that = (DeviceId) other;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
