# Team discussions and instructor meeting

Source: [CS 380 Team 13, regular-chat](https://discord.com/channels/1560122354753871912/1560122355588669505), messages reviewed through November 16, 2026. Times below use the displayed Chicago local time. This is an evidence-based reconstruction of asynchronous discussion, not invented meeting minutes. The server calls the team Group 13; the repository URL is group-9. Isaiah acknowledged that discrepancy on October 19.

## October 25 — game and meeting agreement
Participants visible in the discussion: Isaiah Hashimoto, Austin (Hanwen Liu), Musicalwoofy (Gabriel Hsieh; identified by the ghsieh357 GitHub handle shared October 18).

- Austin proposed Exploding Kittens because a reference project was available (October 24, 10:31 p.m.; reiterated October 25).
- Gabriel replied yes at 6:00 p.m. October 25; Austin asked to make Exploding Kittens official at 6:22 p.m.; Isaiah explicitly agreed at 6:23 p.m.
- Austin accepted responsibility for the weekly report request at 6:24 p.m.
- Isaiah proposed Tuesday at 4:30 p.m. for the instructor meeting. Austin and Gabriel accepted at 9:09 p.m.; Isaiah said he would book it at 9:10 p.m. and posted the online meeting details at 9:19 p.m.

## October 27 — instructor meeting

Scheduled: Tuesday, October 27, 2026, 4:30 p.m., Zoom. The recording page displays 4:25 p.m. Central and a recorded duration of 19:16; that metadata is distinct from the scheduled time. Source: the Zoom transcript open in Safari, reviewed November 16, supplemented by Discord messages. The private recording URL is omitted.

Attendance: Yiji Zhang and Isaiah Hashimoto were present from the start. The instructor initially noted the other two teammates were absent, then acknowledged Austin later during the design/BVA discussion and addressed him near 17:00 about the material he missed. Austin therefore joined late despite his earlier Discord sign-in difficulty. Gabriel's attendance is not evidenced in the transcript. At 18:26–18:52 the instructor explicitly said the required meeting was complete; another required first meeting is not pending.

### Instructor notes and follow-up

These are paraphrased notes from the transcript, not verbatim minutes. Times are elapsed recording times where visible.

- **Team coordination (01:51–03:00; 17:26–18:08):** keep communication active, try meeting in person, and contact the instructor if communication problems persist for a week or two. Use the board to make ownership clear.
- **Game choice (03:02–03:26):** Isaiah confirmed Exploding Kittens. The instructor supported the choice because most card behaviors can be developed independently after setup, with some dependencies.
- **Project board (around 04:26–05:54):** repository setup was present, but the project management board was missing at the time. Create it from the repository Projects area. Current publication/access remains a separate pending action; the transcript does not prove it was later created.
- **Interface (05:54–07:30):** choose GUI or CLI with the team soon; either is acceptable. GUI has a learning curve, and the reference project can help. No final interface/library decision was made on the call.
- **Requirements and design (around 07:30–11:00):** the provided setup user story, acceptance criteria, and use case can be adapted. Agree upcoming features as a team, add meaningful issues to the board backlog, and agree classes/methods before splitting work. Design may live in board items or one central document.
- **Implementation and review (design discussion through 14:55):** assign a person, move started work to In Progress, perform BVA and TDD on a feature branch, and open a draft PR once the branch has its first commit. Give the PR a meaningful title/description, link one or more PRs to the issue, and mark it ready for teammate review when complete. The board describes the feature/task; linked PRs expose implementation progress.
- **Weekly reports (15:01–16:51):** update roughly once a week. Concise actual status and links to PRs/issues are enough; lengthy duplicate explanations are unnecessary. Correct earlier weeks to reflect what actually happened, including limited progress, and add later weeks as needed.
- **Meeting completion (18:26–19:16):** required meeting complete; further meetings, email, or course discussion questions are available when needed. Recording was subsequently shared in Discord.

### Current disposition

The game choice, central design documentation, setup requirements, BVA/test work, and quality tooling now have repository evidence. The remaining team decisions are listed in [board drafts](board-items.md): interface, next feature interfaces/owners, integration plan, localization, and the setup-rule variant. Board publication and evidence links still need completion. Team members who missed part or all of the meeting can review the shared recording.

## Recurring team meetings

Isaiah confirmed on November 16 that the team has been meeting on Saturdays, except in weeks when a meeting took place on another day. This is the recorded team-reported cadence. Discord exchanges above/below are asynchronous unless explicitly described as meetings; they do not establish replacement meeting dates by themselves. Exact times, attendees, and outcomes for individual Saturdays are not reconstructed without notes. The October 27 instructor meeting is recorded separately.

## November 4–5 — setup task allocation (asynchronous)
| Person | Stated commitment | Evidence |
| --- | --- | --- |
| Austin / Hanwen Liu | Player initialization | November 4, 8:41 p.m. |
| Isaiah Hashimoto | Deck setup | November 4, 11:32 p.m. |
| Gabriel Hsieh / Musicalwoofy | Card distribution | November 5, 3:10 p.m. |

Austin listed player initialization, deck setup, distribution, and turn initialization as remaining work. No turn-initialization volunteer appears in that exchange; later repository commits establish Isaiah implemented it. Historical commitments are not the same as eventual authorship: Austin later authored deck validation PR #12, while Isaiah implemented the standard catalog/integration work.

## November 8 — collaborator interface decision
Gabriel reported completing card distribution and explicitly stated that it takes a caller-supplied deck, does not require a deck object, and does not construct the official catalog or determine its counts (6:16–6:18 p.m.). This supports the separation between CardDistribution and later StandardDeck work.

## Still needing team agreement

- GUI/CLI and library choice, localization languages and owner, integration plan, and extra-Defuse rule.
- Next gameplay interfaces, owners, deadlines, reviewers, and board item URLs.
- Specific meeting outcomes when available; the recurring Saturday cadence is confirmed.

Sources are Discord, the instructor-meeting transcript, and Isaiah's November 16 clarification. No email evidence was used.
