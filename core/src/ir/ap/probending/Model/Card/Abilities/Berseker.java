package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Card.CardObjects;
import ir.ap.probending.Control.Game;

public class Berseker extends Ability{
    public void executeAbility(Card card) {
        if (Game.getGame().getCurrentTurn() == 1){
            if (Game.getGame().getGameBoard().getPlayer1Board().getCardsRow(card) == 0){
                if (Game.getGame().getGameBoard().getPlayer1Board().getCommander7() != null && Game.getGame().getGameBoard().getPlayer1Board().getCommander7().getAbility() instanceof Mardroeme) {
                    Game.getGame().getGameBoard().getPlayer1Board().getSiege().remove(card);
                    Game.getGame().getGameBoard().getPlayer1Board().addCardToSiege(CardObjects.getBear());
                    Game.getGame().getGameBoard().getPlayer1Board().setCommander7(null);
                }
                else if (Game.getGame().getGameBoard().getPlayer1Board().doesSiegeHaveMardroeme()){
                    Game.getGame().getGameBoard().getPlayer1Board().getSiege().remove(card);
                    Game.getGame().getGameBoard().getPlayer1Board().addCardToSiege(CardObjects.getBear());
                }
            }
            else if (Game.getGame().getGameBoard().getPlayer1Board().getCardsRow(card) == 1){
                if (Game.getGame().getGameBoard().getPlayer1Board().getCommander8() != null && Game.getGame().getGameBoard().getPlayer1Board().getCommander8().getAbility() instanceof Mardroeme) {
                    Game.getGame().getGameBoard().getPlayer1Board().getRanged().remove(card);
                    Game.getGame().getGameBoard().getPlayer1Board().addCardToRanged(CardObjects.getBear());
                    Game.getGame().getGameBoard().getPlayer1Board().setCommander8(null);
                }
                else if (Game.getGame().getGameBoard().getPlayer1Board().doesRangedHaveMardroeme()){
                    Game.getGame().getGameBoard().getPlayer1Board().getRanged().remove(card);
                    Game.getGame().getGameBoard().getPlayer1Board().addCardToRanged(CardObjects.getBear());
                }
            }
            else if (Game.getGame().getGameBoard().getPlayer1Board().getCardsRow(card) == 2){
                System.out.println("Berseker");
                if (Game.getGame().getGameBoard().getPlayer1Board().getCommander9() != null && Game.getGame().getGameBoard().getPlayer1Board().getCommander9().getAbility() instanceof Mardroeme) {
                    Game.getGame().getGameBoard().getPlayer1Board().getCloseCombat().remove(card);
                    Game.getGame().getGameBoard().getPlayer1Board().addCardToCloseCombat(CardObjects.getBear());
                    Game.getGame().getGameBoard().getPlayer1Board().setCommander9(null);
                }
                else if (Game.getGame().getGameBoard().getPlayer1Board().doesCloseCombatHaveMardroeme()){
                    Game.getGame().getGameBoard().getPlayer1Board().getCloseCombat().remove(card);
                    Game.getGame().getGameBoard().getPlayer1Board().addCardToCloseCombat(CardObjects.getBear());
                }
            }
        }
        else {
            if (Game.getGame().getGameBoard().getPlayer2Board().getCardsRow(card) == 0){
                if (Game.getGame().getGameBoard().getPlayer2Board().getCommander7() != null && Game.getGame().getGameBoard().getPlayer2Board().getCommander7().getAbility() instanceof Mardroeme) {
                    Game.getGame().getGameBoard().getPlayer2Board().getSiege().remove(card);
                    Game.getGame().getGameBoard().getPlayer2Board().addCardToSiege(CardObjects.getBear());
                    Game.getGame().getGameBoard().getPlayer2Board().setCommander7(null);
                }
                else if (Game.getGame().getGameBoard().getPlayer2Board().doesSiegeHaveMardroeme()){
                    Game.getGame().getGameBoard().getPlayer2Board().getSiege().remove(card);
                    Game.getGame().getGameBoard().getPlayer2Board().addCardToSiege(CardObjects.getBear());
                }
            }
            else if (Game.getGame().getGameBoard().getPlayer2Board().getCardsRow(card) == 1){
                if (Game.getGame().getGameBoard().getPlayer2Board().getCommander8() != null && Game.getGame().getGameBoard().getPlayer2Board().getCommander8().getAbility() instanceof Mardroeme) {
                    Game.getGame().getGameBoard().getPlayer2Board().getRanged().remove(card);
                    Game.getGame().getGameBoard().getPlayer2Board().addCardToRanged(CardObjects.getBear());
                    Game.getGame().getGameBoard().getPlayer2Board().setCommander8(null);
                }
                else if (Game.getGame().getGameBoard().getPlayer2Board().doesRangedHaveMardroeme()){
                    Game.getGame().getGameBoard().getPlayer2Board().getRanged().remove(card);
                    Game.getGame().getGameBoard().getPlayer2Board().addCardToRanged(CardObjects.getBear());
                }
            }
            else if (Game.getGame().getGameBoard().getPlayer2Board().getCardsRow(card) == 2){
                if (Game.getGame().getGameBoard().getPlayer2Board().getCommander9() != null && Game.getGame().getGameBoard().getPlayer2Board().getCommander9().getAbility() instanceof Mardroeme) {
                    Game.getGame().getGameBoard().getPlayer2Board().getCloseCombat().remove(card);
                    Game.getGame().getGameBoard().getPlayer2Board().addCardToCloseCombat(CardObjects.getBear());
                    Game.getGame().getGameBoard().getPlayer2Board().setCommander9(null);
                }
                else if (Game.getGame().getGameBoard().getPlayer2Board().doesCloseCombatHaveMardroeme()){
                    Game.getGame().getGameBoard().getPlayer2Board().getCloseCombat().remove(card);
                    Game.getGame().getGameBoard().getPlayer2Board().addCardToCloseCombat(CardObjects.getBear());
                }
            }
        }

        GameUIController.getGameUIController().updateRows();
    }
}
