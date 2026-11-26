# StandardDeck BVA

createCards() has no input boundary; test output inventory and independence partitions. Private constructor is not public API.

| ID | Method | State/input | Expected | Implemented |
| --- | --- | --- | --- | --- |
| SD01 | createCards() | Fresh call | Exactly 56 cards and exact counts of all 13 types; no extra types | Yes, StandardDeckTest.containsExactOriginalEditionCards |
| SD02 | createCards() | Two calls; clear/mutate first list; call again | Mutable independent lists; other decks unchanged | Yes, StandardDeckTest.eachDeckIsIndependentAndMutable |
| SD03 | createCards() through GameSetup | 2–5 players | Sufficient inventory; conservation and N-1 kittens after setup | Yes, StandardDeckTest.startsGameWithRealDeck |
