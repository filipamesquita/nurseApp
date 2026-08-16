# US010 — OperatingRoom Aggregate

*As the Product Owner, I want the system to know about operating rooms — just their identity
and name (e.g. "Coral") — so that later stories can place devices in them, assign nurses to
them, and report their readiness status.*

## Context

`OperatingRoom` knows only about itself: identity and a name/label. Per `docs/OOD/design-
decisions.md` (DD-012), it deliberately does **not** hold a reference to whatever device is
currently inside it — that link is `DevicePlacement` (US011), kept separate so device moves
don't destroy history.

Persistence and the web/API layer are explicitly out of scope.

## Acceptance Criteria

- [ ] A `RoomId` Value Object exists in `domain/model`, self-validating.
- [ ] An `OperatingRoom` Aggregate Root exists in `domain/model`, implementing
      `AggregateRoot<RoomId>`, with identity and a name (self-validating: rejects
      blank/null).
- [ ] `OperatingRoom` can only be constructed through an `OperatingRoomFactory` —
      package-private constructor, matching DD-008.
- [ ] `OperatingRoom.equals()`/`hashCode()` compare identity only; `sameAs(Object)` compares
      business-meaningful field values, matching DD-009.
- [ ] All new classes are built test-first (TDD), matching the coverage bar set by
      `PeriodicityTest`.
- [ ] `mvn verify` passes, including the JaCoCo coverage gate (DD-010).
