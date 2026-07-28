package com.example.demo.domain.kernel;

import java.util.Optional;

public interface RepositoryPort<ID extends DomainId, T extends AggregateRoot<ID>> {

    T save(T aggregate);

    Iterable<T> findAll();

    Optional<T> ofIdentity(ID id);

    boolean containsOfIdentity(ID id);
}
