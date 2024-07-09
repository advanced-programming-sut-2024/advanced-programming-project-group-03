package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;
import ir.ap.probending.Model.Game.Player;

import java.util.ArrayList;

public class Muster extends Ability{
    public void executeAbility(Card card) {
        Player player = GameController.getGame().getCurrentPlayer();
        ArrayList<Card> cards = new ArrayList<>();
        for (Card deckCard : player.getDeck()){
            if (deckCard.getName().equals(card.getName())){

                switch (card.getPlayingRow()){
                    case 0:
                        if (player.equals(GameController.getGame().getGameBoard().getPlayer1()))
                            GameController.getGame().getGameBoard().getPlayer1Board().addCardToSiege(card.clone4());
                        else
                            GameController.getGame().getGameBoard().getPlayer2Board().addCardToSiege(card.clone4());
                        break;
                    case 1:
                        if (player.equals(GameController.getGame().getGameBoard().getPlayer1()))
                            GameController.getGame().getGameBoard().getPlayer1Board().addCardToRanged(card.clone4());
                        else
                            GameController.getGame().getGameBoard().getPlayer2Board().addCardToRanged(card.clone4());
                        break;
                    case 2:
                        if (player.equals(GameController.getGame().getGameBoard().getPlayer1()))
                            GameController.getGame().getGameBoard().getPlayer1Board().addCardToCloseCombat(card.clone4());
                        else
                            GameController.getGame().getGameBoard().getPlayer2Board().addCardToCloseCombat(card.clone4());
                        break;
                    case 6:
                        GameController.getGame().getGameBoard().addSpellCard(card.clone4());
                        break;
                }

                cards.add(deckCard);
            }
        }

        GameController.getGame().getCurrentPlayer().removeCardFromDeckCards(cards);
    }
}
