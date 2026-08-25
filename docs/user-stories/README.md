# User Stories

## Workflow

Every non-trivial change follows the same sequence, in order:

1. **User Story** — the need, written from the Product Owner's perspective
   (`docs/product-owner.md`), with the business justification and acceptance criteria.
2. **GitHub issue** — the US turned into a trackable issue.
3. **Design** — for significant choices, a Design Decision (DD) recorded in
   `docs/OOD/design-decisions.md` *before* any code is written.
4. **Implementation** — only after the design is agreed.

## Phase 0 — Foundations

| ID | Title |
|----|-------|
| [US001](phase-0-foundations/US001-initialize-git-repository.md) | Initialize Git Repository |
| [US002](phase-0-foundations/US002-branch-protection-rules.md) | Branch Protection Rules |
| [US003](phase-0-foundations/US003-ci-pipeline-skeleton.md) | CI Pipeline Skeleton |
| [US004](phase-0-foundations/US004-secret-scanning-gitleaks.md) | Secret Scanning with Gitleaks |

## Phase 1 — Analysis

| ID | Title |
|----|-------|
| [US005](phase-1-analysis/US005-object-oriented-analysis.md) | Object-Oriented Analysis (OOA) |
| [US006](phase-1-analysis/US006-object-oriented-design.md) | Object-Oriented Design (OOD) |

## Phase 2 — Implementation

| ID | Title |
|----|-------|
| [US007](phase-2-implementation/US007-production-database.md) | Production Database |
| [US008](phase-2-implementation/US008-device-aggregate.md) | Device Aggregate |
| [US009](phase-2-implementation/US009-user-and-role.md) | User Aggregate with Role |
| [US010](phase-2-implementation/US010-operating-room-aggregate.md) | OperatingRoom Aggregate |
| [US011](phase-2-implementation/US011-device-placement-aggregate.md) | DevicePlacement Aggregate |
| [US012](phase-2-implementation/US012-shift-and-assignment.md) | Shift and ShiftAssignment Aggregates |
| [US013](phase-2-implementation/US013-checklist-execution.md) | ChecklistExecution Aggregate |
| [US014](phase-2-implementation/US014-room-readiness-status.md) | Room Readiness Status (Read Model) |
