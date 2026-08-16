package com.example.demo.domain.model;

import com.example.demo.domain.kernel.AggregateRoot;
import java.time.LocalDate;
import java.util.Objects;

public final class Device implements AggregateRoot<DeviceId> {

    private final DeviceId deviceId;
    private final String name;
    private final DeviceType deviceType;
    private final Periodicity periodicity;
    private final LocalDate lastReplacedAt;

    Device(
            DeviceId deviceId,
            String name,
            DeviceType deviceType,
            Periodicity periodicity,
            LocalDate lastReplacedAt) {
        if (deviceId == null) {
            throw new IllegalArgumentException("deviceId is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        if (deviceType == null) {
            throw new IllegalArgumentException("deviceType is required");
        }
        if (periodicity == null) {
            throw new IllegalArgumentException("periodicity is required");
        }
        if (lastReplacedAt == null) {
            throw new IllegalArgumentException("lastReplacedAt is required");
        }
        this.deviceId = deviceId;
        this.name = name;
        this.deviceType = deviceType;
        this.periodicity = periodicity;
        this.lastReplacedAt = lastReplacedAt;
    }

    @Override
    public DeviceId identity() {
        return deviceId;
    }

    public String name() {
        return name;
    }

    public DeviceType deviceType() {
        return deviceType;
    }

    public Periodicity periodicity() {
        return periodicity;
    }

    public LocalDate lastReplacedAt() {
        return lastReplacedAt;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Device)) {
            return false;
        }
        Device that = (Device) other;
        return name.equals(that.name)
                && deviceType.equals(that.deviceType)
                && periodicity.equals(that.periodicity)
                && lastReplacedAt.equals(that.lastReplacedAt);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Device)) {
            return false;
        }
        Device that = (Device) other;
        return deviceId.equals(that.deviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId);
    }
}
