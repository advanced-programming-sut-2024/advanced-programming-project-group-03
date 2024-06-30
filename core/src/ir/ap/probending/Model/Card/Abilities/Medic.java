package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;

import java.util.ArrayList;

public class Medic extends Ability{
    public void executeAbility(Card card) {

        ArrayList<Card> burntCards = new ArrayList<>();
        for (Card burntCard : Game.getGame().getCurrentPlayer().getBurntCards()) {
            if (!burntCard.isHero()){
                burntCards.add(burntCard.clone3());
            }
        }

        if (!burntCards.isEmpty()) {
            GameUIController.getGameUIController().activateCardListWindow();
            GameUIController.getGameUIController().addCardsToCardListWindow(burntCards);
        }
    }
}
