# Exploding Kittens

[![Build](https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/actions/workflows/build.yml)

## Current team

- Hanwen (Austin) Liu
- Isaiah Hashimoto

## Dependencies
- JDK 11
- JUnit 5.10
- Gradle 8.10

## Exceptions
### Code Coverage
N/A
### Mutation Testing
N/A
### Others
N/A

## Play locally

Run `./gradlew -q run --console=plain` with JDK 11. Enter the player count (2–5), a name for each seat, and the first seat number. On your turn enter `hand` to see your cards, `draw` to draw, or `skip` to spend a Skip. If you draw a kitten with Defuse, enter its new position. Position 0 is the top of the pile. Prompts are in English. Other action cards have no playable commands yet.

## Quality checks

Run `./gradlew clean build qualityCheck --continue`. See [quality tools](docs/quality-tools.md) for tasks, reports, and the verified baseline.

## Project documentation

See the [documentation index](docs/README.md) and [weekly report](docs/weekly-report/report.md).
