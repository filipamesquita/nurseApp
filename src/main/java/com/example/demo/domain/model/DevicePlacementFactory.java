package com.example.demo.domain.model;

import java.time.LocalDate;

public final class DevicePlacementFactory {

    private DevicePlacementFactory() {}

    public static DevicePlacement create(
            DevicePlacementId devicePlacementId, DeviceId deviceId, RoomId roomId, LocalDate since) {
        return new DevicePlacement(devicePlacementId, deviceId, roomId, since);
    }
}
