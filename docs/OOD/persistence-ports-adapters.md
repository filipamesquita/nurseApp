# Persistence — Ports & Adapters

Diagrama de como a persistência SQL está planeada para o nurseApp, seguindo
Ports & Adapters (DD-004 em [design-decisions.md](design-decisions.md)).
Usa `Device`/`DeviceRepositoryPort` como exemplo ilustrativo; o mesmo padrão
aplica-se a cada Aggregate Root (`User`, `OperatingRoom`, `Shift`, ...).

```mermaid
classDiagram
    direction LR

    namespace domain {
        class Device {
            <<Aggregate Root>>
        }
        class DeviceRepositoryPort {
            <<interface>>
            +save(Device) Device
            +findById(DeviceId) Optional~Device~
            +findAll() List~Device~
        }
    }

    namespace application {
        class RegisterDeviceUseCase {
            -DeviceRepositoryPort repository
            +execute(...) Device
        }
    }

    namespace infrastructure_persistence {
        class DeviceDataModel {
            <<@Entity>>
            +Long id
            +String name
            +...
        }
        class DeviceJpaRepository {
            <<interface, Spring Data JPA>>
        }
        class DeviceRepositoryAdapter {
            -DeviceJpaRepository jpaRepository
            -DeviceMapper mapper
            +save(Device) Device
            +findById(DeviceId) Optional~Device~
        }
        class DeviceMapper {
            +toDataModel(Device) DeviceDataModel
            +toDomain(DeviceDataModel) Device
        }
    }

    namespace infrastructure_config {
        class AppConfig {
            <<@Configuration>>
            +deviceRepositoryPort() DeviceRepositoryPort
        }
    }

    class H2_or_Postgres {
        <<database>>
    }

    RegisterDeviceUseCase --> DeviceRepositoryPort : depends on (port only)
    DeviceRepositoryPort <|.. DeviceRepositoryAdapter : implements
    DeviceRepositoryAdapter --> DeviceJpaRepository : delegates SQL
    DeviceRepositoryAdapter --> DeviceMapper : uses
    DeviceMapper --> Device : domain object
    DeviceMapper --> DeviceDataModel : JPA entity
    DeviceJpaRepository --> DeviceDataModel : manages
    DeviceJpaRepository --> H2_or_Postgres : JDBC
    AppConfig --> DeviceRepositoryAdapter : wires
    AppConfig --> DeviceRepositoryPort : binds
```

**Regra de dependência:** as setas de `application` e `domain` apontam sempre
para dentro — nunca para `infrastructure`. Só `infrastructure/persistence`
depende de JPA/Hibernate.

Estado atual (2026-08-17): o padrão está decidido e as pastas existem
(`domain/repository`, `infrastructure/persistence`), mas nenhum port, adapter,
data model ou mapper concreto foi ainda implementado — ver
[US007 — Production Database](../user-stories/phase-2-implementation/US007-production-database.md)
para a base de dados de produção, ainda por fazer.
