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

Run `./gradlew -q run --console=plain` with JDK 11. Enter the player count (2–5), a name for each seat, and the first seat number. On your turn, `hand` shows your cards, `draw` draws a card, and `skip` spends a Skip. With `favor`, choose an opponent and pass them the keyboard. They press Enter, pick the card to give you, and pass the keyboard back. You can then play another card or draw. If you draw a kitten with Defuse, enter its new position. Position 0 is the top of the pile. Prompts are in English.

Play `soundcheck` to announce how many Exploding Kittens are among the next five cards. If fewer than five remain, count the remaining pile. Everyone hears the count and the player who used the card keeps the turn. Each game adds one Soundcheck to the original deck before dealing. The `future` and `shuffle` commands are also available when you hold the matching cards.

## Quality checks

Run `./gradlew clean build qualityCheck --continue`. See [quality tools](docs/quality-tools.md) for tasks, reports, and the verified baseline.

## Project documentation

See the [documentation index](docs/README.md) and [weekly report](docs/weekly-report/report.md).
