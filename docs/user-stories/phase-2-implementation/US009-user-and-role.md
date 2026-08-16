# US009 — User Aggregate with Role

*As the Product Owner, I want the system to know about the people who use it — nurses, head
nurses, admins — so that a checklist or shift assignment can record **who** did it.*

## Context

No `Nurse` aggregate exists, nor will one: per `docs/OOD/design-decisions.md` (DD-011,
rejected alternative), a single `User` aggregate with a `Role` value object covers `NURSE`,
`HEAD_NURSE`, and `ADMIN`, since nothing yet differs structurally between them.

This US scaffolds `User` as an Aggregate Root and `Role` as its Value Object, following the
same conventions already established by `Periodicity` (TDD, self-validating Value Objects)
and the kernel (DD-007, DD-008, DD-009).

Persistence, authentication, and the web/API layer are explicitly out of scope.

## Acceptance Criteria

- [ ] A `Role` Value Object exists in `domain/model` with exactly three values: `NURSE`,
      `HEAD_NURSE`, `ADMIN` (an enum implementing `ValueObject`, or equivalent).
- [ ] A `UserId` Value Object exists in `domain/model`, self-validating.
- [ ] A `User` Aggregate Root exists in `domain/model`, implementing `AggregateRoot<UserId>`,
      with identity, a name, and a `Role`.
- [ ] `User` can only be constructed through a `UserFactory` — package-private constructor,
      matching DD-008.
- [ ] `User.equals()`/`hashCode()` compare identity only; `sameAs(Object)` compares
      business-meaningful field values, matching DD-009.
- [ ] All new classes are built test-first (TDD), matching the coverage bar set by
      `PeriodicityTest`.
- [ ] `mvn verify` passes, including the JaCoCo coverage gate (DD-010).
