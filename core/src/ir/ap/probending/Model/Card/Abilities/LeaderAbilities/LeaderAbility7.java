package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;
import ir.ap.probending.View.GameUIController;

public class LeaderAbility7 extends Ability {
    @Override
    public void executeAbility(Card card) {
        if (GameController.getGame().getCurrentPlayer().getBurntCards().size() > 1){
            int size = GameController.getGame().getCurrentPlayer().getBurntCards().size();
            int random = (int) (Math.random() * size);
            Card randomCard = GameController.getGame().getCurrentPlayer().getBurntCards().get(random);
            GameController.getGame().getCurrentPlayer().getBurntCards().remove(randomCard);
            GameController.getGame().getCurrentPlayer().getHand().add(randomCard);
            GameUIController.getGameUIController().setUpHandView(GameController.getGame().getCurrentPlayer());
        }
    }
}
