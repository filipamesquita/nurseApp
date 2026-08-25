# US011 — DevicePlacement Aggregate

*As the Product Owner, I want the system to record which device is in which operating room,
and since when, so that a checklist can say "the Airway device, verified in room Coral", and
that link survives the device later being moved to another room.*

## Context

Per `docs/OOD/design-decisions.md` (DD-012), `Device` and `OperatingRoom` do not reference
each other directly. `DevicePlacement` is the fact that links them: `deviceId`, `roomId`,
`since` (when the placement started). "What device is currently in room Coral?" is answered
by querying the active `DevicePlacement` for that room, not a field on `Device` or
`OperatingRoom`.

Requires `Device` (US008), `DeviceId`, `OperatingRoom` (US010), `RoomId` to already exist.
Persistence (`DevicePlacementRepositoryPort` and its adapter) and the web/API layer are
explicitly out of scope — this US only scaffolds the domain aggregate.

## Acceptance Criteria

- [ ] A `DevicePlacementId` Value Object exists in `domain/model`, self-validating.
- [ ] A `DevicePlacement` Aggregate Root exists in `domain/model`, implementing
      `AggregateRoot<DevicePlacementId>`, with identity, a `DeviceId`, a `RoomId`, and a
      `since` date/timestamp.
- [ ] `DevicePlacement` can only be constructed through a `DevicePlacementFactory` —
      package-private constructor, matching DD-008.
- [ ] `DevicePlacement.equals()`/`hashCode()` compare identity only; `sameAs(Object)`
      compares business-meaningful field values, matching DD-009.
- [ ] All new classes are built test-first (TDD), matching the coverage bar set by
      `PeriodicityTest`.
- [ ] `mvn verify` passes, including the JaCoCo coverage gate (DD-010).
