# Card Distribution – Boundary Value Analysis

## Feature Description

The Card Distribution feature performs the initial Exploding Kittens deal from a supplied deck.

Each player receives one Defuse card and seven shuffled cards. The draw pile contains the remaining cards and exactly one fewer Exploding Kitten than the number of players.

## Input Partitions

- Valid player counts from 2 through 5
- Player counts below 2 or above 5
- Decks with enough or too few Defuse cards
- Decks with enough or too few Exploding Kittens
- Decks with enough or too few other cards
- Null decks and decks containing null or blank cards

## Test Case Design

### Method under test: `CardDistribution(int playerCount, List<String> deck)`

| Test Case | State of the System | Expected Output | Implemented? |
|---|---|---|---|
| TC01 | 2 players and sufficient cards | Each player receives one Defuse and seven other cards | :white_check_mark: |
| TC02 | 3 players and sufficient cards | Each player receives one Defuse and seven other cards | :white_check_mark: |
| TC03 | 5 players and sufficient cards | Each player receives one Defuse and seven other cards | :white_check_mark: |
| TC04 | 1 player | IllegalArgumentException | :white_check_mark: |
| TC05 | 6 players | IllegalArgumentException | :white_check_mark: |
| TC06 | Fewer than one Defuse per player | IllegalArgumentException | :white_check_mark: |
| TC07 | Fewer than player count minus one Exploding Kittens | IllegalArgumentException | :white_check_mark: |
| TC08 | Fewer than seven other cards per player | IllegalArgumentException | :white_check_mark: |
| TC09 | Null deck | IllegalArgumentException | :white_check_mark: |
| TC10 | Deck containing a null or blank card | IllegalArgumentException | :white_check_mark: |

### Method under test: `getHands()`

| Test Case | State of the System | Expected Output | Implemented? |
|---|---|---|---|
| TC11 | Distribution completed for 2 players | Hands are returned in player order with 8 cards each; changes to returned lists do not affect stored hands | :white_check_mark: |

### Method under test: `getDrawPile()`

| Test Case | State of the System | Expected Output | Implemented? |
|---|---|---|---|
| TC12 | Distribution completed for 2 players | Draw pile contains exactly 1 Exploding Kitten and remaining cards; changes to returned list do not affect the stored draw pile | :white_check_mark: |
| TC13 | Distribution completed for 4 players | Draw pile contains exactly 3 Exploding Kittens | :white_check_mark: |
| TC14 | Supplied deck contains more Exploding Kittens than needed | Draw pile still contains exactly player count minus one Exploding Kittens | :white_check_mark: |

## Testing Approach

JUnit 5 is used to test the feature. Tests check card counts and distribution constraints without relying on randomized card order.
