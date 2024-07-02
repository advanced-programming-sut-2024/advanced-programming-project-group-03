package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;

import java.util.ArrayList;

public class LeaderAbility9 extends Ability {
    @Override
    public void executeAbility(Card card) {
        ArrayList<Card> weatherCards = new ArrayList<>();
        for (Card c : Game.getGame().getCurrentPlayer().getDeck()) {
            if (c.getName().equals("Rain") || c.getName().equals("Fog") || c.getName().equals("Frost") || c.getName().equals("Clear") || c.getName().equals("Storm")) {
                weatherCards.add(c);
            }
        }

        if (!weatherCards.isEmpty()){
            GameUIController.getGameUIController().clearCardListWindow();
            GameUIController.getGameUIController().activateCardListWindow();
            ArrayList<Card> cards = new ArrayList<>();
            for (Card c : weatherCards) {
                cards.add(c.clone3());
            }
            GameUIController.getGameUIController().addCardsToCardListWindow(cards);
        }
    }
}
