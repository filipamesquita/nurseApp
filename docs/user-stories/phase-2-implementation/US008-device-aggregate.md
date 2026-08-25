# US008 — Device Aggregate

*As the Product Owner, I want the system to know about the medical devices in each
operating room — what they are and when they were last replaced — so that maintenance
staleness (via `Periodicity`, already built) can actually be evaluated for a real device
instead of only in a test.*

## Context

`Periodicity` (the maintenance-interval rule) and the `domain/kernel` marker interfaces
(`AggregateRoot`, `DomainEntity`, `DomainId`, `ValueObject`, `RepositoryPort`) already exist
and are unit-tested. There is no `Device` yet — the aggregate that actually uses
`Periodicity` to answer "is this device due for replacement" doesn't exist in code, only as
a design blueprint in `docs/OOD/design-decisions.md` (DD-003, DD-004, DD-007, DD-008,
DD-009).

This US scaffolds `Device` as the first real Aggregate Root, per that design: identity via
`DeviceId`, package-private constructor, built only through a `DeviceFactory` (DD-008),
`equals`/`hashCode` on identity and a separate `sameAs(Object)` for value comparison
(DD-009), and a `DeviceType` value object to distinguish kinds of equipment. Persistence
(`DeviceRepositoryPort` and its adapter), the due-date domain service (`MaintenanceDueService`,
DD-005), and the web/API layer are explicitly out of scope — those are separate,
independently valuable stories.

## Acceptance Criteria

- [ ] A `Device` Aggregate Root exists in `domain/model`, implementing `AggregateRoot<DeviceId>`,
      with a `DeviceId` identity, a name, a `DeviceType`, a `Periodicity`, and a
      `lastReplacedAt` date.
- [ ] `Device` can only be constructed through a `DeviceFactory` — the constructor itself is
      not public, matching DD-008.
- [ ] `Device.equals()`/`hashCode()` compare identity (`DeviceId`) only; a separate
      `sameAs(Object)` method compares business-meaningful field values, matching DD-009.
- [ ] `DeviceId` and `DeviceType` exist as Value Objects in `domain/model`, self-validating
      in their constructors (e.g. `DeviceType` rejects a blank/null label).
- [ ] All new classes are built test-first (TDD), with unit tests covering construction,
      validation failures, identity equality vs. value equality, following the same
      structure and coverage bar already set by `PeriodicityTest`.
- [ ] `mvn verify` passes, including the existing JaCoCo coverage gate (DD-010).
