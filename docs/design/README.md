# System design

The current Java domain implementation separates validation, deck construction, dealing, and coordination. It has no runnable view/controller yet.

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

Defensive copies protect exposed lists. Current Player state is immutable and always alive; gameplay must deliberately introduce state transitions and tests rather than assume elimination already exists. Strings currently identify card types; localization must not alter these identifiers.

## Proposed interface design
A small Java Swing interface is a proposal, not a recorded team decision. A command-line interface also meets the non-chess guidance. Keep rendering and text formatting in the view, input routing in the controller, and rule decisions in domain classes. The controller should call domain operations rather than implement dealing or card effects. Add a turn engine with explicit current-player, draw/discard piles, pending actions, and game-over state when gameplay work starts; exact public methods must be agreed before parallel implementation.

Detailed designs: [setup](game-setup.md), [turn initialization](turn-initialization.md), [deck](standard-deck.md), [i18n](i18n-plan.md).
