# US013 — ChecklistExecution Aggregate

*As Nurse A, I want to record that I checked the Airway device in room Coral at 08:05 and
found it working correctly, so that there's a durable record of that verification.*

## Context

`ChecklistExecution` is the event at the center of the walking skeleton: a nurse, a device
(via its current `DevicePlacement`), a room, a timestamp, and a result. Per
`docs/OOD/design-decisions.md`, it references `roomId` and `performedById` directly (see
DD-011's note that `ChecklistExecution` is unaffected by the `Shift`/`ShiftAssignment` split).

For this first walking-skeleton slice, keep it minimal: one `CheckItem` (or a single overall
result) is enough — a fuller checklist structure (multiple check items per execution) can be
added later without breaking this shape.

Requires `Device`/`DeviceId` (US008), `OperatingRoom`/`RoomId` (US010), `User`/`UserId`
(US009) to already exist. Persistence and the web/API layer are explicitly out of scope.

`roomId` is intentionally stored directly on `ChecklistExecution`, alongside `deviceId`,
even though `DevicePlacement` (US011) already links device and room. This is a deliberate,
event-sourcing-style denormalization: a `ChecklistExecution` is a record of "what was true at
the moment of the check" (which room the nurse was physically standing in), which must
survive unchanged even if the device is placed in a different room later. It is not a
substitute for `DevicePlacement`, and does not need to be validated against the device's
placement at write time for this US — US014 is the first consumer that reasons about the two
together.

## Acceptance Criteria

- [ ] A `ChecklistExecutionId` Value Object exists in `domain/model`, self-validating.
- [ ] A `CheckStatus` Value Object exists (e.g. `OK` / `FAILED`).
- [ ] A `ChecklistExecution` Aggregate Root exists in `domain/model`, implementing
      `AggregateRoot<ChecklistExecutionId>`, with identity, `deviceId` (`DeviceId`), `roomId`
      (`RoomId`), `performedById` (`UserId`), a timestamp, and a `CheckStatus`.
- [ ] `ChecklistExecution` can only be constructed through a `ChecklistExecutionFactory` —
      package-private constructor, matching DD-008.
- [ ] `ChecklistExecution.equals()`/`hashCode()` compare identity only; `sameAs(Object)`
      compares business-meaningful field values, matching DD-009.
- [ ] All new classes are built test-first (TDD), matching the coverage bar set by
      `PeriodicityTest`.
- [ ] `mvn verify` passes, including the JaCoCo coverage gate (DD-010).
