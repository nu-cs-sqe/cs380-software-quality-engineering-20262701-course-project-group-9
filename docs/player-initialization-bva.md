
# Player Initialization – Boundary Value Analysis

## Feature Description

The Player Initialization feature creates Player objects based on the configured player count and player names.

Valid player counts range from 2 to 5, inclusive.

Each player receives a unique ID, a name, an initial alive status, and an empty hand.

## BVA Step 4: Test Case Design

- TC1: Below Minimum Players (Invalid Input)
  - input: playerCount = 1
  - output: IllegalArgumentException

- TC2: Minimum Valid Players
  - input: playerCount = 2, names = ["Alice", "Bob"]
  - output: 2 Player objects created

- TC3: Just Above Minimum
  - input: playerCount = 3
  - output: 3 Player objects created

- TC4: Just Below Maximum
  - input: playerCount = 4
  - output: 4 Player objects created

- TC5: Maximum Valid Players
  - input: playerCount = 5
  - output: 5 Player objects created

- TC6: Above Maximum Players (Invalid Input)
  - input: playerCount = 6
  - output: IllegalArgumentException

## Additional Functional Test Cases

- TC7: Unique Player IDs
  - input: playerCount = 2, names = ["Alice", "Bob"]
  - output: Player IDs = [0, 1]

- TC8: Player Name Order
  - input: playerCount = 2, names = ["Alice", "Bob"]
  - output: Player names = ["Alice", "Bob"]

- TC9: Initial Player Status
  - input: playerCount = 2, names = ["Alice", "Bob"]
  - output: All players are alive

- TC10: Initial Player Hands
  - input: playerCount = 2, names = ["Alice", "Bob"]
  - output: All player hands are empty

## Testing Approach

JUnit 5 will be used for unit testing.

Development will follow TDD:

1. Red: Write failing unit tests.
2. Green: Implement the minimum code required.
3. Refactor: Improve code while keeping all tests passing.

