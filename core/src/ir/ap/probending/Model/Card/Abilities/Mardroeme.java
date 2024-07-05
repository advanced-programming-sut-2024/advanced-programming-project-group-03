package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Card.CardObjects;
import ir.ap.probending.Model.Game.Game;

public class Mardroeme extends Ability{
    public void executeAbility(Card card) {
        if (Game.getGame().getCurrentTurn() == 1){
            if ((Game.getGame().getGameBoard().getPlayer1Board().getCommander7() != null && Game.getGame().getGameBoard().getPlayer1Board().getCommander7().equals(card) )|| Game.getGame().getGameBoard().getPlayer1Board().doesSiegeHaveMardroeme()){
                for (Card c : Game.getGame().getGameBoard().getPlayer1Board().getSiege()){
                    if (c.getAbility() instanceof Berseker){
                        Game.getGame().getGameBoard().getPlayer1Board().getSiege().remove(c);
                        Game.getGame().getGameBoard().getPlayer1Board().addCardToSiege(CardObjects.getBear());
                    }
                }
            }
            else if ((Game.getGame().getGameBoard().getPlayer1Board().getCommander8() != null && Game.getGame().getGameBoard().getPlayer1Board().getCommander8().equals(card)) || Game.getGame().getGameBoard().getPlayer1Board().doesRangedHaveMardroeme()){
                for (Card c : Game.getGame().getGameBoard().getPlayer1Board().getRanged()){
                    if (c.getAbility() instanceof Berseker){
                        Game.getGame().getGameBoard().getPlayer1Board().getRanged().remove(c);
                        Game.getGame().getGameBoard().getPlayer1Board().addCardToRanged(CardObjects.getBear());
                    }
                }
            }
            else if ((Game.getGame().getGameBoard().getPlayer1Board().getCommander9() != null && Game.getGame().getGameBoard().getPlayer1Board().getCommander9().equals(card)) || Game.getGame().getGameBoard().getPlayer1Board().doesCloseCombatHaveMardroeme()){
                for (Card c : Game.getGame().getGameBoard().getPlayer1Board().getCloseCombat()){
                    if (c.getAbility() instanceof Berseker){
                        Game.getGame().getGameBoard().getPlayer1Board().getCloseCombat().remove(c);
                        Game.getGame().getGameBoard().getPlayer1Board().addCardToCloseCombat(CardObjects.getBear());
                    }
                }
            }
        }
        else {
            if ((Game.getGame().getGameBoard().getPlayer2Board().getCommander7() != null && Game.getGame().getGameBoard().getPlayer2Board().getCommander7().equals(card)) || Game.getGame().getGameBoard().getPlayer2Board().doesSiegeHaveMardroeme()){
                for (Card c : Game.getGame().getGameBoard().getPlayer2Board().getSiege()){
                    if (c.getAbility() instanceof Berseker){
                        Game.getGame().getGameBoard().getPlayer2Board().getSiege().remove(c);
                        Game.getGame().getGameBoard().getPlayer2Board().addCardToSiege(CardObjects.getBear());
                    }
                }
            }
            else if ((Game.getGame().getGameBoard().getPlayer2Board().getCommander8() != null && Game.getGame().getGameBoard().getPlayer2Board().getCommander8().equals(card)) || Game.getGame().getGameBoard().getPlayer2Board().doesRangedHaveMardroeme()){
                for (Card c : Game.getGame().getGameBoard().getPlayer2Board().getRanged()){
                    if (c.getAbility() instanceof Berseker){
                        Game.getGame().getGameBoard().getPlayer2Board().getRanged().remove(c);
                        Game.getGame().getGameBoard().getPlayer2Board().addCardToRanged(CardObjects.getBear());
                    }
                }
            }
            else if ((Game.getGame().getGameBoard().getPlayer2Board().getCommander9() != null && Game.getGame().getGameBoard().getPlayer2Board().getCommander9().equals(card)) || Game.getGame().getGameBoard().getPlayer2Board().doesCloseCombatHaveMardroeme()){
                for (Card c : Game.getGame().getGameBoard().getPlayer2Board().getCloseCombat()){
                    if (c.getAbility() instanceof Berseker){
                        Game.getGame().getGameBoard().getPlayer2Board().getCloseCombat().remove(c);
                        Game.getGame().getGameBoard().getPlayer2Board().addCardToCloseCombat(CardObjects.getBear());
                    }
                }
            }

        }
    }
}
