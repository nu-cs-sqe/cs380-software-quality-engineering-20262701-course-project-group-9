# GitHub Projects entries prepared for publication

These are local drafts, not existing board cards. Publication is deferred while work must remain unpushed. The previously checked GitHub token also lacks read:project; no access scopes have been changed.

## Card: Agree and execute integration testing plan
Status: To discuss. Owner: Unassigned (team to agree).
Description: Review docs/testing/integration-plan.md. Setup IT01–IT04 are implemented; IT05–IT10 cover UI, turn actions, Defuse/elimination, turn debt, winning, and i18n.
Acceptance: team records discussion date and attendees; agrees interfaces/owners; adds remaining scenarios as tasks; runs real-component setup and full quality checks; links test evidence and PRs; only marks implemented scenarios complete.
Dependencies: runnable interface and turn engine for later scenarios.

## Card: Agree and implement internationalization
Status: To discuss. Owner: Unassigned (team to agree).
Description: Review docs/design/i18n-plan.md. Proposed English/Spanish ResourceBundles; preserve stable domain card IDs and player names.
Acceptance: confirm locales and translator/reviewer; assign implementation owner; complete I18N01–I18N06; demonstrate locale switch without game-state change; link tests and PR.
Dependencies: UI design and agreement on message keys.

## Card: Confirm setup rules variant
Status: Decision needed. Owner: Team.
Description: Current dealing returns all unused Defuse cards. Publisher's 2022 rule sheet retains only two extras for 2–3 players.
Acceptance: record selected rule and rationale; if changed, add failing boundary tests before implementation and update requirements/deck conservation expectations.

## Card: Complete instructor-meeting follow-up
Status: Required meeting complete; notes recovered from transcript. Owner: Team for remaining follow-up.
Evidence: October 27, Zoom; Isaiah attended from the start, Austin joined late, and the instructor explicitly confirmed completion near 18:26. Recorded duration: 19:16.
Follow-up: review the recovered notes; agree feature requirements/design and owners; publish board issues with linked PRs; keep concise weekly updates. See [discussion record](discussion-record.md).

## Reconstructed discussion record
- Dates: October 25 (game/meeting agreement), October 27 (instructor session), November 4–5 (task allocation).
- Participants: Isaiah, Austin, and Gabriel participated in asynchronous planning. Instructor-session attendance is described separately in the evidence record.
- Decision: Exploding Kittens agreed October 25.
- Historical owners: Austin — player initialization; Isaiah — deck setup; Gabriel — card distribution. Austin also accepted weekly-report work October 25.
- Evidence and limitations: [discussion record](discussion-record.md).
- Meeting cadence: Saturdays, except weeks with a meeting on another day (Isaiah confirmed November 16). Individual meeting outcomes are recorded only when supplied.
- Still pending: new gameplay owners and shared interfaces, GUI/i18n decisions, integration-plan agreement, setup-rule choice, and board URLs.
