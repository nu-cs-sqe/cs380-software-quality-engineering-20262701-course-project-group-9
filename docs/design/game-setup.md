# Game setup integration

Setup integration from PR #15 is on main. This local branch additionally contains the standard-deck work from PR #16, which was merged into the integration branch after main had already received #15.

As a player, I want validated players, dealt hands, draw pile, and starting turn initialized together so the first turn can begin.

GameSetup accepts player count, ordered names, a supplied deck, and starting index. It composes PlayerInitializer, DeckSetup, CardDistribution, and TurnInitialization. Player gains an overloaded constructor accepting a defensively copied starting hand; the existing constructor still creates an empty hand. No duplicate player validation or dealing algorithm.

Acceptance: 2 through 5 players; normalized names and IDs match hand/turn indices; each player alive with seven ordinary cards and one Defuse; draw pile has N-1 kittens; invalid setup throws; returned lists cannot change stored setup. Standard deck construction and the default-deck constructor are present locally; UI, turn advancement, and card effects remain future work.

## Integration test plan
See the [detailed integration plan](../testing/integration-plan.md) for existing and planned scenarios.

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

## Historical validation for PR #15
Actual red commit 1f4ba10: all 12 new integration cases failed against stubs. Green run: 83 tests pass; Checkstyle and SpotBugs pass; JaCoCo 121/121 lines and 52/52 branches; PIT 59/62 killed. Two surviving mutations remove existing shuffles; the third replaces Player.isAlive with true, equivalent while Player has no elimination behavior. No exclusions added. Player and PlayerInitializer are final to satisfy constructor safety checks. This original run used supplied decks; subsequent StandardDeckTest covers automatic decks. The application is not yet playable.
