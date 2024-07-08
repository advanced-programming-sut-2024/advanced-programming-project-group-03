package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Abilities.Agile;
import ir.ap.probending.Model.Card.Abilities.Agile2;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.Game;

public class LeaderAbility15 extends Ability {
    @Override
    public void executeAbility(Card card) {
        boolean isRow0Good = false;
        boolean isRow1Good = false;
        boolean isRow2Good = false;

        if (Game.getGame().getCurrentTurn() == 1){
            if (Game.getGame().getGameBoard().getPlayer1Board().getCommander7() != null || Game.getGame().getGameBoard().getPlayer1Board().isCommander7Played()){
                isRow0Good = true;
            }
            if (Game.getGame().getGameBoard().getPlayer1Board().getCommander8() != null || Game.getGame().getGameBoard().getPlayer1Board().isCommander8Played()){
                isRow1Good = true;
            }
            if (Game.getGame().getGameBoard().getPlayer1Board().getCommander9() != null || Game.getGame().getGameBoard().getPlayer1Board().isCommander9Played()){
                isRow2Good = true;
            }

            if (isRow0Good){
                for (Card c : Game.getGame().getGameBoard().getPlayer1Board().getRanged()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        Game.getGame().getGameBoard().getPlayer1Board().getRanged().remove(c);
                        Game.getGame().getGameBoard().getPlayer1Board().getSiege().add(card);
                    }
                }
                for (Card c : Game.getGame().getGameBoard().getPlayer1Board().getCloseCombat()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        Game.getGame().getGameBoard().getPlayer1Board().getCloseCombat().remove(c);
                        Game.getGame().getGameBoard().getPlayer1Board().getSiege().add(card);
                    }
                }
            }
            else if (isRow2Good){
                for (Card c : Game.getGame().getGameBoard().getPlayer1Board().getRanged()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        Game.getGame().getGameBoard().getPlayer1Board().getRanged().remove(c);
                        Game.getGame().getGameBoard().getPlayer1Board().getCloseCombat().add(card);
                    }
                }
                for (Card c : Game.getGame().getGameBoard().getPlayer1Board().getSiege()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        Game.getGame().getGameBoard().getPlayer1Board().getSiege().remove(c);
                        Game.getGame().getGameBoard().getPlayer1Board().getCloseCombat().add(card);
                    }
                }
            }
            else if (isRow1Good){
                for (Card c : Game.getGame().getGameBoard().getPlayer1Board().getCloseCombat()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        Game.getGame().getGameBoard().getPlayer1Board().getCloseCombat().remove(c);
                        Game.getGame().getGameBoard().getPlayer1Board().getRanged().add(card);
                    }
                }
                for (Card c : Game.getGame().getGameBoard().getPlayer1Board().getSiege()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        Game.getGame().getGameBoard().getPlayer1Board().getSiege().remove(c);
                        Game.getGame().getGameBoard().getPlayer1Board().getRanged().add(card);
                    }
                }
            }
        }
        else {
            if (Game.getGame().getGameBoard().getPlayer2Board().getCommander7() != null || Game.getGame().getGameBoard().getPlayer2Board().isCommander7Played()){
                isRow0Good = true;
            }
            if (Game.getGame().getGameBoard().getPlayer2Board().getCommander8() != null || Game.getGame().getGameBoard().getPlayer2Board().isCommander8Played()){
                isRow1Good = true;
            }
            if (Game.getGame().getGameBoard().getPlayer2Board().getCommander9() != null || Game.getGame().getGameBoard().getPlayer2Board().isCommander9Played()){
                isRow2Good = true;
            }

            if (isRow0Good){
                for (Card c : Game.getGame().getGameBoard().getPlayer2Board().getRanged()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        Game.getGame().getGameBoard().getPlayer2Board().getRanged().remove(c);
                        Game.getGame().getGameBoard().getPlayer2Board().getSiege().add(card);
                    }
                }
                for (Card c : Game.getGame().getGameBoard().getPlayer2Board().getCloseCombat()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        Game.getGame().getGameBoard().getPlayer2Board().getCloseCombat().remove(c);
                        Game.getGame().getGameBoard().getPlayer2Board().getSiege().add(card);
                    }
                }
            }
            else if (isRow2Good){
                for (Card c : Game.getGame().getGameBoard().getPlayer2Board().getRanged()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        Game.getGame().getGameBoard().getPlayer2Board().getRanged().remove(c);
                        Game.getGame().getGameBoard().getPlayer2Board().getCloseCombat().add(card);
                    }
                }
                for (Card c : Game.getGame().getGameBoard().getPlayer2Board().getSiege()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        Game.getGame().getGameBoard().getPlayer2Board().getSiege().remove(c);
                        Game.getGame().getGameBoard().getPlayer2Board().getCloseCombat().add(card);
                    }
                }
            }
            else if (isRow1Good) {
                for (Card c : Game.getGame().getGameBoard().getPlayer2Board().getCloseCombat()) {
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2) {
                        Game.getGame().getGameBoard().getPlayer2Board().getCloseCombat().remove(c);
                        Game.getGame().getGameBoard().getPlayer2Board().getRanged().add(card);
                    }
                }
                for (Card c : Game.getGame().getGameBoard().getPlayer2Board().getSiege()) {
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2) {
                        Game.getGame().getGameBoard().getPlayer2Board().getSiege().remove(c);
                        Game.getGame().getGameBoard().getPlayer2Board().getRanged().add(card);

                    }
                }
            }
        }

        GameUIController.getGameUIController().updateRows();
        Game.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
