# Internationalization plan

Status: proposed and documented locally; not implemented, not yet recorded as a team discussion or published to the project board.

## Scope
Localize UI labels, buttons, prompts, validation messages, turn status, winner announcements, and displayed card names. Preserve player-entered names and stable domain card identifiers. Proposed initial locales: English default and Spanish; the team must confirm the second language and translation reviewer.

## Design
Use Java ResourceBundle with Messages.properties for English and Messages_es.properties for Spanish under src/main/resources/i18n. A UI translation service receives the selected Locale and resolves keys; pass it to views/controllers instead of changing global JVM locale or introducing translated strings into domain logic. Use MessageFormat for complete sentences with named concepts represented by positional arguments, such as turn.current={0}'s turn. Do not concatenate translated fragments. Use UTF-8 resource text and preserve accented names.

Example key groups: setup.playerCount, setup.playerName, setup.start, validation.playerCount, validation.startingPlayer, turn.current, game.winner, card.defuse, card.explodingKitten. Translate card display through a lookup from stable identifiers; CardDistribution must continue matching its existing identifiers. Domain exceptions must not become the displayed text directly; controller maps known failures to translation keys. No resource bundles or translation service exist yet.

## Fallback and user flow
Start with explicit English default; provide a language selector on the future setup screen. A supported language change refreshes visible text without resetting game state. Unsupported locale falls back to English. Test fallback deliberately; missing required keys should fail the test suite rather than be silently shipped. Use layout managers so longer translations are not clipped. Locale-sensitive number formatting belongs in the view.

## Acceptance and tests
| ID | Scenario | Expected result | Status |
| --- | --- | --- | --- |
| I18N01 | Compare required key sets | English and Spanish keys match | Planned |
| I18N02 | Format a message with a player name | Name inserted unchanged; no placeholder visible | Planned |
| I18N03 | Unsupported locale | English fallback | Planned |
| I18N04 | Switch language after setup | Domain IDs, hands, deck, and turn order unchanged | Planned |
| I18N05 | Names such as José and 美咲 | Unicode round-trip preserved | Planned |
| I18N06 | Longer Spanish labels | No truncation in supported window size | Planned manual UI check |

Implement bundles/translation service with unit tests first, then wire UI and add integration tests. Have a teammate review translation accuracy and demonstrate both languages during acceptance testing. Owner and language approval are pending team agreement. Board-ready text is in ../project-management/board-items.md.
