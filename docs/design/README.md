# System design

Setup is split into small Java classes for validation, deck creation, dealing, and turn order. We have not built the interface yet.

| Class | Responsibility | Collaborators |
| --- | --- | --- |
| PlayerSetup | Enforce player count | None |
| PlayerNames | Check name count, trim/default names | None |
| PlayerInitializer | Create ordered players with unique IDs | PlayerSetup, PlayerNames, Player |
| Player | Store ID, name, alive state, copied initial hand | None |
| DeckSetup | Validate/copy supplied cards | None |
| StandardDeck | Create fresh 56-card inventory | None |
| CardDistribution | Separate special cards, validate inventory, shuffle/deal, assemble pile | PlayerSetup, DeckSetup |
| TurnInitialization | Validate starting index and construct cyclic order | PlayerSetup |
| GameSetup | Coordinate setup and align dealt hands with players | PlayerInitializer, StandardDeck, DeckSetup, CardDistribution, TurnInitialization |

Getters return copies so callers cannot change the stored lists. Player is currently immutable and always alive; elimination still needs to be added and tested. Card types use string IDs, which should stay the same when we translate displayed names.

## Proposed interface design
Swing is one option; we still need to choose as a team. A command-line interface is also allowed for this project. The view should display the game, the controller should handle input, and domain classes should apply the rules. The turn engine will need to track the current player, draw/discard piles, pending actions, and whether the game is over. Agree on its public methods before splitting up the work.

Detailed designs: [setup](game-setup.md), [turn initialization](turn-initialization.md), [deck](standard-deck.md), [i18n](i18n-plan.md).
