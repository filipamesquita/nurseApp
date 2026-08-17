package com.example.demo.domain.model;

import com.example.demo.domain.kernel.AggregateRoot;
import java.util.Objects;

public final class OperatingRoom implements AggregateRoot<RoomId> {

    private final RoomId roomId;
    private final String name;

    OperatingRoom(RoomId roomId, String name) {
        if (roomId == null) {
            throw new IllegalArgumentException("roomId is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        this.roomId = roomId;
        this.name = name;
    }

    @Override
    public RoomId identity() {
        return roomId;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof OperatingRoom)) {
            return false;
        }
        OperatingRoom that = (OperatingRoom) other;
        return name.equals(that.name);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OperatingRoom)) {
            return false;
        }
        OperatingRoom that = (OperatingRoom) other;
        return roomId.equals(that.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }
}
