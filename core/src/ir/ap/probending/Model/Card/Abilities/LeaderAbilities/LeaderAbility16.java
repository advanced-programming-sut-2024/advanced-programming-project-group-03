package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.Game;

public class LeaderAbility16 extends Ability {
    @Override
    public void executeAbility(Card card) {
        Card frostCardFromDeck = null;
        for (Card c : Game.getGame().getCurrentPlayer().getDeck()) {
            if (c.getName().equals("Rain")) {
                frostCardFromDeck = c;
                break;
            }
        }

        if (frostCardFromDeck != null) {
            Game.getGame().playCard(frostCardFromDeck , Game.getGame().getCurrentPlayer());
            Game.getGame().getCurrentPlayer().removeCardFromDeckCards(frostCardFromDeck);
        }
        Game.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
