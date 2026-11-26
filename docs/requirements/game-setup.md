# Game setup requirements

## User story
As a player, I want setup to create the players, distribute cards, and select a starting turn so that play can begin without manually organizing the deck.

The criteria below describe the current implementation. The [Canvas rule comparison](game-rules.md) and [issue #30](https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/issues/30) track the differences in player count, deck, hand size, and the timing of spare Defuses.

## Acceptance criteria
| ID | Requirement | Evidence |
| --- | --- | --- |
| S01 | Accept 2–5 players; reject 1 and 6 | PlayerSetupTest; GameSetupTest; StandardDeckTest |
| S02 | Preserve player order, trim names, default empty names, assign IDs 0 through N-1 | PlayerInitializerTest; GameSetupTest |
| S03 | Every player starts alive with eight cards: seven ordinary and one Defuse; no kitten in an initial hand | GameSetupTest; StandardDeckTest |
| S04 | Draw pile has exactly N-1 kittens; preserve all other cards under current Defuse policy | StandardDeckTest.startsGameWithRealDeck |
| S05 | Shuffle ordinary cards before dealing and draw pile after assembling | Calls present in CardDistribution; distribution tests do not prove randomness |
| S06 | Starting index must be 0 through N-1; cyclic order starts at that index | TurnInitializationTest |
| S07 | Returned lists and caller-owned input lists cannot mutate stored setup | GameSetupTest; PlayerInitializerTest |
| S08 | Default constructor uses the full 56-card deck | StandardDeckTest |

## Use case: start a game
Actor: player. Precondition: a future interface has collected count, ordered names, and starting index. Current domain API can be invoked directly by tests.

1. Interface invokes GameSetup(count, names, start).
2. System validates player count/names and starting index.
3. System creates the standard deck, separates special cards, shuffles ordinary cards, and deals hands.
4. System assembles/shuffles the remaining pile with N-1 kittens.
5. System returns aligned players, initial hands, draw pile, and cyclic turn order.

Alternative: an invalid count, mismatched name count, or invalid starting index raises IllegalArgumentException. A future controller should display a localized correction prompt and request valid input; that UI behavior is not implemented.

Postcondition: setup data is ready for a future turn engine. No turn has advanced and no winner has been selected. Supplied-deck API additionally rejects null/blank cards and insufficient inventory. Null player-name inputs are outside the currently documented valid-input contract.
