package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Card.CardObjects;
import ir.ap.probending.Control.GameController;

public class Berseker extends Ability{
    public void executeAbility(Card card) {
        if (GameController.getGame().getCurrentTurn() == 1){
            if (GameController.getGame().getGameBoard().getPlayer1Board().getCardsRow(card) == 0){
                if (GameController.getGame().getGameBoard().getPlayer1Board().getCommander7() != null && GameController.getGame().getGameBoard().getPlayer1Board().getCommander7().getAbility() instanceof Mardroeme) {
                    GameController.getGame().getGameBoard().getPlayer1Board().getSiege().remove(card);
                    GameController.getGame().getGameBoard().getPlayer1Board().addCardToSiege(CardObjects.getBear());
                    GameController.getGame().getGameBoard().getPlayer1Board().setCommander7(null);
                }
                else if (GameController.getGame().getGameBoard().getPlayer1Board().doesSiegeHaveMardroeme()){
                    GameController.getGame().getGameBoard().getPlayer1Board().getSiege().remove(card);
                    GameController.getGame().getGameBoard().getPlayer1Board().addCardToSiege(CardObjects.getBear());
                }
            }
            else if (GameController.getGame().getGameBoard().getPlayer1Board().getCardsRow(card) == 1){
                if (GameController.getGame().getGameBoard().getPlayer1Board().getCommander8() != null && GameController.getGame().getGameBoard().getPlayer1Board().getCommander8().getAbility() instanceof Mardroeme) {
                    GameController.getGame().getGameBoard().getPlayer1Board().getRanged().remove(card);
                    GameController.getGame().getGameBoard().getPlayer1Board().addCardToRanged(CardObjects.getBear());
                    GameController.getGame().getGameBoard().getPlayer1Board().setCommander8(null);
                }
                else if (GameController.getGame().getGameBoard().getPlayer1Board().doesRangedHaveMardroeme()){
                    GameController.getGame().getGameBoard().getPlayer1Board().getRanged().remove(card);
                    GameController.getGame().getGameBoard().getPlayer1Board().addCardToRanged(CardObjects.getBear());
                }
            }
            else if (GameController.getGame().getGameBoard().getPlayer1Board().getCardsRow(card) == 2){
                System.out.println("Berseker");
                if (GameController.getGame().getGameBoard().getPlayer1Board().getCommander9() != null && GameController.getGame().getGameBoard().getPlayer1Board().getCommander9().getAbility() instanceof Mardroeme) {
                    GameController.getGame().getGameBoard().getPlayer1Board().getCloseCombat().remove(card);
                    GameController.getGame().getGameBoard().getPlayer1Board().addCardToCloseCombat(CardObjects.getBear());
                    GameController.getGame().getGameBoard().getPlayer1Board().setCommander9(null);
                }
                else if (GameController.getGame().getGameBoard().getPlayer1Board().doesCloseCombatHaveMardroeme()){
                    GameController.getGame().getGameBoard().getPlayer1Board().getCloseCombat().remove(card);
                    GameController.getGame().getGameBoard().getPlayer1Board().addCardToCloseCombat(CardObjects.getBear());
                }
            }
        }
        else {
            if (GameController.getGame().getGameBoard().getPlayer2Board().getCardsRow(card) == 0){
                if (GameController.getGame().getGameBoard().getPlayer2Board().getCommander7() != null && GameController.getGame().getGameBoard().getPlayer2Board().getCommander7().getAbility() instanceof Mardroeme) {
                    GameController.getGame().getGameBoard().getPlayer2Board().getSiege().remove(card);
                    GameController.getGame().getGameBoard().getPlayer2Board().addCardToSiege(CardObjects.getBear());
                    GameController.getGame().getGameBoard().getPlayer2Board().setCommander7(null);
                }
                else if (GameController.getGame().getGameBoard().getPlayer2Board().doesSiegeHaveMardroeme()){
                    GameController.getGame().getGameBoard().getPlayer2Board().getSiege().remove(card);
                    GameController.getGame().getGameBoard().getPlayer2Board().addCardToSiege(CardObjects.getBear());
                }
            }
            else if (GameController.getGame().getGameBoard().getPlayer2Board().getCardsRow(card) == 1){
                if (GameController.getGame().getGameBoard().getPlayer2Board().getCommander8() != null && GameController.getGame().getGameBoard().getPlayer2Board().getCommander8().getAbility() instanceof Mardroeme) {
                    GameController.getGame().getGameBoard().getPlayer2Board().getRanged().remove(card);
                    GameController.getGame().getGameBoard().getPlayer2Board().addCardToRanged(CardObjects.getBear());
                    GameController.getGame().getGameBoard().getPlayer2Board().setCommander8(null);
                }
                else if (GameController.getGame().getGameBoard().getPlayer2Board().doesRangedHaveMardroeme()){
                    GameController.getGame().getGameBoard().getPlayer2Board().getRanged().remove(card);
                    GameController.getGame().getGameBoard().getPlayer2Board().addCardToRanged(CardObjects.getBear());
                }
            }
            else if (GameController.getGame().getGameBoard().getPlayer2Board().getCardsRow(card) == 2){
                if (GameController.getGame().getGameBoard().getPlayer2Board().getCommander9() != null && GameController.getGame().getGameBoard().getPlayer2Board().getCommander9().getAbility() instanceof Mardroeme) {
                    GameController.getGame().getGameBoard().getPlayer2Board().getCloseCombat().remove(card);
                    GameController.getGame().getGameBoard().getPlayer2Board().addCardToCloseCombat(CardObjects.getBear());
                    GameController.getGame().getGameBoard().getPlayer2Board().setCommander9(null);
                }
                else if (GameController.getGame().getGameBoard().getPlayer2Board().doesCloseCombatHaveMardroeme()){
                    GameController.getGame().getGameBoard().getPlayer2Board().getCloseCombat().remove(card);
                    GameController.getGame().getGameBoard().getPlayer2Board().addCardToCloseCombat(CardObjects.getBear());
                }
            }
        }

        GameUIController.getGameUIController().updateRows();
    }
}
