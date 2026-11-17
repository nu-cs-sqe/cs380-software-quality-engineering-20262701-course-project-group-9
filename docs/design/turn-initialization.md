# Turn initialization

As a player, I want a valid starting player and turn order established during game setup so the first turn can begin.

## Acceptance criteria

- Accept 2–5 players, using the existing PlayerSetup validation.
- Player indices correspond to the order used by PlayerNames, CardDistribution.getHands(), and the IDs proposed in PR #8.
- The caller supplies a starting-player index from 0 through playerCount - 1.
- Initialize a cyclic order beginning with that index, wrapping to 0, containing each player exactly once.
- Expose the starting player as the current player; returned order lists cannot mutate internal state.
- Invalid counts and starting indices fail before a usable object is created.

## Design

TurnInitialization(int playerCount, int startingPlayerIndex) stores the initial list of player indices. getTurnOrder() returns a defensive copy; getCurrentPlayerIndex() returns its first entry. PlayerSetup supplies the existing count validation. This class does not shuffle cards, create Player objects, advance turns, resolve attacks, or eliminate players.

The setup coordinator will pass the same player count to PlayerInitializer (PR #8), CardDistribution, and TurnInitialization, then use the current index to select the player and hand. Selecting a starting index is explicit so tests are deterministic; the eventual UI may choose it. No unapproved Player/PlayerInitializer code is copied or merged here.

## Integration checks planned

Once PR #8 is approved, test setup for 2 and 5 players: IDs/names/hands/order align; each hand contains 8 cards; draw pile contains playerCount - 1 kittens; the initial current player exists. Also test a nonzero starting index and failure propagation for invalid input. These end-to-end checks are planned, not yet implemented.

## Development process

This branch depends on tooling PR #13. Document BVA and open a draft PR first; commit and run failing JUnit tests against minimal stubs; then implement the behavior and rerun all quality tools. Teammate approval is required before merge.

## Validation

PR #14 contains the work. Commit e0a1d5e is the actual red state: all 23 new JUnit cases failed against the minimal stub. After implementation, clean build and qualityCheck passed with 63 tests, no lint findings, 100% JaCoCo line/branch coverage, and 48/50 PIT mutants killed (96%). All 10 mutants in TurnInitialization are killed; the two existing CardDistribution shuffle-call mutants remain.
