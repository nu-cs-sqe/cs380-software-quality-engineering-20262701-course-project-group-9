
# Deck Setup – Boundary Value Analysis

## Feature Description

The Deck Setup feature creates and validates a deck of cards for the Exploding Kittens game.

The deck stores card names as strings and preserves duplicate cards because a deck may contain multiple cards of the same type.

## BVA Step 4: Test Case Design

- TC1: Null Deck (Invalid Input)
  - input: cards = null
  - output: IllegalArgumentException

- TC2: Empty Deck
  - input: cards = []
  - output: Empty deck created

- TC3: Minimum Non-Empty Deck
  - input: cards = ["Skip"]
  - output: Deck containing 1 card

- TC4: Two Cards
  - input: cards = ["Skip", "Attack"]
  - output: Deck containing 2 cards

- TC5: Duplicate Cards
  - input: cards = ["Skip", "Skip"]
  - output: Deck containing 2 Skip cards

- TC6: Null Card (Invalid Input)
  - input: cards = ["Skip", null]
  - output: IllegalArgumentException

- TC7: Empty Card Name (Invalid Input)
  - input: cards = ["Skip", ""]
  - output: IllegalArgumentException

- TC8: Whitespace-Only Card Name (Invalid Input)
  - input: cards = ["Skip", "   "]
  - output: IllegalArgumentException

## Additional Functional Tests

- TC9: Preserve Card Order
  - input: cards = ["Skip", "Attack", "Defuse"]
  - output: Cards remain in the same order before shuffling

- TC10: Protect Internal Deck
  - input: Create a deck, then modify the original input list
  - output: Deck contents remain unchanged

- TC11: Protect Returned Deck
  - input: Retrieve cards and modify the returned list
  - output: Internal deck contents remain unchanged
