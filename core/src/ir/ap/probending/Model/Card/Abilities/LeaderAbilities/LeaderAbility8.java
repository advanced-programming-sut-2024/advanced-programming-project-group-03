package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;

import java.util.ArrayList;

public class LeaderAbility8 extends Ability {
    @Override
    public void executeAbility(Card card) {
        Card discardedCard1 = Game.getGame().getCurrentPlayer().getHand().remove(0);
        Card discardedCard2 = Game.getGame().getCurrentPlayer().getHand().remove(0);

        Game.getGame().getCurrentPlayer().getBurntCards().add(discardedCard1);
        Game.getGame().getCurrentPlayer().getBurntCards().add(discardedCard2);

        GameUIController.getGameUIController().clearCardListWindow();
        GameUIController.getGameUIController().activateCardListWindow();
        ArrayList<Card> cards = new ArrayList<>();
        for (Card c : Game.getGame().getCurrentPlayer().getDeck()) {
            cards.add(c.clone7());
        }
        GameUIController.getGameUIController().addCardsToCardListWindow(cards);
        Game.getGame().setUpHandView(Game.getGame().getCurrentPlayer());
    }
}
