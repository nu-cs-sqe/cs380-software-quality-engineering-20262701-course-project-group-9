# Rules and project scope

Existing rule reference (not yet verified against the Canvas link): [Exploding Kittens Original Edition publisher rule sheet (2022)](https://dumekj556jp75.cloudfront.net/exploding-kittens/English.pdf).

The deck has 56 cards. Setup supports 2–5 players, gives each seven ordinary cards and one Defuse, returns N-1 kittens to the draw pile, and initializes a chosen first player. See [standard deck design](../design/standard-deck.md) for composition.

## Rules to follow

On November 24, Isaiah reported that we will follow the rules linked in Canvas. Verify the exact linked edition before making rule changes.

## Existing implementation difference
The current implementation returns every unused Defuse to the draw pile. The linked rule sheet returns only two extras for a 2- or 3-player game. The course example does not specify this extra-card limit. Current tests preserve the existing implementation; we need to compare this behavior with the exact Canvas-linked rules before describing the product as fully rules-compliant. A future change needs BVA and test-first implementation.

## Implemented versus planned
Setup, names, players, deck composition, dealing, and turn initialization are implemented on the local completion branch. The last two standard-deck changes are not yet on remote main.

The following are requirements for future work, not implemented features: playing/passing then drawing, card effects and Nope responses, Defuse reinsertion, player elimination, advancing turns/Attack debt, and declaring the last surviving player the winner. Use the selected command-line interface and verify combo rules against the Canvas source. No UI currently launches a playable game.
