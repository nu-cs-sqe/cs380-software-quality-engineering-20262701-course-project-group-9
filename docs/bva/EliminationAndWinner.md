# Elimination and winner detection

These are regression tests added after the implementation in PRs #18 and #19. They do not establish test-first history for those PRs.

| Input/state | Expected result |
| --- | --- |
| Newly created player | Alive |
| First elimination | Dead; identity and hand unchanged |
| Repeated elimination | Still dead |
| 0, 1, 2, 5 survivors | Winner only when exactly one survives |
| Survivor at first or last list position | Correct player returned |
| Elimination after constructing WinCondition | Updated state observed |
| Caller clears original player list | Membership retained by WinCondition |
| Empty player list | No winner; not a valid playable game |

Player references remain shared intentionally so WinCondition sees eliminations. Collection membership is copied. GameSetup also returns shared Player objects; it does not promise immutable player state.
