package com.example.demo.domain.model;

import com.example.demo.domain.kernel.ValueObject;
import java.util.Objects;

public final class DeviceType implements ValueObject {

    private final String label;

    public DeviceType(String label) {
        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("label is required");
        }
        this.label = label;
    }

    public String label() {
        return label;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceType)) {
            return false;
        }
        DeviceType that = (DeviceType) other;
        return label.equals(that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
