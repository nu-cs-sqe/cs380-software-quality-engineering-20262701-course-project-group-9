# GameSetup BVA

Each row is implemented in GameSetupTest unless StandardDeckTest is named.

| ID | Public methods | State/input | Expected | Implemented |
| --- | --- | --- | --- | --- |
| GS01 | supplied-deck constructor, all getters | Counts 2,3,4,5; valid names/deck; last starting index | Ordered players/IDs, eight-card hands, N-1 kittens, correct order/current index | Yes |
| GS02 | supplied-deck constructor | Counts 1,6 | IllegalArgumentException | Yes |
| GS03 | supplied-deck constructor | Count 2, start -1 or 2 | IllegalArgumentException | Yes |
| GS04 | supplied-deck constructor | Count 2, one name | IllegalArgumentException | Yes |
| GS05 | supplied-deck constructor | Null, empty, insufficient deck | IllegalArgumentException | Yes |
| GS06 | all getters, supplied-deck constructor | Clear source names/deck and returned player/hand/pile/order lists; blank/trimmed names; start 0 | Internal state unchanged; defaults/trim applied; start 0 | Yes |
| GS07 | standard-deck constructor, all getters | Counts 2,3,4,5; start 0 and N-1 | Eight-card hands, correct current index, exact card conservation under chosen policy | Yes, StandardDeckTest |
| GS08 | standard-deck constructor | Counts 1/6, start -1/N, name mismatch | IllegalArgumentException | Yes, StandardDeckTest |

See [integration plan](../testing/integration-plan.md) for future gameplay scenarios. Tests describe current behavior, not a claim of complete game rules.
