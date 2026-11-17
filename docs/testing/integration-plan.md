# Integration testing plan

Status: documented locally for team review. Setup integration tests exist; gameplay/UI cases below are planned. This document has not been posted to GitHub Projects.

## Scope and approach
Use real domain collaborators for in-memory setup. Mocking them would hide the interactions under test. Use mocks/stubs only for future external input, display, clock, or persistence boundaries when isolation is needed. Do not mock the dealing logic in tests intended to verify real dealing.

| ID | Boundary/scenario | Expected result | Test/status |
| --- | --- | --- | --- |
| IT01 | Real setup with 2,3,4,5 players | IDs/names/hands align, alive players, eight cards each, N-1 kittens | GameSetupTest.initializesRealComponentsTogether; implemented |
| IT02 | Real standard deck, first/last starting index | All card frequencies conserved except removed surplus kittens; correct current player | StandardDeckTest.startsGameWithRealDeck; implemented |
| IT03 | Count 1/6, start -1/N, mismatched names, invalid supplied deck | Reject setup without publishing a usable game | GameSetupTest; StandardDeckTest; implemented |
| IT04 | Caller mutates names/deck/getter lists | Stored players/hands/order/pile unchanged | GameSetupTest.protectsStateAndUsesDefaultName; implemented |
| IT05 | Controller submits valid/invalid setup | Valid view shown; localized validation shown for invalid input | Planned; UI absent |
| IT06 | Play/pass then ordinary draw | Hand/pile/current player updated once, card conservation maintained | Planned; turn engine absent |
| IT07 | Kitten with/without Defuse, reinsertion at start/end | Defuse consumed and kitten reinserted, or player eliminated | Planned |
| IT08 | Attack/Skip and eliminated-player traversal | Correct outstanding turn count and next living player | Planned |
| IT09 | Final elimination | Exactly one winner; further game actions rejected | Planned |
| IT10 | Locale change and non-ASCII names | Same domain state, translated display, names preserved | Planned |

## Execution and evidence
Run ./gradlew test --tests domain.GameSetupTest --tests domain.StandardDeckTest for setup integration. These tests intentionally run in the ordinary JUnit task so CI cannot omit them. Run ./gradlew clean build qualityCheck --continue --no-daemon for all checks. Reports: build/reports/tests/test, build/reports/jacoco/test, build/reports/pitest, build/reports/checkstyle, build/reports/spotbugs.

Latest verified local full run: 98 tests passing, zero lint findings, 100% line/branch coverage, 62/65 killed mutants. The two shuffle-call survivors mean the suite does not establish shuffle randomness; the Player.isAlive survivor is equivalent while no elimination method exists. Coverage percentages alone do not prove gameplay correctness.

For future randomized gameplay, inject a controlled shuffle/random source at the relevant boundary and test invariants plus deterministic scenarios; avoid flaky assertions that a random deck must differ on every run. Record seed and operation sequence when a randomized test fails.

## Review and completion
Before implementation: agree interfaces, document BVA, open draft PR, commit/run failing tests, implement minimal behavior, then run the suite. Each integration bug should yield a reproducing test and linked fix. A feature is complete only when its relevant scenarios pass, teammates review it, and planned cases are not mislabeled as implemented. Team still needs to discuss this plan, assign owners for IT05–IT10, and copy the board item into GitHub Projects.
