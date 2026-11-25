# Integration testing plan

The setup and console turn tests pass locally. The table records the scenarios covered and the work still open. We still need to review this plan together, assign owners, and post it to the project board.

## Scope and approach
Run setup tests with the real classes so we check how they work together, including actual dealing. Use mocks or stubs for outside dependencies, such as input or storage, when needed.

| ID | Boundary/scenario | Expected result | Test/status |
| --- | --- | --- | --- |
| IT01 | Real setup with 2,3,4,5 players | IDs/names/hands align, alive players, eight cards each, N-1 kittens | GameSetupTest.initializesRealComponentsTogether; implemented |
| IT02 | Real standard deck, first/last starting index | All card frequencies conserved except removed surplus kittens; correct current player | StandardDeckTest.startsGameWithRealDeck; implemented |
| IT03 | Count 1/6, start -1/N, mismatched names, invalid supplied deck | Reject setup without publishing a usable game | GameSetupTest; StandardDeckTest; implemented |
| IT04 | Caller mutates names/deck/getter lists | Stored players/hands/order/pile unchanged | GameSetupTest.protectsStateAndUsesDefaultName; implemented |
| IT05 | Controller submits valid/invalid setup | Valid view shown; localized validation shown for invalid input | ConsoleGameTest covers count, names, starting seat and English retry prompts. Translations pending. |
| IT06 | Play/pass then ordinary draw | Hand/pile/current player updated once, card conservation maintained | GameTest and ConsoleGameTest cover ordinary draw, hand changes and turn advancement. |
| IT07 | Kitten with/without Defuse, reinsertion at start/end | Defuse consumed and kitten reinserted, or player eliminated | GameTest and ConsoleGameTest cover both outcomes and reinsertion boundaries. |
| IT08 | Attack/Skip and eliminated-player traversal | Correct outstanding turn count and next living player | Skip and traversal tested. Attack and outstanding turns pending. |
| IT09 | Final elimination | Exactly one winner; further game actions rejected | GameTest verifies action rejection. ConsoleGameTest verifies the winner message and that input consumption stops. |
| IT10 | Locale change and non-ASCII names | Same domain state, translated display, names preserved | Planned |

## Execution and evidence
Run ./gradlew test --tests domain.GameSetupTest --tests domain.StandardDeckTest for setup integration. These tests intentionally run in the ordinary JUnit task so CI cannot omit them. Run ./gradlew clean build qualityCheck --continue --no-daemon for all checks. Reports: build/reports/tests/test, build/reports/jacoco/test, build/reports/pitest, build/reports/checkstyle, build/reports/spotbugs.

Run `./gradlew test --tests ui.ConsoleGameTest` for the console scenarios. At `9c07d6c` the initial eight cases compiled and failed on assertions. They passed with the implementation in `caf463f`. Regression tests added after mutation analysis bring the full local suite to 125. A full game through `./gradlew -q run --console=plain` reached a winner after both players used Defuse. See [quality results](../quality-tools.md).

For future randomized gameplay, inject a controlled shuffle/random source at the relevant boundary and test invariants plus deterministic scenarios; avoid flaky assertions that a random deck must differ on every run. Record seed and operation sequence when a randomized test fails.

## Review and completion
Before implementation: agree interfaces, document BVA, open draft PR, commit/run failing tests, implement minimal behavior, then run the suite. Each integration bug should yield a reproducing test and linked fix. Mark a feature done after its tests pass and a teammate reviews it. We still need owners for IT05–IT10 and a board entry. For an A, the grading rubric requires completed integration testing on at least two main features (page 3).
