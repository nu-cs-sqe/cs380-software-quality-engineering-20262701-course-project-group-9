# Player Setup – Boundary Value Analysis

## Feature Description

The Player Setup feature validates the number of players before starting an Exploding Kittens game.

A valid game must have between 2 and 5 players, inclusive.

## Boundary Value Analysis

| Test Case | Number of Players | Expected Result |
|---|---|---|
| TC01 | 1 | Reject (IllegalArgumentException) |
| TC02 | 2 | Accept |
| TC03 | 3 | Accept |
| TC04 | 4 | Accept |
| TC05 | 5 | Accept |
| TC06 | 6 | Reject (IllegalArgumentException) |

## Additional Test Cases

| Test Case | Number of Players | Expected Result |
|---|---|---|
| TC07 | 0 | Reject (IllegalArgumentException) |
| TC08 | -1 | Reject (IllegalArgumentException) |

## Acceptance Criteria

1. The system must allow games with 2 to 5 players.
2. The system must reject player counts below 2.
3. The system must reject player counts above 5.
4. Invalid player counts must result in an IllegalArgumentException.
5. Valid player counts must be stored correctly.

## Testing Approach

JUnit 5 will be used to implement the test cases.

Development will follow the Red-Green-Refactor cycle:

1. Red: Write tests that fail before the implementation is complete.
2. Green: Implement the minimum code necessary to pass the tests.
3. Refactor: Improve the code without changing its behavior.