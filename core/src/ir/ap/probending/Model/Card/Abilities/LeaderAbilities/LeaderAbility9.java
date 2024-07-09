package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;

import java.util.ArrayList;

public class LeaderAbility9 extends Ability {
    @Override
    public void executeAbility(Card card) {
        if (!GameController.getGame().isRestoreCardRandomlyActivated()){
            ArrayList<Card> weatherCards = new ArrayList<>();
            for (Card c : GameController.getGame().getCurrentPlayer().getDeck()) {
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
        else {
            ArrayList<Card> weatherCards = new ArrayList<>();
            for (Card c : GameController.getGame().getCurrentPlayer().getDeck()) {
                if (c.getName().equals("Rain") || c.getName().equals("Fog") || c.getName().equals("Frost") || c.getName().equals("Clear") || c.getName().equals("Storm")) {
                    weatherCards.add(c);
                }
            }

            GameController.getGame().playCard(weatherCards.get((int) (Math.random() * weatherCards.size())) , GameController.getGame().getCurrentPlayer() , true);
        }
    }
}
