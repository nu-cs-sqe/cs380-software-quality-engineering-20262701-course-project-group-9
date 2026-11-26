# Quality tools

Run `./gradlew clean build qualityCheck --continue` with JDK 11.
The existing Build and test CI job runs the same checks on pull requests and
main pushes and uploads reports even when a check fails.

| Tool | Task | Report |
|---|---|---|
| JUnit | `test` | `build/reports/tests/test/index.html` |
| Checkstyle | `checkstyleMain checkstyleTest` | `build/reports/checkstyle/` |
| SpotBugs | `spotbugsMain spotbugsTest` | `build/reports/spotbugs/` |
| JaCoCo | `jacocoTestReport` | `build/reports/jacoco/test/html/index.html` |
| PIT | `pitest` | `build/reports/pitest/index.html` |

Checkstyle uses a project-specific subset of the Sun Java conventions in
`config/checkstyle/checkstyle.xml`. It enforces naming, braces, whitespace,
imports, 120-column lines, and basic correctness rules. Static wildcard imports
for JUnit assertions are allowed. Linters fail the build on reported violations;
no blanket exclusions or ignored failures are configured.

JaCoCo reports all compiled main classes. PIT targets the domain classes and `ui.ConsoleGame`. It runs the domain and console tests with JUnit 5. The launcher is exercised separately through Gradle.

These are build/configuration changes, not new game behavior. Existing unit
tests remain the regression suite. Future behavior changes must follow the
course's BVA, draft PR, red/green commits, and teammate-review workflow.

## Verified baseline (November 16, 2026)

The full clean build and qualityCheck passed from IntelliJ: 40 tests, zero Checkstyle or SpotBugs findings, 100% JaCoCo line and branch coverage, and 38/40 PIT mutants killed (95%). The two surviving mutants remove Collections.shuffle calls in CardDistribution; the tests verify distribution constraints but do not establish randomness. No mutants are excluded to hide this gap.

SpotBugs initially reported CT_CONSTRUCTOR_THROW in the four setup classes. Making these concrete classes final resolves the finalizer-subclass risk while preserving constructor validation and all existing tests. They are no longer subclassable. No new gameplay behavior is introduced.

## Earlier local setup baseline

Before elimination and playable turns, setup passed 98 tests and both linters. JaCoCo covered 131/131 lines and 46/46 branches. PIT killed 62/65 mutants. These numbers describe that earlier build.

## Local turn and console results (November 24, 2026)

`./gradlew clean build qualityCheck --continue --console=plain` passed all 125 tests. Checkstyle and SpotBugs each reported zero findings in main and test code. JaCoCo covered 270/274 lines, 117/118 branches, and 121/123 units of cyclomatic complexity. The two missed complexity units are in `Main.main` and `WinCondition.getWinner`.

`ConsoleGame` covered 64/64 lines, 34/34 branches, and 25/25 complexity units. Its tests killed all 39 console mutants. Across the project PIT recorded 145 kills, one timeout in `Game.advance`, and two surviving shuffle removals in `CardDistribution`. The tests do not yet distinguish a shuffled deal from one that preserves the input order.
