package com.example.demo.domain.model;

public final class ShiftFactory {

    private ShiftFactory() {}

    public static Shift create(ShiftId shiftId, ShiftWindow window, ShiftStatus status) {
        return new Shift(shiftId, window, status);
    }
}
