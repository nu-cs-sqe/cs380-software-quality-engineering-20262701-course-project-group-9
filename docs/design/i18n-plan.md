# Internationalization plan (proposed for team review)

Keep domain card identifiers stable; do not translate strings used by CardDistribution comparisons. When adding UI, place visible labels, prompts, and validation messages in Java ResourceBundle properties. Start with English and a teammate-selected second locale. Pass the selected Locale through the UI; domain logic stays independent of translated text. Test missing-key parity, fallback, Unicode player names, and changing display language without changing card behavior. Avoid joining translated sentence fragments.

This is a proposed plan, not a completed team discussion or project-board entry. The GitHub token lacks read:project, so board publication remains pending.
