# US012 — Shift and ShiftAssignment Aggregates

*As the Product Owner, I want the system to know that Nurse A is working the morning shift
and has been assigned to room Coral, so that a checklist performed by Nurse A can be
understood as happening in the context of that shift and that room.*

## Context

Per `docs/OOD/design-decisions.md` (DD-011): `Shift` represents only a time window (`id` +
window/status) — no `nurseId`, no `roomId`. `ShiftAssignment` is a separate Aggregate Root
linking a nurse, a room, and a shift: `id`, `shiftId`, `roomId`, `nurseId`, `assignedById`
(the `HEAD_NURSE` who made the assignment). Multiple `ShiftAssignment` records can share the
same `shiftId`.

Requires `User`/`UserId`/`Role` (US009) and `OperatingRoom`/`RoomId` (US010) to already
exist. Persistence and the web/API layer are explicitly out of scope.

## Acceptance Criteria

- [ ] `ShiftId` and `ShiftAssignmentId` Value Objects exist in `domain/model`,
      self-validating.
- [ ] A `ShiftWindow` Value Object exists (e.g. start/end time or a named period like
      "MORNING"), self-validating.
- [ ] A `ShiftStatus` Value Object exists (e.g. `PLANNED` / `ACTIVE` / `CLOSED`).
- [ ] A `Shift` Aggregate Root exists in `domain/model`, implementing `AggregateRoot<ShiftId>`,
      with identity, a `ShiftWindow`, and a `ShiftStatus` — no `nurseId`/`roomId` fields.
- [ ] A `ShiftAssignment` Aggregate Root exists in `domain/model`, implementing
      `AggregateRoot<ShiftAssignmentId>`, with identity, `shiftId` (`ShiftId`), `roomId`
      (`RoomId`), `nurseId` (`UserId`), `assignedById` (`UserId`).
- [ ] `ShiftAssignment`'s constructor/factory does **not** validate that `assignedById`
      refers to a `User` with role `HEAD_NURSE` — checking another aggregate's state is an
      application-layer concern (a use case looks up the `User` via `UserRepositoryPort`
      before calling `ShiftAssignmentFactory`), not a domain-object invariant. This US only
      scaffolds the aggregate; that check is out of scope until the corresponding use case
      exists.
- [ ] Both aggregates are constructed only through their respective `<Aggregate>Factory`
      classes — package-private constructors, matching DD-008.
- [ ] `equals()`/`hashCode()` compare identity only; `sameAs(Object)` compares
      business-meaningful field values, matching DD-009, for both aggregates.
- [ ] All new classes are built test-first (TDD), matching the coverage bar set by
      `PeriodicityTest`.
- [ ] `mvn verify` passes, including the JaCoCo coverage gate (DD-010).
