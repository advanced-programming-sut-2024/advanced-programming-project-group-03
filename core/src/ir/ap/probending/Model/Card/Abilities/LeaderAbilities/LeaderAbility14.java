package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;

public class LeaderAbility14 extends Ability {
    @Override
    public void executeAbility(Card card) {
        Card frostCardFromDeck = null;
        for (Card c : GameController.getGame().getCurrentPlayer().getDeck()) {
            if (c.getName().equals("Frost")) {
                frostCardFromDeck = c;
                break;
            }
        }

        if (frostCardFromDeck != null) {
            GameController.getGame().playCard(frostCardFromDeck , GameController.getGame().getCurrentPlayer());
            GameController.getGame().getCurrentPlayer().removeCardFromDeckCards(frostCardFromDeck);
        }
        GameController.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
