package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Abilities.Agile;
import ir.ap.probending.Model.Card.Abilities.Agile2;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;

public class LeaderAbility15 extends Ability {
    @Override
    public void executeAbility(Card card) {
        boolean isRow0Good = false;
        boolean isRow1Good = false;
        boolean isRow2Good = false;

        if (GameController.getGame().getCurrentTurn() == 1){
            if (GameController.getGame().getGameBoard().getPlayer1Board().getCommander7() != null || GameController.getGame().getGameBoard().getPlayer1Board().isCommander7Played()){
                isRow0Good = true;
            }
            if (GameController.getGame().getGameBoard().getPlayer1Board().getCommander8() != null || GameController.getGame().getGameBoard().getPlayer1Board().isCommander8Played()){
                isRow1Good = true;
            }
            if (GameController.getGame().getGameBoard().getPlayer1Board().getCommander9() != null || GameController.getGame().getGameBoard().getPlayer1Board().isCommander9Played()){
                isRow2Good = true;
            }

            if (isRow0Good){
                for (Card c : GameController.getGame().getGameBoard().getPlayer1Board().getRanged()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        GameController.getGame().getGameBoard().getPlayer1Board().getRanged().remove(c);
                        GameController.getGame().getGameBoard().getPlayer1Board().getSiege().add(card);
                    }
                }
                for (Card c : GameController.getGame().getGameBoard().getPlayer1Board().getCloseCombat()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        GameController.getGame().getGameBoard().getPlayer1Board().getCloseCombat().remove(c);
                        GameController.getGame().getGameBoard().getPlayer1Board().getSiege().add(card);
                    }
                }
            }
            else if (isRow2Good){
                for (Card c : GameController.getGame().getGameBoard().getPlayer1Board().getRanged()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        GameController.getGame().getGameBoard().getPlayer1Board().getRanged().remove(c);
                        GameController.getGame().getGameBoard().getPlayer1Board().getCloseCombat().add(card);
                    }
                }
                for (Card c : GameController.getGame().getGameBoard().getPlayer1Board().getSiege()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        GameController.getGame().getGameBoard().getPlayer1Board().getSiege().remove(c);
                        GameController.getGame().getGameBoard().getPlayer1Board().getCloseCombat().add(card);
                    }
                }
            }
            else if (isRow1Good){
                for (Card c : GameController.getGame().getGameBoard().getPlayer1Board().getCloseCombat()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        GameController.getGame().getGameBoard().getPlayer1Board().getCloseCombat().remove(c);
                        GameController.getGame().getGameBoard().getPlayer1Board().getRanged().add(card);
                    }
                }
                for (Card c : GameController.getGame().getGameBoard().getPlayer1Board().getSiege()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        GameController.getGame().getGameBoard().getPlayer1Board().getSiege().remove(c);
                        GameController.getGame().getGameBoard().getPlayer1Board().getRanged().add(card);
                    }
                }
            }
        }
        else {
            if (GameController.getGame().getGameBoard().getPlayer2Board().getCommander7() != null || GameController.getGame().getGameBoard().getPlayer2Board().isCommander7Played()){
                isRow0Good = true;
            }
            if (GameController.getGame().getGameBoard().getPlayer2Board().getCommander8() != null || GameController.getGame().getGameBoard().getPlayer2Board().isCommander8Played()){
                isRow1Good = true;
            }
            if (GameController.getGame().getGameBoard().getPlayer2Board().getCommander9() != null || GameController.getGame().getGameBoard().getPlayer2Board().isCommander9Played()){
                isRow2Good = true;
            }

            if (isRow0Good){
                for (Card c : GameController.getGame().getGameBoard().getPlayer2Board().getRanged()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        GameController.getGame().getGameBoard().getPlayer2Board().getRanged().remove(c);
                        GameController.getGame().getGameBoard().getPlayer2Board().getSiege().add(card);
                    }
                }
                for (Card c : GameController.getGame().getGameBoard().getPlayer2Board().getCloseCombat()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        GameController.getGame().getGameBoard().getPlayer2Board().getCloseCombat().remove(c);
                        GameController.getGame().getGameBoard().getPlayer2Board().getSiege().add(card);
                    }
                }
            }
            else if (isRow2Good){
                for (Card c : GameController.getGame().getGameBoard().getPlayer2Board().getRanged()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        GameController.getGame().getGameBoard().getPlayer2Board().getRanged().remove(c);
                        GameController.getGame().getGameBoard().getPlayer2Board().getCloseCombat().add(card);
                    }
                }
                for (Card c : GameController.getGame().getGameBoard().getPlayer2Board().getSiege()){
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2){
                        GameController.getGame().getGameBoard().getPlayer2Board().getSiege().remove(c);
                        GameController.getGame().getGameBoard().getPlayer2Board().getCloseCombat().add(card);
                    }
                }
            }
            else if (isRow1Good) {
                for (Card c : GameController.getGame().getGameBoard().getPlayer2Board().getCloseCombat()) {
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2) {
                        GameController.getGame().getGameBoard().getPlayer2Board().getCloseCombat().remove(c);
                        GameController.getGame().getGameBoard().getPlayer2Board().getRanged().add(card);
                    }
                }
                for (Card c : GameController.getGame().getGameBoard().getPlayer2Board().getSiege()) {
                    if (c.getAbility() instanceof Agile || c.getAbility() instanceof Agile2) {
                        GameController.getGame().getGameBoard().getPlayer2Board().getSiege().remove(c);
                        GameController.getGame().getGameBoard().getPlayer2Board().getRanged().add(card);

                    }
                }
            }
        }

        GameUIController.getGameUIController().updateRows();
        GameController.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
