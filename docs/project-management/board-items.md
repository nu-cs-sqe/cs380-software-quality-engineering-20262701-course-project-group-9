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

## Card: Verify required instructor meeting
Status: Evidence needed. Owner: Team.
Acceptance: record actual meeting date, attendees, instructor feedback, and follow-up actions in the weekly report. If not held, arrange the required meeting. Do not mark complete from an assumption.

## Discussion record to complete after the real discussion
Date: pending. Attendees: pending. Decisions: pending. Agreed owners: pending. Board URLs: pending. These fields cannot be completed from repository history alone.
