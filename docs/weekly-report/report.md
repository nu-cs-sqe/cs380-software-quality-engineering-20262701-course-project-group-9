# Meeting cadence

Team meetings are on Saturdays, except weeks when the team met on another day (Isaiah confirmed November 16). This cadence does not supply missing per-meeting attendees or decisions. Instructor-session notes are in the [discussion record](../project-management/discussion-record.md).

# Week 3 (10/12/2026-10/18/2026)
**Planning and Progress Tracking**:

1. [to confirm] Team: Reviewed the course project requirements and weekly activity guidance.
2. [to confirm] Team: Set up the GitHub Classroom repository for team collaboration.
3. [to confirm] Team: Reviewed the repository setup requirements, including README, branch protection, and CI.
4. [to confirm] Team: Discussed potential game projects and initial development plans.

# Week 4 (10/19/2026-10/25/2026)
**Planning and Progress Tracking**:

1. [done] Team: Discussed game options and selected Exploding Kittens as the course project.
2. [done] Hanwen Liu: Proposed Exploding Kittens and encouraged the team to finalize the project selection and begin development.
3. [done] Isaiah Hashimoto: Updated repository configuration, documentation, contributors, build badge, and Java 11 GitHub Actions CI in merged PR #1.
4. [completed later] Setup components and integration design are documented in [game setup](../design/game-setup.md). Historical task assignments are reconstructed in [discussion record](../project-management/discussion-record.md).

# Week 5 (10/26/2026-11/01/2026)
**Planning and Progress Tracking**:

1. [done] Hanwen Liu: Implemented player count validation (2–5 players) for Exploding Kittens, documented BVA, and added 8 passing JUnit tests using TDD. Merged in PR #2.(https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/pull/2)

- [done] October 27: required instructor meeting completed. Isaiah attended from the start; Austin joined late. Recovered transcript notes cover board setup, joint requirements/design, draft PRs and reviews, interface choice, and concise weekly reporting. See [meeting notes](../project-management/discussion-record.md).

# Week 6 (11/02/2026-11/08/2026)
**Planning and Progress Tracking**:

1. [done] Hanwen Liu: Completed Player Name Management, including name validation, default name generation, BVA documentation, and JUnit tests. PR #5 was merged into main. (https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/pull/5)
2. [done] Hanwen Liu: Refactored the PlayerNames constructor based on the instructor's Week 6 code review feedback by extracting validation and initialization into separate private methods. All 15 existing JUnit tests passed. PR #7 is now approved and merged. (https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/pull/7)
3. [done] Hanwen Liu: Implemented Player Initialization in approved and merged PR #8. Austin explained that tests ran before implementation but were committed together; review accepted that author-reported explanation. Separate historical red/green commits are not available. Added Player and PlayerInitializer classes to create player objects with unique IDs, names, alive status, and empty hands.

# Week 7 (11/09/2026-11/15/2026)

**Recorded progress**:

- Gabriel Hsieh: Card distribution was merged in PR #9.
- Instructor Week 7 feedback requested a smaller CardDistribution constructor and replacement of README TBD; both are addressed by subsequent work.
- Regular Saturday cadence is confirmed. Specific planning outcomes for this week are not recorded; add actual decisions when available.

# Week 8 (11/16/2026-11/22/2026)

**Planning**:
- Integrate player creation, deck validation, dealing, and turn initialization.
- Configure and run all required quality tools.
- Add the real deck and address the instructor's distribution-constructor feedback.
- Next proposed work: runnable interface and one turn, followed by card effects, repeated turns, elimination, and win conditions. Owners and team agreement remain to be recorded.

**Progress as of November 16**:
- [done, on main] Isaiah Hashimoto: Checkstyle, SpotBugs, JaCoCo, and PIT in approved/merged PR #13.
- [done, on main] Isaiah Hashimoto: Turn initialization in PR #14, subsequently brought into main through approved/merged PR #15.
- [done, on main] Isaiah Hashimoto: Setup integration and integration tests in PR #15.
- [approved, merged into feature branch] Isaiah Hashimoto: PR #16 adds the 56-card deck, automatic-deck setup constructor, and smaller CardDistribution methods. It merged into feature/game-setup-integration after #15 had already merged, so these changes are not yet on remote main.
- [prepared locally, not pushed] The chore/complete-setup-report branch brings the remaining #16 changes together with latest main and corrects these reports. A new PR is still needed to bring this work into main.
- [done, on main] PR #17 removes Evan Pendleton from the current team list.
- [verified locally] Synchronized completion branch: 98 tests, Checkstyle, SpotBugs, 100% line/branch coverage, and 62/65 PIT mutations killed. Full clean build and qualityCheck passed.

**Outstanding course documentation**:
- Detailed integration and i18n plans are complete locally in [integration testing](../testing/integration-plan.md) and [i18n](../design/i18n-plan.md), with [board-ready entries](../project-management/board-items.md). Team discussion and board publication remain pending.
- The October 27 instructor meeting is complete, as explicitly confirmed by the instructor in the reviewed Zoom transcript. Isaiah attended from the start and Austin joined late; detailed feedback is now recorded. See [discussion record](../project-management/discussion-record.md).
- Add actual meeting outcomes and agreed task owners; do not replace missing history with assumed activity.

Earlier weeks are retrospective progress summaries updated with current merge outcomes. Saturday cadence is team-reported; individual meeting decisions are included only when supported.

## Documentation follow-up

- Completed local setup requirements/use case, game-rule reference and variant note, architecture, detailed integration and i18n plans, per-class BVA for the new setup classes, documentation index, and course checklist.
- No remote publication, invented meeting notes, or unagreed task assignments. See [course checklist](../course-checklist.md) for remaining team actions.

## Team communication evidence

- October 25: team agreed on Exploding Kittens; Austin accepted weekly-report work; all three agreed to Tuesday 4:30 p.m. for the instructor meeting.
- October 27: instructor meeting occurred online; Isaiah attended and shared the recording afterward. The transcript establishes that Austin joined late after his reported sign-in issue, and the instructor confirmed the required meeting was complete.
- November 4–5: Austin volunteered for player initialization, Isaiah for deck setup, and Gabriel for card distribution.
- November 8: Gabriel clarified caller-supplied-deck input and that the catalog was separate work.
- November 16: Isaiah confirmed Saturday team meetings, except weeks with a meeting on another day.
- These are sourced asynchronous discussions, transcript notes, and team-reported cadence. See [full reconstruction](../project-management/discussion-record.md). Future UI/i18n and gameplay owners still require agreement.
