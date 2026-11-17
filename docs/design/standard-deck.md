# Standard deck and distribution refactor

Create a fresh mutable 56-card Original Edition deck on each StandardDeck.createCards() call: 4 kittens, 6 Defuse, 4 each Attack/Favor/Shuffle/Skip, 5 each Nope/See the Future, and 4 each of five distinct cat types. No shuffling in the factory; CardDistribution owns setup shuffling. Preserve existing card identifiers.

Source: publisher rule sheet https://dumekj556jp75.cloudfront.net/exploding-kittens/English.pdf (2022 Original Edition). Cat identifiers are Tacocat, Cattermelon, Hairy Potato Cat, Beard Cat, Rainbow-Ralphing Cat.

GameSetup gains a three-argument constructor (count, names, start) that delegates to the existing supplied-deck constructor using StandardDeck. Existing seven-random-plus-Defuse hands and return-all-extra-Defuse behavior remain unchanged. The publisher removes some extras for 2–3 players; this project currently retains them. That rules decision needs team review, not a silent change in this refactor.

Split CardDistribution into small extraction, validation, dealing, and pile-building methods while preserving existing behavior and tests. Existing tests are regression coverage for this refactor; no fabricated red phase.

## BVA and acceptance
| API | Cases | Implemented |
| --- | --- | --- |
| createCards | exact 56 cards and all 13 type counts; no extra types | No |
| createCards | mutate result; next call independent | No |
| GameSetup(count,names,start) | counts 2,3,4,5; first/last starting player; 8-card hands, N-1 kittens, conservation | No |
| GameSetup(count,names,start) | invalid counts 1/6; invalid starting index -1/N; mismatched names | No |

Test-first: document and open draft, run failing tests with stubs, commit red, implement, run full quality suite. Refactor uses the existing regression tests. Dependencies: integration PR #15; approval needed before main merge.
