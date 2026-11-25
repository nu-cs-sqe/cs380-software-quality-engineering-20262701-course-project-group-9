# Playable turn slice

This slice connects setup to drawing, Skip, Defuse, elimination, and winning. Other action cards and combinations remain separate work. It is not the finished ruleset.

| Boundary | Expected behavior |
| --- | --- |
| 2 players; last player draws | Turn wraps to first living player |
| Ordinary draw | Card enters hand; turn ends |
| Kitten with no Defuse | Player eliminated; dead players skipped |
| One survivor | Winner returned; further actions rejected |
| Kitten with Defuse | Pause for reinsertion; block draw and Skip |
| Reinsertion at 0 or remaining deck size | Accepted; Defuse spent; turn ends |
| Reinsertion at -1 or size + 1 | Rejected without changing state |
| Skip held / absent | Spend one and end turn / reject without advancing |
| Empty pile | Reject draw without advancing |

Game takes a GameSetup or deterministic player/deck/start inputs for tests. It owns copies of players and cards. Read methods return snapshots. The CLI only prompts, parses, and displays. No new PR is opened while the user's no-push instruction is active. New turn behavior follows local test-first commits.
