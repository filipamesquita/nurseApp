package com.example.demo.domain.model;

public final class UserFactory {

    private UserFactory() {}

    public static User create(UserId userId, String name, Role role) {
        return new User(userId, name, role);
    }
}
