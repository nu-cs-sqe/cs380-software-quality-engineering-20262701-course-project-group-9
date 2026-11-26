# Draft project board tasks

These tasks are ready for team discussion but have not been posted to GitHub Projects. This update is staying local. The GitHub token checked earlier did not have read:project access.

## Integration testing

**Owner:** To assign. **Status:** Needs team review.

Review the [integration test plan](../testing/integration-plan.md). Setup tests IT01–IT04 are done. IT05–IT10 cover the interface, turns, Defuse and elimination, Attack/Skip, winning, and language changes.

Agree on interfaces and assign the remaining tests. Record the discussion date and decisions, link the PRs and test results, and mark each scenario done when it passes. The later tests depend on the interface and turn engine.

## Internationalization

**Owner:** To assign. **Status:** Needs team review.

Review the [i18n plan](../design/i18n-plan.md). At least two languages at game start are required for an A, and new locales must be easy to add without changing existing code (project grading rubric, page 3). English and Spanish are selected.

Assign the implementation owner and translation reviewer. Add the resource files and planned tests, connect the language choice to setup, and check that translations do not change game state. Link the finished PR and test results.

## Extra Defuse cards

**Owner:** To assign. **Status:** Verify Canvas rule source, then check implementation.

Our setup code returns all unused Defuse cards to the deck. The publisher's 2022 rules return only two extras for 2–3 players. We will follow the Canvas-linked rules. Verify that exact source before deciding whether this implementation needs changing. If we change the code, start with failing boundary tests and update the setup requirements and card-count checks.

## Instructor meeting follow-up

**Status:** Meeting complete; follow-up work remains.

We met with Prof. Zhang on October 27. Isaiah attended from the start and Austin joined late. The instructor confirmed the required meeting was complete. See the [meeting notes](discussion-record.md).

Next, agree on gameplay features, classes/methods, owners, deadlines, and reviewers. Put the tasks on the board with linked PRs and keep the weekly report updated.

## Decisions already made

- Exploding Kittens was agreed on October 25.
- Austin volunteered for the weekly report on October 25.
- On November 4–5, Austin took player initialization, Isaiah took deck setup, and Gabriel took card distribution.
- We meet on Saturdays unless we have met on another day that week.

Isaiah will manage the board. The interface will be command line, the languages English and Spanish, and the rules those linked in Canvas. Still needed: gameplay assignments and interfaces, integration test owners, translation review, and verification of the exact Canvas rule source.
