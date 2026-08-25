package com.example.demo.domain.model;

import com.example.demo.domain.kernel.AggregateRoot;
import java.time.LocalDate;
import java.util.Objects;

public final class DevicePlacement implements AggregateRoot<DevicePlacementId> {

    private final DevicePlacementId devicePlacementId;
    private final DeviceId deviceId;
    private final RoomId roomId;
    private final LocalDate since;

    DevicePlacement(
            DevicePlacementId devicePlacementId, DeviceId deviceId, RoomId roomId, LocalDate since) {
        if (devicePlacementId == null) {
            throw new IllegalArgumentException("devicePlacementId is required");
        }
        if (deviceId == null) {
            throw new IllegalArgumentException("deviceId is required");
        }
        if (roomId == null) {
            throw new IllegalArgumentException("roomId is required");
        }
        if (since == null) {
            throw new IllegalArgumentException("since is required");
        }
        this.devicePlacementId = devicePlacementId;
        this.deviceId = deviceId;
        this.roomId = roomId;
        this.since = since;
    }

    @Override
    public DevicePlacementId identity() {
        return devicePlacementId;
    }

    public DeviceId deviceId() {
        return deviceId;
    }

    public RoomId roomId() {
        return roomId;
    }

    public LocalDate since() {
        return since;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof DevicePlacement)) {
            return false;
        }
        DevicePlacement that = (DevicePlacement) other;
        return deviceId.equals(that.deviceId)
                && roomId.equals(that.roomId)
                && since.equals(that.since);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DevicePlacement)) {
            return false;
        }
        DevicePlacement that = (DevicePlacement) other;
        return devicePlacementId.equals(that.devicePlacementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(devicePlacementId);
    }
}
