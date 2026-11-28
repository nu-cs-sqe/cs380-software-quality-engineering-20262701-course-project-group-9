
# Second Chance – Boundary Value Analysis

## 1. Feature Description

Second Chance is an original action card for the Exploding Kittens
course project.

When a player uses Second Chance, the top card of the draw pile is
removed and placed at the bottom of the same pile.

The action does not reveal or draw the card, and it does not end
the player's turn.

### Rules

- The current player must have at least one Second Chance card.
- Playing the card consumes exactly one Second Chance.
- The draw pile must contain at least one card.
- The top card moves to the bottom of the draw pile.
- The total number of cards in the draw pile remains unchanged.
- The current player does not change.
- The action cannot be performed while an Exploding Kitten is
  awaiting reinsertion.
- The action cannot be performed after the game ends.

## 2. Input Parameters

The behavior depends on the following inputs:

- Number of cards in the draw pile.
- Number of Second Chance cards in the current player's hand.
- Order of cards in the draw pile.
- Whether the game is currently active.
- Whether an Exploding Kitten is awaiting reinsertion.

## 3. Boundary Identification

### Draw Pile Size

The important boundary is between an empty and a nonempty draw pile.

- 0 cards: Invalid action.
- 1 card: Valid action, but the order does not change.
- 2 cards: Valid action; the two cards exchange positions.
- 3 or more cards: Valid action; the first card moves to the end.

### Second Chance Card Count

The important boundary is between holding no card and at least one card.

- 0 copies: Invalid action.
- 1 copy: Valid action; the card is consumed.
- 2 copies: Valid action; only one copy is consumed.

### Game State

- Active game: The action may be performed.
- Pending kitten reinsertion: The action is rejected.
- Finished game: The action is rejected.

## 4. BVA Step 4: Test Case Design

### Draw Pile Boundaries

- TC1: Empty Draw Pile
  - input: hand = ["Second Chance"], deck = []
  - output: IllegalStateException; Second Chance remains in hand.

- TC2: One Card in Draw Pile
  - input: hand = ["Second Chance"], deck = ["Skip"]
  - output: deck = ["Skip"]; Second Chance is consumed; turn unchanged.

- TC3: Two Cards in Draw Pile
  - input: hand = ["Second Chance"], deck = ["Attack", "Skip"]
  - output: deck = ["Skip", "Attack"]; turn unchanged.

- TC4: Three Cards in Draw Pile
  - input: hand = ["Second Chance"], deck = ["Attack", "Skip", "Favor"]
  - output: deck = ["Skip", "Favor", "Attack"]; turn unchanged.

### Hand Boundaries

- TC5: No Second Chance Card
  - input: hand = ["Skip"], deck = ["Attack"]
  - output: IllegalStateException; hand and deck remain unchanged.

- TC6: Exactly One Second Chance Card
  - input: hand = ["Second Chance"], deck = ["Attack", "Skip"]
  - output: Second Chance is consumed; deck = ["Skip", "Attack"].

- TC7: Two Second Chance Cards
  - input: hand = ["Second Chance", "Second Chance"],
           deck = ["Attack", "Skip"]
  - output: Exactly one Second Chance remains in hand;
            deck = ["Skip", "Attack"].

### Game State Boundaries

- TC8: Kitten Reinsertion Pending
  - input: hand = ["Second Chance", "Defuse"],
           deck initially = ["Exploding Kitten", "Skip"];
           player draws Exploding Kitten.
  - output: Second Chance is rejected with IllegalStateException;
            the card remains in hand.

- TC9: Game Already Finished
  - input: A game where one player has been eliminated and
           a winner has been determined.
  - output: IllegalStateException when attempting Second Chance.

### Integration and Invariant Tests

- TC10: Turn Preservation
  - input: Current player uses Second Chance with a nonempty deck.
  - output: The same player remains the current player.

- TC11: Draw Pile Size Preservation
  - input: deck = ["Attack", "Skip", "Favor"].
  - output: Draw pile size remains 3 after Second Chance.

- TC12: Card Order Verification
  - input: deck = ["Attack", "Skip", "Favor"];
           player uses Second Chance and players draw in order.
  - output: Draw order is ["Skip", "Favor", "Attack"].

- TC13: CLI Command Execution
  - input: Player has Second Chance and enters "second".
  - output: "played Second Chance" is displayed;
            the player may continue the same turn.

- TC14: CLI Invalid Action
  - input: Player does not have Second Chance and enters "second".
  - output: "No Second Chance card" is displayed;
            no card is consumed and the turn remains available.

- TC15: Project Deck Integration
  - input: Initialize a normal game using GameSetup.
  - output: Exactly one Second Chance card exists across
            all initial player hands and the remaining draw pile.

## 5. Testing Strategy

JUnit 5 is used to verify the domain behavior.

Tests focus on:

- Empty, single-card, and multiple-card draw piles.
- Missing, single, and duplicate Second Chance cards.
- Exact draw pile rotation.
- Card consumption and turn preservation.
- Rejection of actions during kitten reinsertion or after game over.
- Integration with GameSetup and ConsoleGame.

The draw pile order is verified through actual draw operations,
rather than checking only the total number of cards.

This helps detect implementations that consume Second Chance
without moving the top card to the bottom.

## 6. Expected Result

Second Chance allows players to move the top card to the bottom
of the draw pile without revealing it.

The action preserves the card count, consumes one Second Chance,
and does not advance the current turn.

Invalid actions must not consume the card or modify the game state.
