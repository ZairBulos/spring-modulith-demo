# Spring Modulith

A hands-on project to explore Spring Modulith concepts step by step.

## About this project

A simple newsletter application where content can be published and subscribers get notified. 
The domain is intentionally small so the focus stays on architecture — not business logic.

- **Publications** — create and retrieve published content
- **Subscribers** — manage email subscriptions and deliver notifications

## Agenda

0. Monolith
1. **Spring Modulith - Fundamentals**
2. Spring Modulith - Verify & Documenting Application Modules
3. Spring Modulith - Application Events
4. Spring Modulith - Integration Testing Application Modules

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