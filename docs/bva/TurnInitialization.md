# TurnInitialization BVA

Player IDs are zero-based indices shared with the planned setup coordinator.

| ID | Method | State/input | Expected output | Implemented |
|---|---|---|---|---|
| T01–T04 | constructor, getTurnOrder, getCurrentPlayerIndex | 2, 3, 4, or 5 players; start 0 | Order 0..count-1, current 0 | Yes |
| T05–T08 | constructor | Counts -1, 0, 1, or 6; start 0 | IllegalArgumentException | Yes |
| T09–T10 | constructor | Count Integer.MIN_VALUE or Integer.MAX_VALUE | IllegalArgumentException | Yes |
| T11–T14 | constructor, both getters | Counts 2..5; start count-1 | Last index first, followed by 0..count-2 | Yes |
| T15 | constructor, both getters | 5 players; start 2 | [2,3,4,0,1], current 2 | Yes |
| T16–T19 | constructor | 2 or 5 players; start -1 or count | IllegalArgumentException | Yes |
| T20–T21 | constructor | 5 players; start Integer.MIN_VALUE or Integer.MAX_VALUE | IllegalArgumentException | Yes |
| T22 | getTurnOrder, getCurrentPlayerIndex | Modify and clear a returned order | Stored order/current unchanged | Yes |
| T23 | both getters | Two instances with different starts | Independent order/current | Yes |

Valid cases also check exact order and size, which establishes that every player appears once. Invalid-start cases keep the player count valid to isolate the starting-index boundary. Tests use real PlayerSetup because it is a small deterministic value validator; mocking is unnecessary.
