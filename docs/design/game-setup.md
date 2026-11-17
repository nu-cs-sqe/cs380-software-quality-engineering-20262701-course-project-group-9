# Game setup integration

Combine PRs #8, #10, #11, #13, and #14 on this dependent branch; preserve their original commits and reviews. Main remains unchanged.

As a player, I want validated players, dealt hands, draw pile, and starting turn initialized together so the first turn can begin.

GameSetup accepts player count, ordered names, a supplied deck, and starting index. It composes PlayerInitializer, DeckSetup, CardDistribution, and TurnInitialization. Player gains an overloaded constructor accepting a defensively copied starting hand; the existing constructor still creates an empty hand. No duplicate player validation or dealing algorithm.

Acceptance: 2 through 5 players; normalized names and IDs match hand/turn indices; each player alive with seven ordinary cards and one Defuse; draw pile has N-1 kittens; invalid setup throws; returned lists cannot change stored setup. UI, standard deck construction, turn advancement, and card effects are separate work.

## Integration test plan
Use real collaborators (no mocks) at both player-count boundaries and intermediate counts. Check cross-component ID/name/hand alignment, card conservation, nonzero starting index, invalid counts/names/decks/start, and defensive copies. Run all unit/integration tests and qualityCheck. Keep test-first commits and actual red/green results.

## BVA
| Input/API | Cases | Implemented |
| --- | --- | --- |
| GameSetup count | 1, 2, 3, 4, 5, 6 | Yes |
| starting index | -1, 0, N-1, N | Yes |
| names | wrong length, whitespace, empty default | Yes |
| deck | null, empty, insufficient, valid | Yes |
| getters | players/hands/draw/order defensive copies | Yes |
| Player hand constructor | empty, eight cards, source/returned mutation | Yes |

## Validation
Actual red commit 1f4ba10: all 12 new integration cases failed against stubs. Green run: 83 tests pass; Checkstyle and SpotBugs pass; JaCoCo 121/121 lines and 52/52 branches; PIT 59/62 killed. Two surviving mutations remove existing shuffles; the third replaces Player.isAlive with true, equivalent while Player has no elimination behavior. No exclusions added. Player and PlayerInitializer are final to satisfy constructor safety checks. This is setup against a supplied deck, not yet a playable application.
