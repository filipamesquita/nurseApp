# nurseApp — Architecture

Standing architectural rules for this project. Where `docs/OOD/design-decisions.md` records
the individual *choices* made and why, this document records the *rules* those choices must
obey going forward.

## 1. Layers (Onion / Clean Architecture)

Dependencies only point inward. Outer layers depend on inner layers through interfaces
("ports"); inner layers never depend on outer ones or on frameworks.

| Layer | Contains | Depends on |
|---|---|---|
| **Domain** (`domain/`) | Aggregates, entities, value objects, domain services (`Device`, `User`, `OperatingRoom`, `Shift`, `ChecklistExecution`, `MaintenanceReport`, `Periodicity`, `MaintenanceDueService`) and outbound **ports** (`domain/repository`: `DeviceRepositoryPort`, `ShiftRepositoryPort`, etc.) | nothing — pure Java, no framework of any kind (see §3.1) |
| **Application** (`application/`) | Use cases that orchestrate domain objects and call the ports (`GetDeviceListUseCase`, `StartShiftUseCase`, `RunChecklistUseCase`, ...) | Domain only |
| **Infrastructure** (`infrastructure/`) | Outbound: JPA repository adapters, data models, mappers (`infrastructure/persistence`); Spring wiring, the maintenance-warning scheduler (`infrastructure/config`) | Application (implements its ports) |
| **Interfaces** (`interfaces/`) | Inbound: Spring `@RestController`s, DTOs, mappers (`interfaces/web`) | Application (calls its use cases) |

## 3. Three models, never mixed

A recurring Clean Architecture mistake is letting one class serve two layers. This project
keeps three distinct model types, mapped explicitly at the boundaries:

- **Domain object** (`domain/model/`) — pure business object, enforces its own invariants (as
  `Periodicity` already does). Pure Java only — see §3.1.
- **Data model** (`infrastructure/persistence/datamodel/`) — `@Entity` classes shaped for
  Hibernate/JPA, named `*DataModel` (e.g. `DeviceDataModel`) rather than `*Entity` to avoid
  clashing with the DDD sense of "entity" used for domain objects like `Device`/`Shift`. Can
  be denormalized or structured differently from the domain object.
- **DTO** (`interfaces/web/dto/`) — wire format returned to clients, shaped for the API
  contract (HAL+JSON, `_links`, pagination — see DD-002 in `design-decisions.md`),
  independent of both the domain and the data model.

Mapping between them is an explicit, testable step (`mapper` classes) — never shared
inheritance, never a "smart" object doing double duty.

### 3.1 The domain layer is pure Java — no frameworks, including Lombok

The `domain/` layer (business rules) must have **zero framework dependencies of any kind**.
No Spring, no JPA/Hibernate, no Jackson, no HTTP client — and **no Lombok**. Domain classes
hand-write their constructors (where they already enforce invariants), getters,
`equals`/`hashCode`, and `toString` in plain Java rather than generating them with
`@Getter`/`@Value`/`@Data` etc.

The point is that the business rules stay independent of every tool the project happens to
use today: a domain class should read the same, and compile, if Spring and Lombok were
removed from the build tomorrow. Lombok is a compile-time annotation processor —
convenient, but still an external dependency the domain would be coupled to, so it's
excluded here on the same principle as the rest.

**This ban is scoped to `domain/` only.** Every other layer may use frameworks freely —
Lombok in `infrastructure/persistence/datamodel` JPA data models, `interfaces/web/dto` DTOs,
adapters, and config is fine and encouraged where it cuts boilerplate. Purity is a property
we buy for the business rules specifically, not a project-wide style rule.
