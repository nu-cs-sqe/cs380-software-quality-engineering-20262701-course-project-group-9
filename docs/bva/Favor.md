# Favor

The active player plays one Favor and picks an opponent. That opponent picks one card from their own hand to give back. The active player can play again or draw. See the [Original Edition rules, page 2](https://dumekj556jp75.cloudfront.net/exploding-kittens/English.pdf).

| Boundary or example | Expected behavior |
| --- | --- |
| First or last card in a target hand | Move the selected card only. Preserve the order of the other cards. |
| Two copies of the same card, or a Favor given back | Move one copy at the selected position and spend exactly one Favor. |
| First or last opponent in the seating order | Only the selected opponent gives a card. Seat numbers distinguish duplicate names. |
| Self, eliminated seat, seat outside the lineup, or empty target hand | No transfer. The console offers only opponents who can give a card. |
| Card position below zero or beyond the last card | Reject before changing either hand. |
| No Favor in hand, pending kitten reinsertion, or finished game | Reject the action without changing the hands, turn, or draw pile. |
| Console command with a valid target and card | The target chooses a numbered card at a handoff prompt. The original player keeps the turn. |
| End of input before the target or card choice | Neither hand changes. |

Empty hands are an input choice in this interface: an opponent with no cards cannot complete a transfer. The console stages choices before spending Favor. Seat and card numbers shown to players start at 1; domain indices start at 0.
