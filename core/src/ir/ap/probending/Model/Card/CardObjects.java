package ir.ap.probending.Model.Card;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public enum CardObjects {
    // water benders
    Amon(CardsInfo.Amon),
    DesnaAndEska(CardsInfo.DesnaAndEska),
    Due(CardsInfo.Due),
    Hakoda(CardsInfo.Hakoda),
    Hama(CardsInfo.Hama),
    Hasook(CardsInfo.Hasook),
    Huu(CardsInfo.Huu),
    Kanna(CardsInfo.Kanna),
    Katara(CardsInfo.Katara),
    Kya1(CardsInfo.Kya1),
    Kya2(CardsInfo.Kya2),
    MingHua(CardsInfo.MingHua),
    OldKatara(CardsInfo.OldKatara),
    Pakku(CardsInfo.Pakku),
    Senna(CardsInfo.Senna),
    ShadyShin(CardsInfo.ShadyShin),
    Sokka(CardsInfo.Sokka),
    Taqukaq(CardsInfo.Taqukaq),
    Tarrlok(CardsInfo.Tarrlok),
    Thano(CardsInfo.Thano),
    Tho(CardsInfo.Tho),
    Tonraq(CardsInfo.Tonraq),
    Unalaq(CardsInfo.Unalaq),
    Viper(CardsInfo.Viper),
    WaterKid(CardsInfo.WaterKid),
    WaterSoldiers(CardsInfo.WaterSoldiers),
    Yagoda(CardsInfo.Yagoda),
    Yakone(CardsInfo.Yakone),
    Yue(CardsInfo.Yue),

    // fire benders
    Azula(CardsInfo.Azula),
    Azulon(CardsInfo.Azulon),
    Buijing(CardsInfo.Buijing),
    BullyGuard(CardsInfo.BullyGuard),
    ChitSang(CardsInfo.ChitSang),
    CircusTrainer(CardsInfo.CircusTrainer),
    CombustionMan(CardsInfo.CombustionMan),
    Councilwoman(CardsInfo.Councilwoman),
    Druk(CardsInfo.Druk),
    EvilDruk(CardsInfo.EvilDruk),
    FireSages(CardsInfo.FireSages),
    Hide(CardsInfo.Hide),
    Iroh(CardsInfo.Iroh),
    Jee(CardsInfo.Jee),
    JeongJeong(CardsInfo.JeongJeong),
    Mako(CardsInfo.Mako),
    OldZuko(CardsInfo.OldZuko),
    Ozai(CardsInfo.Ozai),
    Pli(CardsInfo.Pli),
    RoyalSoldiers(CardsInfo.RoyalSoldiers),
    Sozin(CardsInfo.Sozin),
    SunWarriors(CardsInfo.SunWarriors),
    TaxCollector(CardsInfo.TaxCollector),
    TwoToedPing(CardsInfo.TwoToedPing),
    Ursa(CardsInfo.Ursa),
    Warden(CardsInfo.Warden),
    Zhao(CardsInfo.Zhao),
    Zuko(CardsInfo.Zuko);

    private Card card;
    CardObjects(CardsInfo cardInfo){
        this.card = new Card(cardInfo.getAbility() , cardInfo.getName() , cardInfo.getDescription() , cardInfo.getPower() , cardInfo.isHero() , new Texture(cardInfo.getPictureLocation()) , cardInfo.getPlayingRow());
    }
    public Card getCard(){
        return card;
    }
    public static ArrayList<Card> getWaterCards(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(Amon.getCard().clone());
        cards.add(DesnaAndEska.getCard().clone());
        cards.add(DesnaAndEska.getCard().clone());
        cards.add(Due.getCard().clone());
        cards.add(Hakoda.getCard().clone());
        cards.add(Hama.getCard().clone());
        cards.add(Hasook.getCard().clone());
        cards.add(Huu.getCard().clone());
        cards.add(Kanna.getCard().clone());
        cards.add(Katara.getCard().clone());
        cards.add(Kya1.getCard().clone());
        cards.add(Kya2.getCard().clone());
        cards.add(MingHua.getCard().clone());
        cards.add(OldKatara.getCard().clone());
        cards.add(Pakku.getCard().clone());
        cards.add(Senna.getCard().clone());
        cards.add(ShadyShin.getCard().clone());
        cards.add(Sokka.getCard().clone());
        cards.add(Taqukaq.getCard().clone());
        cards.add(Tarrlok.getCard().clone());
        cards.add(Thano.getCard().clone());
        cards.add(Tho.getCard().clone());
        cards.add(Tonraq.getCard().clone());
        cards.add(Unalaq.getCard().clone());
        cards.add(Viper.getCard().clone());
        cards.add(WaterKid.getCard().clone());
        cards.add(WaterKid.getCard().clone());
        cards.add(WaterSoldiers.getCard().clone());
        cards.add(WaterSoldiers.getCard().clone());
        cards.add(WaterSoldiers.getCard().clone());
        cards.add(Yagoda.getCard().clone());
        cards.add(Yakone.getCard().clone());
        cards.add(Yue.getCard().clone());
        return cards;
    }
}
