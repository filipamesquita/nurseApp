package com.example.demo.domain.model;

public final class OperatingRoomFactory {

    private OperatingRoomFactory() {}

    public static OperatingRoom create(RoomId roomId, String name) {
        return new OperatingRoom(roomId, name);
    }
}
