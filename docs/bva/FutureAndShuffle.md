
# See the Future and Shuffle – Test Case Design

## Feature Description

See the Future allows the current player to view up to three cards
from the top of the draw pile without changing their order.

Shuffle randomly rearranges the remaining draw pile.

Both actions consume their corresponding card and keep the
current player's turn active.

## BVA Step 4: Test Case Design

### See the Future

- TC1: Empty Draw Pile
  - input: deck = [], hand = ["See the Future"]
  - output: Returned cards = []

- TC2: One Card in Draw Pile
  - input: deck = ["Skip"], hand = ["See the Future"]
  - output: Returned cards = ["Skip"]

- TC3: Two Cards in Draw Pile
  - input: deck = ["Skip", "Attack"], hand = ["See the Future"]
  - output: Returned cards = ["Skip", "Attack"]

- TC4: Exactly Three Cards
  - input: deck = ["Skip", "Attack", "Favor"]
  - output: Returned cards = ["Skip", "Attack", "Favor"]

- TC5: More Than Three Cards
  - input: deck = ["Skip", "Attack", "Favor", "Nope"]
  - output: Returned cards = ["Skip", "Attack", "Favor"]

- TC6: Missing See the Future Card
  - input: hand = ["Skip"]
  - output: IllegalStateException

### Shuffle

- TC7: Empty Draw Pile
  - input: deck = [], hand = ["Shuffle"]
  - output: Empty draw pile

- TC8: One Card
  - input: deck = ["Skip"], hand = ["Shuffle"]
  - output: Draw pile contains ["Skip"]

- TC9: Multiple Cards
  - input: deck = ["Skip", "Attack", "Favor", "Nope"]
  - output: Same cards and quantities after shuffling

- TC10: Missing Shuffle Card
  - input: hand = ["Skip"]
  - output: IllegalStateException

## Additional Functional Tests

- TC11: See the Future Does Not End Turn
  - input: Current player uses See the Future
  - output: Current player remains unchanged

- TC12: Shuffle Does Not End Turn
  - input: Current player uses Shuffle
  - output: Current player remains unchanged

- TC13: Played Cards Are Consumed
  - input: Hand contains one action card
  - output: Played card is removed from the hand

- TC14: Reinsertion Prevents Card Actions
  - input: Player is resolving an Exploding Kitten
  - output: IllegalStateException

## Testing Approach

Use JUnit 5 and follow Red-Green-Refactor.

Commit failing tests separately before implementing production code.
