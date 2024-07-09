package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;

import java.util.ArrayList;

public class LeaderAbility8 extends Ability {
    @Override
    public void executeAbility(Card card) {
        if (!GameController.getGame().isRestoreCardRandomlyActivated()){
            Card discardedCard1 = GameController.getGame().getCurrentPlayer().getHand().remove(0);
            Card discardedCard2 = GameController.getGame().getCurrentPlayer().getHand().remove(0);

            GameController.getGame().getCurrentPlayer().getBurntCards().add(discardedCard1);
            GameController.getGame().getCurrentPlayer().getBurntCards().add(discardedCard2);

            GameUIController.getGameUIController().clearCardListWindow();
            GameUIController.getGameUIController().activateCardListWindow();
            ArrayList<Card> cards = new ArrayList<>();
            for (Card c : GameController.getGame().getCurrentPlayer().getDeck()) {
                cards.add(c.clone7());
            }
            GameUIController.getGameUIController().addCardsToCardListWindow(cards);
            GameUIController.getGameUIController().setUpHandView(GameController.getGame().getCurrentPlayer());
        }
        else {
            Card discardedCard1 = GameController.getGame().getCurrentPlayer().getHand().remove(0);
            Card discardedCard2 = GameController.getGame().getCurrentPlayer().getHand().remove(0);

            GameController.getGame().getCurrentPlayer().getBurntCards().add(discardedCard1);
            GameController.getGame().getCurrentPlayer().getBurntCards().add(discardedCard2);

            GameController.getGame().getCurrentPlayer().getHand().add(GameController.getGame().getCurrentPlayer().getDeck().remove(0));
            GameUIController.getGameUIController().setUpHandView(GameController.getGame().getCurrentPlayer());
        }
    }
}
