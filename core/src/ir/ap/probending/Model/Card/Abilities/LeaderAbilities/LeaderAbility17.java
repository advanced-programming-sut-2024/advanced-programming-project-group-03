package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.Model.Game.Game;

import java.util.ArrayList;

public class LeaderAbility17 extends Ability {
    @Override
    public void executeAbility(Card card) {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(Game.getGame().getOtherPlayer().getHand().get(0));
        cards.add(Game.getGame().getOtherPlayer().getHand().get(1));
        cards.add(Game.getGame().getOtherPlayer().getHand().get(2));

        GameUIController.getGameUIController().activateCardListWindow();
        GameUIController.getGameUIController().clearCardListWindow();
        GameUIController.getGameUIController().addCardsToCardListWindow(cards);
        TextButton closeButton = new TextButton("Close", GameAssetManager.getGameAssetManager().getSkin());
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                GameUIController.getGameUIController().deactivateCardListWindow();
                GameUIController.getGameUIController().clearCardListWindow();
                closeButton.remove();
            }
        });
        GameUIController.getGameUIController().getCardListWindow().row();
        GameUIController.getGameUIController().getCardListWindow().add(closeButton);
        Game.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
