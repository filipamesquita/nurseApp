package com.example.demo.domain.model;

import com.example.demo.domain.kernel.AggregateRoot;
import java.util.Objects;

public final class User implements AggregateRoot<UserId> {

    private final UserId userId;
    private final String name;
    private final Role role;

    User(UserId userId, String name, Role role) {
        if (userId == null) {
            throw new IllegalArgumentException("userId is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        if (role == null) {
            throw new IllegalArgumentException("role is required");
        }
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    @Override
    public UserId identity() {
        return userId;
    }

    public String name() {
        return name;
    }

    public Role role() {
        return role;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof User)) {
            return false;
        }
        User that = (User) other;
        return name.equals(that.name) && role.equals(that.role);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User that = (User) other;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
