# Player Name Management – Boundary Value Analysis

## Feature Description

The Player Name Management feature handles player names during the Exploding Kittens Game Setup Phase.

The feature stores player names, removes leading and trailing whitespace, and assigns default names when inputs are blank.

The number of player names must match the configured player count.

## Input Partitions

- Valid non-blank player names
- Names containing leading or trailing whitespace
- Empty names
- Whitespace-only names
- A valid number of player names
- Too few or too many player names

## Test Case Design

| Test Case | Input | Expected Result |
|---|---|---|
| TC01 | "Alice" | "Alice" |
| TC02 | " Alice " | "Alice" |
| TC03 | "" for Player 1 | "Player 1" |
| TC04 | "   " for Player 1 | "Player 1" |
| TC05 | ["Alice", "Bob"], 2 players | Both names stored |
| TC06 | Five valid names, 5 players | All five names stored |
| TC07 | Two names, 3 players | IllegalArgumentException |
| TC08 | Three names, 2 players | IllegalArgumentException |

## Acceptance Criteria

1. Player names must be stored in the correct order.
2. Leading and trailing whitespace must be removed.
3. Blank names must be replaced with default names.
4. The number of supplied names must match the configured player count.
5. Invalid name counts must throw IllegalArgumentException.

## Testing Approach

JUnit 5 will be used to test the feature.

Development will follow TDD:

1. Red: Write tests that initially fail.
2. Green: Implement the minimum necessary code.
3. Refactor: Improve code without changing behavior.