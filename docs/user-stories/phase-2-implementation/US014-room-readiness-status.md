# US014 — Room Readiness Status (Read Model)

*As Nurse A or an Admin, I want to see that room Coral is green and ready for the first
surgery, so that I can trust the checklist I just completed is actually reflected in the
application.*

## Context

This is the closing story of the walking skeleton: it doesn't introduce a new persisted
aggregate, only a read/query that combines existing facts — the active `DevicePlacement`(s)
for a room (US011) and the most recent `ChecklistExecution` (US013) for each of those
devices — into a `RoomReadinessStatus` (e.g. `READY` / `NOT_READY` / `UNKNOWN`).

This closes the loop described by the Product Owner: "Enfermeiro A ... fez a checklist ...
está a sala Coral está verde e pronta a ser utilizada."

Requires US008–US013 to already exist. Persistence adapters for `Device`, `OperatingRoom`,
`DevicePlacement`, `ChecklistExecution` are explicitly out of scope for this US — an
in-memory or stubbed port implementation is acceptable to prove the domain logic, per
`docs/OOD/design-decisions.md` (DD-004, Ports & Adapters — the read logic depends only on
port interfaces, not concrete persistence).

## Acceptance Criteria

- [ ] A `RoomReadinessStatus` Value Object exists in `domain/model` with values `READY` /
      `NOT_READY` / `UNKNOWN`.
- [ ] A domain service (e.g. `RoomReadinessService`) computes a room's `RoomReadinessStatus`
      given its `RoomId`, using the active `DevicePlacement`(s) and the latest
      `ChecklistExecution` per placed device — `READY` only if every currently placed device's
      latest check is `OK`.
- [ ] The rule is unit-tested against ports (mocked), not a real database, matching DD-004's
      testability rationale.
- [ ] All new classes are built test-first (TDD), matching the coverage bar set by
      `PeriodicityTest`.
- [ ] `mvn verify` passes, including the JaCoCo coverage gate (DD-010).
