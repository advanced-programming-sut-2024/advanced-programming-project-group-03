package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;

public class LeaderAbility7 extends Ability {
    @Override
    public void executeAbility(Card card) {
        int size = Game.getGame().getCurrentPlayer().getBurntCards().size();
        int random = (int) (Math.random() * size);
        Card randomCard = Game.getGame().getCurrentPlayer().getBurntCards().get(random);
        Game.getGame().getCurrentPlayer().getBurntCards().remove(randomCard);
        Game.getGame().getCurrentPlayer().getHand().add(randomCard);
        Game.getGame().setUpHandView(Game.getGame().getCurrentPlayer());
    }
}
