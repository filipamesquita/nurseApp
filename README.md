# nurseApp

## About this project

I'm an aspiring software developer, transitioning from a career as a nurse. This project is
my way of putting what I've learned in my course into practice — not a toy exercise, but an
attempt to solve real problems I ran into during my time working as a nurse.

For now, nurseApp is focused on one thing: **helping nurses** manage the parts of a shift
that are easy to lose track of under pressure — knowing which devices are due for
maintenance, running shift checklists, and reporting issues — instead of relying on memory
or paper. The scope may grow, but that's the starting point and the reason it exists.

It's also a deliberate learning exercise in software engineering practice: Domain-Driven
Design, Onion/Clean Architecture, and test-driven development, rather than just "make it
work" code.

## Documentation

- [`docs/architecture.md`](docs/architecture.md) — standing architectural rules (layers,
  dependency direction, the three-model separation).
- [`docs/OOD/design-decisions.md`](docs/OOD/design-decisions.md) — Design Decision (DD)
  records: the individual choices made and why.
- [`docs/product-owner.md`](docs/product-owner.md) — the Product Owner persona behind the
  User Stories.
- [`docs/user-stories/`](docs/user-stories/README.md) — User Stories by phase, and the
  PO → issue → design → implementation workflow they follow.

## Tech stack

- Java 17, Spring Boot 3
- Spring Data JPA (H2 in-memory for now — see
  [US007](docs/user-stories/phase-2-implementation/US007-production-database.md) for the
  production database story)
- Maven

## Feedback welcome

This is a learning project and very much a work in progress — suggestions, questions, and
critiques on the design or the code are genuinely welcome.
