# PlayerInitializer BVA

| ID | Public methods | State/input | Expected | Implemented |
| --- | --- | --- | --- | --- |
| PI01 | constructor | Counts 1,6 | IllegalArgumentException | Yes |
| PI02 | constructor, getPlayers() | Counts 2,3,4,5 with matching names | Exactly N ordered players | Yes |
| PI03 | constructor, getPlayers() | Two distinct names | IDs 0/1; names in order; alive; empty hands | Yes |
| PI04 | constructor | Count 2 with one name | IllegalArgumentException | Yes |
| PI05 | constructor, getPlayers() | Padded and empty names; clear source list | Trimmed/default names retained internally | Yes |
| PI06 | getPlayers() | Clear returned player list | Stored player list unchanged | Yes |

All cases are in PlayerInitializerTest. See [original analysis](../player-initialization-bva.md) for history. Austin's test-first account is documented in PR #8; original tests and implementation share a commit. Later review-time tests are regression additions.
