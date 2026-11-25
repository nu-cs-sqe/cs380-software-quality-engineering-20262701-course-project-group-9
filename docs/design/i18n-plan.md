# Internationalization plan

This is a draft for the team to review. We have not implemented translations yet.

## Course requirement

Week 7 asks us to discuss and document an i18n plan on the project board. The project grading rubric, page 3 under **A: Excellent**, also requires players to choose between at least two languages at the beginning of the game. Adding another locale must not require changes to existing code. The rubric lists this under A, not B or C.

We will use English and Spanish, as reported by Isaiah on November 24. We still need someone to check the Spanish translations.

## How we would build it

Keep displayed text in Java ResourceBundle files under src/main/resources/i18n. Use Messages.properties for English and a separate file for each additional language, such as Messages_es.properties for Spanish. Load available locales from configuration so adding a translation file and configuration entry does not require editing Java classes.

The view would look up labels, prompts, validation messages, turn messages, winner messages, and card names by key. Card IDs inside the game logic would stay the same. Player names would stay exactly as entered.

Use MessageFormat for complete messages with placeholders for names and numbers. Avoid building sentences from translated fragments. Keep the files in UTF-8 so accented characters work. Example keys: setup.start, validation.playerCount, turn.current, game.winner, and card.defuse. The controller would turn known validation errors into message keys rather than displaying exception text directly.

## Player experience

Let players choose a language before starting. Use English as the default and as a fallback for unsupported locales. Changing languages should only change the displayed text, not the cards, players, or turn order. Switching during a game is a proposed extra feature; the rubric only specifies a choice at the beginning.

Keep console prompts readable when translated text is longer. Format numbers in the view. Missing translation keys should fail tests before we ship them.

## Tests to add

| ID | Test | Expected result |
| --- | --- | --- |
| I18N01 | Compare translation keys | Each supported language has every required key |
| I18N02 | Insert a player's name into a message | Name is unchanged and no placeholder is left visible |
| I18N03 | Request an unsupported locale | English is used |
| I18N04 | Change the selected language | Hands, deck, player IDs, and turn order stay the same |
| I18N05 | Use names such as José and 美咲 | Characters are preserved |
| I18N06 | Display longer translated labels | Console prompts remain readable; check manually |

All six tests are planned. Write the translation tests first, add the resource files and lookup code, then connect the interface. A teammate should check the translations. We still need an implementation owner and translation reviewer before posting the [board task](../project-management/board-items.md).
