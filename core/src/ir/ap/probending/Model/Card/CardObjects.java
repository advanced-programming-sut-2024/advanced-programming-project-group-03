package ir.ap.probending.Model.Card;

import com.badlogic.gdx.graphics.Texture;

public enum CardObjects {
    //WaterBenders
    Amon(CardsInfo.Amon),
    DesnaAndEska(CardsInfo.DesnaAndEska),
    Due(CardsInfo.Due),
    Hakoda(CardsInfo.Hakoda),
    Hama(CardsInfo.Hama);

    private Card card;
    CardObjects(CardsInfo cardInfo){
        this.card = new Card(cardInfo.getAbility() , cardInfo.getName() , cardInfo.getDescription() , cardInfo.getPower() , cardInfo.isHero() , new Texture(cardInfo.getPictureLocation()) , cardInfo.getPlayingRow());
    }
    public Card getCard(){
        return card;
    }
}
