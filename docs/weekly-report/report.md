# Weekly report

We meet on Saturdays unless we have already met on another day that week. See the [meeting notes](../project-management/discussion-record.md) for the instructor's feedback and our recorded decisions. Earlier entries have been updated with the latest PR outcomes.

## Week 3 (10/12–10/18)

We still need to confirm the details of this week's requirements review, repository setup, and early planning.

## Week 4 (10/19–10/25)

- We chose Exploding Kittens after Austin suggested using the available reference project.
- Isaiah updated the repository configuration, README, contributors, build badge, and Java 11 CI in PR #1.
- Austin volunteered to update the weekly report.
- We booked the instructor meeting for October 27 at 4:30 p.m.
- Setup design was documented later in [game-setup.md](../design/game-setup.md).

## Week 5 (10/26–11/01)

- Austin implemented player count validation for 2–5 players, documented BVA, and added eight passing JUnit tests using TDD. [PR #2](https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/pull/2) is merged.
- We completed the instructor meeting on October 27. Isaiah attended from the start, and Austin joined late. We discussed the project board, shared design, draft PRs, interface choice, and weekly reports. See the [notes](../project-management/discussion-record.md).

## Week 6 (11/02–11/08)

- Austin completed Player Name Management, including default names, validation, BVA, and tests. PR #5 is merged.
- Austin split the PlayerNames constructor into smaller methods after the instructor's feedback. All 15 existing tests passed. PR #7 is approved and merged.
- Austin added Player and PlayerInitializer with IDs, names, alive status, and empty hands in PR #8, now approved and merged. He explained that he ran tests before implementing the classes but committed both together, so the original red/green steps are not separate commits.
- In Discord, Austin took player initialization, Isaiah took deck setup, and Gabriel took card distribution. Gabriel later clarified that distribution takes a supplied list of cards; building the standard deck was separate work.

## Week 7 (11/09–11/15)

- Gabriel's card distribution work was merged in PR #9.
- Instructor feedback asked us to shorten the CardDistribution constructor and replace README TBD. Both have since been addressed.
- We still need the specific planning decisions from this week's meeting.

## Week 8 (11/16–11/22)

### Plan

Finish connecting setup, add the standard deck, and run the required quality tools. Next, agree on the interface and divide up turn logic, card effects, elimination, and winning.

### Progress as of November 16

| Work | Status |
| --- | --- |
| Isaiah: Checkstyle, SpotBugs, JaCoCo, and PIT (#13) | Approved, merged, and on main |
| Isaiah: turn initialization (#14) | On main through #15 |
| Isaiah: setup integration and tests (#15) | Approved, merged, and on main |
| Isaiah: 56-card deck and smaller distribution methods (#16) | Approved and merged into feature/game-setup-integration after #15; still needs to reach main |
| Remove Evan from the current team list (#17) | Merged into main |
| Combine remaining #16 work with main and update docs | On local chore/complete-setup-report; not pushed |

The combined local branch passed all 98 tests and both linters. JaCoCo reported 100% line and branch coverage; PIT killed 62 of 65 mutants. The full clean build and qualityCheck passed. See [quality tools](../quality-tools.md) for the remaining mutants.

### Next steps

- Review the [integration plan](../testing/integration-plan.md) and [i18n plan](../design/i18n-plan.md) together and assign owners.
- Choose GUI or CLI and agree on the gameplay classes/methods before splitting work.
- Choose a second language if aiming for an A. The rubric requires two languages at game start; Spanish is only a suggestion.
- Decide how to handle extra Defuse cards for 2–3 players.
- Post the [draft tasks](../project-management/board-items.md) to the project board with owners and linked PRs.
- Create a PR for the local setup and documentation work when ready to push.

The setup requirements, design, BVA notes, meeting notes, and course checklist are written. The game still needs an interface and playable turns through a win condition.

## Week 9 (11/23–11/29)

### Planning update, November 24

Isaiah reported the command-line interface, English/Spanish, and following the Canvas-linked rules. He will manage the board. Gameplay and test assignments still need owners. Verify the exact Canvas rule source before changing the dealing rules.

After Isaiah fetched the repository, we integrated origin/main at 0907c12 locally. This includes Austin's player elimination (#18), winner detection (#19), and Week 8 report (#20). Those PRs are merged. Their implementation commits did not include the reported elimination BVA or new tests; follow-up coverage is being added locally. PR #16 is still only on the feature branch and our local branch, not remote main.

The older report from #20 was reconciled into this file. Its claim that #16 reached main and its pending-review labels for #18 and #19 were corrected. The November 16 quality results above are historical, not results for the newly integrated code.

### Local turn and CLI verification, November 24

The turn engine passed its eight test-first cases and was committed on `feature/playable-turns` (`4bdf17a`). On `feature/command-line-game`, eight console tests first failed against a stub (`9c07d6c`) and passed after setup and turns were connected (`caf463f`). The game now runs in a terminal with 2–5 players. It accepts hand, draw and Skip commands, handles Defuse reinsertion, and announces the last survivor.

After mutation testing we added regression checks for the prompts, Skip, and invalid starting players. All 125 tests and both linters passed. A full game using the Gradle launcher ended with a winner. The [quality report](../quality-tools.md) lists the surviving mutants and coverage gaps. Both feature branches are local and await team review. Remaining work includes the other card effects, invented cards, a second win condition, and language selection.

### Team update, November 25

Isaiah Hashimoto and Hanwen (Austin) Liu are the two active members of Group 9.

### Favor, November 25

PR #21 merged the playable CLI into `main`. On `feature/favor-card`, the [Favor boundary analysis](../bva/Favor.md) preceded 20 failing tests for the card and shared-keyboard interface. A later regression test reproduced premature handoff before the fix. The [clean quality run](../quality-tools.md) passed all 146 tests, and a five-player game exercised Favor through the normal launcher. Teammate review is pending.

### Soundcheck, November 25

PRs #22 and #23 merged Favor, See the Future, and Shuffle. The Soundcheck feature branch adds an original card with a public count of kittens near the top of the pile. The [boundary analysis](../bva/Soundcheck.md) came first (`d7bd436`), followed by 15 failing cases (`ed4cd61`) and the implementation (`9e5e975`). All 171 tests passed in the clean quality run. Checkstyle and SpotBugs reported zero findings. A five-player game exercised the new card through the Gradle launcher.
