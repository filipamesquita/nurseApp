package com.example.demo.domain.kernel;

public interface DomainEntity<ID extends DomainId> {

    ID identity();

    boolean sameAs(Object other);
}
