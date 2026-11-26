# Favor

The active player spends one Favor and chooses an opponent. That opponent selects one card from their own hand to give back. The active player can then play again or draw. [Original Edition rules, page 2](https://dumekj556jp75.cloudfront.net/exploding-kittens/English.pdf)

The console numbers seats and cards from 1. Game methods use indices starting at 0. The interface offers only living opponents with cards. If nobody can give a card, the actor keeps their Favor.

## Game boundaries

| ID | Method | Starting state or input | Expected result | Implemented in FavorTest |
| --- | --- | --- | --- | --- |
| F01 | `Game.favor` | Actor holds two Favors. Donor has Skip, Defuse, Skip. Select the first or last card. | Spend one Favor and receive the selected Skip. Preserve the order of the remaining hand, deck size, and actor's turn until they draw. | `transfersTheChosenCopyAndKeepsTheTurn` |
| F02 | `Game.favor`, `Game.favorTargets` | Both hands hold just one Favor. | Donor may give Favor. Donor is then empty and there are no eligible targets. | `aFavorMayBeGivenBackToThePlayerWhoAsked` |
| F03 | `Game.favorTargets`, `Game.favor` | Seats include an eliminated opponent, an empty hand, and a final eligible seat. Names repeat and IDs differ from seat indices. | Offer only the eligible seat. Reject the other opponents without spending Favor. The last seat can give its card. | `offersOnlyLivingOpponentsWithCardsBySeat` |
| F04 | `Game.favorTargets`, `Game.favor` | Actor is second and the first seat holds one card. | Offer seat index 0 and transfer its only card. | `theFirstSeatCanBeChosenByALaterPlayer` |
| F05 | `Game.favor` | In a two-player game the first actor selects seat −1, 0 (self), or 2. | Reject the seat and preserve both hands. | `rejectsInvalidOrOwnSeatWithoutSpending` |
| F06 | `Game.favor` | A donor holds one card. Select card −1 or 1. | Reject the card index and preserve both hands. | `rejectsAnInvalidGiftWithoutSpending` |
| F07 | `Game.favor` | Actor has no Favor. | Reject the play and preserve both hands. | `requiresFavorInHand` |
| F08 | `Game.favor` | Actor has Favor and must reinsert a kitten. | Reject the play. The donor retains their card and reinsertion remains pending. | `cannotPlayFavorWhileReinsertionIsPending` |
| F09 | `Game.favor` | Game has a winner who holds Favor. | Reject the play. Winner retains Favor. | `cannotPlayAfterAWin` |

## Shared-keyboard boundaries

All cases below exercise `ConsoleGame.play` through typed commands.

| ID | Starting state or input | Expected result | Implemented in ConsoleGameTest |
| --- | --- | --- | --- |
| F10 | Two eligible opponents share a name. Enter an out-of-range choice, then the second opponent and their second card. | Show seat numbers, retry invalid input, and transfer the chosen card. The other opponent's hand stays hidden. The actor can draw. | `playingFavorLetsTheSelectedOpponentChooseAndTheActorContinue` |
| F11 | Input ends at the opponent menu, at handoff, at card selection, or after invalid card input. | Preserve both hands and the current turn. Reveal the donor's cards only after handoff confirmation. | `interruptedFavorPreservesBothHands` |
| F12 | Actor enters Favor without holding it. | Show the missing-card message. | `unheldFavorReportsTheMissingCard` |
| F13 | No opponent has a card. | Skip the handoff, explain why, and allow the actor to draw with Favor still in hand. | `unavailableFavorTargetLeavesTheCardAndTurnAvailable` |
| F14 | A number is typed at handoff, followed by a blank line and a valid gift choice. | Wait for the blank line before listing cards. Clear the terminal display at each handoff. | `privateFavorChoicesAreClearedAtEachKeyboardHandoff` |
