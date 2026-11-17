# Course checklist

| Item | Where we are | Next step |
| --- | --- | --- |
| Instructor meeting | Completed October 27; [notes](project-management/discussion-record.md) added from the transcript | Follow up on the agreed workflow |
| Weekly planning and progress | [Weekly report](weekly-report/report.md); Saturday meetings confirmed | Add missing meeting decisions and next owners |
| Reference project and interface | Reference project informed our game choice; GUI/CLI still undecided | Agree on the interface and review the relevant example |
| Setup requirements | [Story, acceptance criteria, and use case](requirements/game-setup.md) written | Add them to the board |
| Design | [Classes and responsibilities](design/README.md) documented | Agree on gameplay and interface methods |
| BVA and TDD | [BVA index](bva/index.md); separate red/green commits for new setup/deck work | Keep using test-first development; #8's original sequence is based on Austin's explanation |
| Checkstyle and SpotBugs | Configured and passing | Keep new code passing |
| JaCoCo and PIT | Configured; [results and remaining mutants](quality-tools.md) documented | Address the shuffle survivors; reassess Player.isAlive when adding elimination |
| Integration tests | Setup tests run; [remaining plan](testing/integration-plan.md) drafted | Assign and implement gameplay tests; A requires integration testing on at least two main features |
| Internationalization | [Plan](design/i18n-plan.md) drafted | Choose languages and owner; A requires two languages at game start and easy addition of locales without changing existing code |
| Finished product | Setup works; 98 tests passed locally | Build the interface, turns, card effects, elimination, and winning |

The language and two-feature integration requirements above come from page 3 of the project grading rubric under A: Excellent. Week 7 also asks us to discuss both plans and document them on the project board. The plans are still awaiting team review and publication.
