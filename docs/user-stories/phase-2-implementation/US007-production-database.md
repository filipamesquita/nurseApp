# US007 — Production Database

*As the Product Owner, I want nurseApp to run on a persistent, production-grade database
instead of an in-memory one, so that the application can actually be deployed and operated
outside a developer's local machine.*

## Context

The application currently only runs against H2 in-memory (`spring.datasource.url=jdbc:h2:mem:demo`
in `application.properties`). H2 in-memory is appropriate for local development and the
automated test suite, but every restart wipes all data — devices, shifts, checklist
executions, maintenance reports. That is a blocker for any environment beyond a developer's
own machine, not yet a data-loss incident in a live environment.

## Acceptance Criteria

- A production-grade relational database is selected and the choice, context, and rationale
  are recorded as a Design Decision (DD) in `docs/OOD/design-decisions.md`, per the
  Ports & Adapters boundary already established (DD-004) — the choice must not require
  changes to `domain/` or `application/`.
- H2 in-memory remains the configuration used for the automated test suite and local
  development; it is not removed.
- The application has a distinct configuration profile for the production database
  (connection settings externalized, not hardcoded), separate from the dev/test profile.
- The change is delivered as its own GitHub issue, designed before implementation, per the
  workflow in `docs/user-stories/README.md`.
