# Course checklist

| Item | Where we are | Next step |
| --- | --- | --- |
| Instructor meeting | Completed October 27; [notes](project-management/discussion-record.md) added from the transcript | Follow up on the agreed workflow |
| Weekly planning and progress | [Weekly report](weekly-report/report.md); Saturday meetings confirmed | Add missing meeting decisions and next owners |
| Reference project and interface | English console runs setup and turns | Add the remaining card actions and language selection |
| Setup requirements | [Story, acceptance criteria, and use case](requirements/game-setup.md) written | Add them to the board |
| Design | [Classes and responsibilities](design/README.md) documented | Agree on gameplay and interface methods |
| BVA and TDD | [BVA index](bva/index.md); separate failing-test and implementation commits for setup, turns, and the console | Keep using test-first development; #8's original sequence is based on Austin's explanation |
| Checkstyle and SpotBugs | Configured and passing | Keep new code passing |
| JaCoCo and PIT | Configured; [results and remaining mutants](quality-tools.md) documented | Investigate the two surviving shuffle mutations and the remaining domain coverage gap |
| Integration tests | Local tests cover setup and playing turns through a winner | Review the [integration scenarios](testing/integration-plan.md) and assign the remaining work |
| Internationalization | [Plan](design/i18n-plan.md) drafted | English/Spanish selected; assign implementation and translation review. A requires two languages at game start and easy addition of locales without changing existing code |
| Finished product | Local CLI plays through the last-survivor win; 125 tests passed | Implement the remaining card effects, each member’s invented card, the additional win condition, and locale support |

The language and two-feature integration requirements above come from page 3 of the project grading rubric under A: Excellent. Week 7 also asks us to discuss both plans and document them on the project board. The plans are still awaiting team review and publication.
