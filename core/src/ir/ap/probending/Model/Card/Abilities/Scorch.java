package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;

import java.util.ArrayList;

public class Scorch extends Ability{
    public void executeAbility(Card card) {
        int highestPower = 0;

        ArrayList<Card> cards1 = new ArrayList<>(Game.getGame().getGameBoard().getPlayer1Board().getAllCards());
        ArrayList<Card> cards2 = new ArrayList<>(Game.getGame().getGameBoard().getPlayer2Board().getAllCards());

        for (Card c : cards1) {
            if (c.getPower() > highestPower && !c.isHero() && !c.equals(card)) {
                highestPower = c.getPower();
            }
        }

        for (Card c : cards2) {
            if (c.getPower() > highestPower && !c.isHero() && !c.equals(card)){
                highestPower = c.getPower();
            }
        }

        if (highestPower > 0) {
            for (Card c : cards1) {
                if (c.getPower() == highestPower && !c.isHero() && !c.equals(card)) {
                    Game.getGame().getGameBoard().getPlayer1Board().removeCardFromCloseCombat(c);
                    Game.getGame().getGameBoard().getPlayer1Board().removeCardFromRanged(c);
                    Game.getGame().getGameBoard().getPlayer1Board().removeCardFromSiege(c);
                }
            }

            for (Card c : cards2) {
                if (c.getPower() == highestPower && !c.isHero() && !c.equals(card)) {
                    Game.getGame().getGameBoard().getPlayer2Board().removeCardFromCloseCombat(c);
                    Game.getGame().getGameBoard().getPlayer2Board().removeCardFromRanged(c);
                    Game.getGame().getGameBoard().getPlayer2Board().removeCardFromSiege(c);
                }
            }
        }
    }
}
