# Team meeting notes

We usually meet on Saturdays, unless we have already met on another day that week. Isaiah confirmed this schedule on November 16. We still need to add any missing notes from individual meetings.

These notes come from our [Discord channel](https://discord.com/channels/1560122354753871912/1560122355588669505) and the October 27 Zoom transcript, reviewed November 16. Times are Central. Our Discord server says Team 13, while the repository is group-9; Isaiah noted this on October 19.

## October 25: game choice and instructor meeting

Isaiah, Austin (Hanwen Liu), and Gabriel Hsieh (Musicalwoofy) agreed to build Exploding Kittens. Austin suggested it because we had a reference project to work from. He also volunteered to update the weekly report.

We agreed to meet with the instructor on Tuesday at 4:30 p.m. Isaiah booked the meeting and shared the details in Discord.

## October 27: meeting with Prof. Zhang

The meeting was scheduled for 4:30 p.m. on Zoom. The recording is 19 minutes and 16 seconds long; Zoom labels it 4:25 p.m. Isaiah attended from the start, and Austin joined late after having trouble signing in. We could not confirm Gabriel's attendance from the transcript. Prof. Zhang said at the end that we had completed the required meeting. Isaiah shared the recording in Discord afterward.

### What we discussed

- **Communication:** Keep in touch and try to meet in person. Contact Prof. Zhang if communication problems hold us up for a week or two. (01:51–03:00; 17:26–18:08)
- **Game choice:** Exploding Kittens works well for splitting up work because most card effects can be built separately once setup is done. (03:02–03:26)
- **Project board:** The board was missing at the time. Set it up so everyone can see the tasks and who is working on them. We still need to check the board and publish the prepared tasks. (around 04:26–05:54)
- **Interface:** Either a GUI or command line is fine. Decide as a team soon. A GUI takes more learning, but the reference project can help. We did not pick an interface during the meeting. (05:54–07:30)
- **Planning and design:** Adapt the example setup story, acceptance criteria, and use case. Agree on the next features and the classes/methods they need before assigning work. Put the design in the issues or in a shared document. (around 07:30–11:00)
- **Branches and PRs:** Assign each task, move it to In Progress, and use BVA and TDD on a feature branch. Open a draft PR after the first commit and link it to the issue. Mark it ready for review when finished and ask a teammate to review it. (through 14:55)
- **Weekly reports:** Update them about once a week. Short progress notes with issue/PR links are enough. Correct earlier entries if little work was done that week. (15:01–16:51)
- **Follow-up:** The required meeting is complete. We can book another meeting or ask questions when we need help. (18:26–19:16)

## November 4–5: task assignments in Discord

| Person | Task | Message |
| --- | --- | --- |
| Austin | Player initialization | November 4, 8:41 p.m. |
| Isaiah | Deck setup | November 4, 11:32 p.m. |
| Gabriel | Card distribution | November 5, 3:10 p.m. |

Turn initialization was also needed, but nobody claimed it in that exchange. Isaiah later implemented it. These were the original assignments; Austin later wrote deck validation in PR #12, and Isaiah added the standard deck and setup integration.

## November 8: card distribution input

Gabriel clarified that CardDistribution takes a list of cards supplied by the caller. It does not need a Deck object or create the standard deck. That left deck construction as separate work. (Discord, 6:16–6:18 p.m.)

## November 24: project choices

Isaiah reported the following choices:

- Use a command-line interface.
- Follow the rules linked in Canvas rather than add house rules.
- Support English and Spanish.
- Isaiah will manage the project board.

On November 26 Isaiah verified the exact Canvas link. The [comparison](../requirements/game-rules.md) and [issue #30](https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/issues/30) record the differences between Grab & Game and the current setup.

## Next team discussion

Agree on the gameplay classes and methods, task owners, deadlines, and reviewers. Assign integration tests and a translation reviewer. Isaiah will maintain the board. See the [live board and issues](board-items.md).
