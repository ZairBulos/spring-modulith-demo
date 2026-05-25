# Spring Modulith

A hands-on project to explore Spring Modulith concepts step by step.

## About this project

A simple newsletter application where content can be published and subscribers get notified. 
The domain is intentionally small so the focus stays on architecture — not business logic.

- **Publications** — create and retrieve published content
- **Subscribers** — manage email subscriptions and deliver notifications

## Agenda

0. Monolith
1. Spring Modulith - Fundamentals
2. Spring Modulith - Verify & Documenting Application Modules
3. Spring Modulith - Application Events
4. **Spring Modulith - Integration Testing Application Modules**

## Branch: `00-monolith`

A traditional layered app (`controller → service → repository`).
No module boundaries are enforced.

> [!WARNING]
> `PublicationService` directly calls `SubscriberService.notifySubscribers()`.
> 
> Publishing a post shouldn't need to know anything about subscriber notification

## Branch: `01-fundamentals`

### step-01: bounded contexts with package-private visibility

Separated the codebase into two modules: `publications` and `subscribers`.
Internal classes (entity, repository, service) are package-private by default — only what needs to be shared is `public`.

### step-02: add Spring Modulith

Added `spring-modulith-starter-core` dependency. 
The project structure now aligns with Spring Modulith's conventions — each top-level package under the main package is treated as an application module.

### step-03: advanced application modules

Moved internal classes into a nested `internal` sub-package, making the module boundary explicit and intentional.

> [!NOTE]
> The IDE will warn about accessing `internal` types from another module, but the project still compiles and runs.

## Branch: `02-verify-documentation`

### step-04: add spring-modulith-starter-test

Added `spring-modulith-starter-test` to support module verification and documentation generation in tests.

### step-05: verify application modules structure

Added a test that calls `ApplicationModules.verify()` to enforce module boundaries.

> [!WARNING]
> This test fails due to a cyclic dependency between modules:
> - `publications` → `subscribers.SubscriberService`
> - `subscribers` → `publications.Publication`

### step-06: document application modules

Extended `ApplicationModulesTest` to generate PlantUML diagrams using `Documenter`.
Output is written to `target/spring-modulith-docs`.

## Branch: `03-application-events`

### step-07: introduce application events

Added `spring-modulith-events-api` to support application events between modules.

Decoupled `publications` and `subscribers` by replacing the direct service call
with a `PublicationPublished` event published via `ApplicationEventPublisher`.

The `subscribers` module listens via `@ApplicationModuleListener` in a dedicated
`SubscriberListener` — no longer depends on anything from `publications`.

This resolves the cyclic dependency — `ApplicationModulesTest` now passes.

### step-08: durable events

Added `spring-modulith-starter-jpa` to persist events in the `event_publication` table.

Configured `republish-outstanding-events-on-restart` to reprocess incomplete events on startup,
and `completion-mode: archive` to move completed events to `event_publication_archive`.

## Branch: `04-integration-testing`

### step-09: @ApplicationModuleTest

Added an integration test for the `subscribers` module using `@ApplicationModuleTest`.
The module is bootstrapped in isolation — only the beans belonging to that module are loaded.

Uses the `Scenario` API to publish a `PublicationPublished` event and verify 
that the `subscribers` module receives and processes it correctly.
