package com.example.demo.domain.model;

import java.time.LocalDate;

public final class DeviceFactory {

    private DeviceFactory() {}

    public static Device create(
            DeviceId deviceId,
            String name,
            DeviceType deviceType,
            Periodicity periodicity,
            LocalDate lastReplacedAt) {
        return new Device(deviceId, name, deviceType, periodicity, lastReplacedAt);
    }
}
