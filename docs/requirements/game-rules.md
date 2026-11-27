# Rules and project scope

The [Canvas Project Resources page](https://canvas.northwestern.edu/courses/258102/pages/project-resources) links the publisher’s [Grab & Game instructions (2023)](https://cdn.shopify.com/s/files/1/0345/9180/1483/files/Exploding-Kittens_Grab-N-Game_Instructions_2023.pdf?v=1712786226). The team agreed to use the Canvas-linked rules. The current [standard deck design](../design/standard-deck.md) uses the 56-card Original Edition. Course-required original cards and the second win condition are project additions.

## Setup comparison

| Rule | Canvas Grab & Game, setup steps 1–6 | Current game on main |
| --- | --- | --- |
| Player count | 2–4 | 2–5 |
| Base deck | 42 cards, including 3 kittens and 5 Defuses | 56 cards, including 4 kittens and 6 Defuses |
| Starting hand | 1 guaranteed Defuse plus 5 cards (6 total) | 1 guaranteed Defuse plus 7 non-Defuse, non-kitten cards (8 total) |
| Spare Defuses | Return all extras before the five-card deal. One of those random cards can be another Defuse. | Return all extras after the seven-card deal. Every starting hand has exactly one Defuse. |
| Kittens in the draw pile | One fewer than the number of players, added after dealing | Same count and timing |

For 2, 3, and 4 players, Grab & Game puts 3, 2, and 1 spare Defuses back into the deck before the deal. A five-player game is outside that edition’s range. The previously discussed two-extra limit came from a different edition. It does not appear in the Canvas rules.

[Issue #30](https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/issues/30) tracks how to reconcile the published rules, the team's existing setup tests, and the agreed card additions. Before changing behavior, record the supported player range and base deck, then run failing tests for the revised deal and its boundaries.

## Current gameplay

The command-line game on main supports setup, drawing and passing play to the next player, Skip, Favor, See the Future, Shuffle, Defuse reinsertion, elimination, and the last-surviving-player win. The [board](https://github.com/orgs/nu-cs-sqe/projects/134) tracks the original cards, [second win condition](https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/issues/27), and [language selection](https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/issues/28).
