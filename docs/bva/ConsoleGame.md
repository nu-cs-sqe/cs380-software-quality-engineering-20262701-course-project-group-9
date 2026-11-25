# Command-line setup and turns

The console uses GameSetup for dealing and Game for all turn and winner decisions. Players enter seat numbers starting at 1. Reinsertion positions start at 0 (the top of the remaining pile).

| Input or state | Expected result |
| --- | --- |
| Player count 1, 6, or text | Explain the range and ask again |
| Player count 2 or 5 | Collect one name per seat; an empty name uses the domain default |
| First player 0 or N+1, then 1 or N | Reject the invalid seats and start at the selected valid seat |
| Unknown command or Skip without a Skip card | Same player keeps the turn; no card is drawn |
| Hand command, then ordinary draw | Show only the current hand on request; drawing advances once |
| A plays Skip, B draws an undefused kitten | Consume A's Skip, eliminate B, announce A once, stop reading |
| Kitten drawn with Defuse | Request a position; reject non-numbers, -1 and size+1; accept 0 and size |
| End of input during setup, a turn, or reinsertion | Return without inventing a move or a winner |

The integration tests use the real setup and turn logic. Fixed decks keep draw and Defuse scenarios repeatable. Other action cards and translated prompts are separate work.
