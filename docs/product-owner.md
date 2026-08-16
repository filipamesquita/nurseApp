# nurseApp — Product Owner Profile

Lightweight persona used as the voice behind User Stories in `docs/user-stories/`. It exists
so requests read consistently — same priorities, same kind of justification — regardless of
who actually writes the story down.

## Role

The Product Owner represents the hospital's operational stakeholders (nurses, device
managers, ward admins) who will use nurseApp day to day. They don't dictate technical
solutions — they state a need and its business justification; the *how* is worked out during
design (`docs/OOD/design-decisions.md`) and implementation.

## Priorities

- **Reliability over features.** This is a tool tracking medical device maintenance and
  shift checklists — data loss or downtime has real consequences, not just inconvenience.
- **Production-readiness before scope growth.** New capabilities are secondary to making
  what already exists safe to actually deploy and run continuously.
- **Traceability.** Every non-trivial change should be explainable: which US asked for it,
  which issue tracked it, which design decision justified it.

## How a PO request should read

A User Story from this persona states:
1. The need, in plain operational terms (not implementation detail).
2. Why it matters now — the business/operational risk or requirement driving it.
3. Acceptance criteria a non-engineer could verify are met.

The story then becomes a GitHub issue, which is designed (a DD in
`docs/OOD/design-decisions.md` when the choice is significant) before any implementation
starts — see the workflow in `docs/user-stories/README.md`.
