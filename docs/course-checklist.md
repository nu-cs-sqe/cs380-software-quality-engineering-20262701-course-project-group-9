# Course checklist

| Item | Where we are | Next step |
| --- | --- | --- |
| Instructor meeting | Completed October 27; [notes](project-management/discussion-record.md) added from the transcript | Follow up on the agreed workflow |
| Weekly planning and progress | [Weekly report](weekly-report/report.md); Saturday meetings confirmed | Weeks 3 and 7 cite Discord and commits. Confirm any further decisions with both teammates |
| Reference project and interface | English console runs setup and turns | Add the remaining card actions and language selection |
| Setup requirements | [Story, acceptance criteria, and use case](requirements/game-setup.md) written | [Issue #30](https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/issues/30) links the criteria and tracks the Canvas edition mismatch |
| Design | [Classes and responsibilities](design/README.md) documented | Agree on gameplay and interface methods |
| BVA and TDD | [BVA index](bva/index.md); separate failing-test and implementation commits for setup, turns, and the console | Keep using test-first development; #8's original sequence is based on Austin's explanation |
| Checkstyle and SpotBugs | Configured and passing | Keep new code passing |
| JaCoCo and PIT | Configured; [results and remaining mutants](quality-tools.md) documented | Investigate the two surviving shuffle mutations and the remaining domain coverage gap |
| Integration tests | Local tests cover setup and playing turns through a winner | Review the [integration scenarios](testing/integration-plan.md) and assign the remaining work |
| Internationalization | [Plan](design/i18n-plan.md) drafted | English/Spanish selected; assign implementation and translation review. A requires two languages at game start and easy addition of locales without changing existing code |
| Finished product | CLI plays through the last-survivor win. Favor, See the Future, and Shuffle are on main | Implement the remaining card effects, each member’s invented card, the additional win condition, and locale support |

The language and two-feature integration requirements above come from page 3 of the project grading rubric under A: Excellent. Week 7 also asks us to discuss both plans and document them on the project board. The board tracks [integration testing](https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/issues/29) and [English/Spanish](https://github.com/nu-cs-sqe/cs380-software-quality-engineering-20262701-course-project-group-9/issues/28). The team still needs to agree on owners.
