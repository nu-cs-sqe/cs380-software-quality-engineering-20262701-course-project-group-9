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

JaCoCo reports all compiled main classes. PIT targets `domain.*` and discovers
`domain.*Test`, matching the actual package rather than the Gradle group name.
PIT runs with its JUnit 5 plugin. Coverage and mutation percentages are reported
without an invented course threshold; review surviving mutants and uncovered
branches when adding features. Empty UI scaffolding contains no compiled class.

These are build/configuration changes, not new game behavior. Existing unit
tests remain the regression suite. Future behavior changes must follow the
course's BVA, draft PR, red/green commits, and teammate-review workflow.

## Verified baseline (November 16, 2026)

The full clean build and qualityCheck passed from IntelliJ: 40 tests, zero Checkstyle or SpotBugs findings, 100% JaCoCo line and branch coverage, and 38/40 PIT mutants killed (95%). The two surviving mutants remove Collections.shuffle calls in CardDistribution; the tests verify distribution constraints but do not establish randomness. No mutants are excluded to hide this gap.

SpotBugs initially reported CT_CONSTRUCTOR_THROW in the four setup classes. Making these concrete classes final resolves the finalizer-subclass risk while preserving constructor validation and all existing tests. They are no longer subclassable. No new gameplay behavior is introduced.

## Current local setup baseline

The synchronized completion branch passed 98 tests, both linters, 131/131 lines, 46/46 branches, and 62/65 killed mutants. The two shuffle-removal survivors remain; Player.isAlive also has an equivalent always-true survivor until elimination is introduced. No mutants are excluded. This supersedes the earlier 40-test tooling baseline for current setup code.
