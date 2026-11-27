# Soundcheck

Spend one Soundcheck to announce how many Exploding Kittens are among the next five cards in the draw pile. With fewer than five cards, count the remaining pile. An empty pile gives zero. Everyone hears the total. Card identities and positions stay hidden, the pile stays in order, and the active player can play again or draw. Each new game includes one copy before dealing.

| ID | Method | State or input | Expected result | Test status |
| --- | --- | --- | --- | --- |
| SC01 | Game.soundcheck | Empty pile, or four safe cards | Report zero and spend one Soundcheck. | Covered by `countsKittensOnlyInTheFirstFiveCards` |
| SC02 | Game.soundcheck | One kitten in a one-card pile, or kittens at positions 1, 3, and 5 in a five-card pile | Report one or three respectively. | Covered by `countsKittensOnlyInTheFirstFiveCards` |
| SC03 | Game.soundcheck | Six cards with kittens at positions 1, 5, and 6 | Report two. The sixth card falls outside the scan. | Covered by `countsKittensOnlyInTheFirstFiveCards` |
| SC04 | Game.soundcheck | Actor holds two copies. The first three cards are known distinct safe cards. | Spend exactly one copy. Preserve the draw order, pile size, and active seat. | Covered by `spendsOneCopyAndPreservesTurnAndDrawOrder` |
| SC05 | Game.soundcheck | Actor lacks the card, must reinsert a kitten, or has already won | Reject the command before spending a card. | Covered by `rejectsPlayWithoutSoundcheck`, `preservesSoundcheckDuringReinsertion`, `winnerCannotSpendSoundcheckAfterTheGame` |
| SC06 | GameSetup constructor | Default setup for 2 through 5 players with the first or last seat starting | Exactly one Soundcheck among hands and pile. Starting hands have eight cards. Existing inventory rules still apply. | Covered by `startsGameWithRealDeck` |
| SC07 | ConsoleGame.play | Actor types soundcheck, then draws | Print a public count, keep card names and positions hidden, and let the same actor draw. Include all playable commands in the turn prompt. | Covered by `soundcheckAnnouncesOnlyTheCountAndLetsTheSamePlayerDraw` |
| SC08 | ConsoleGame.play | Actor types soundcheck without a copy | Explain that the card is missing and let the actor continue. | Covered by `missingSoundcheckAllowsAnotherCommand` |

StandardDeck.createCards() still describes the 56-card Original Edition inventory. The default GameSetup adds the project card. A caller who supplies a deck controls its contents.
