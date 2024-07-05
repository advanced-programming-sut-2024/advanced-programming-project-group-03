package ir.ap.probending.Model.Card;

import com.badlogic.gdx.graphics.Texture;
import ir.ap.probending.Model.Card.Abilities.*;

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
    Zuko(CardsInfo.Zuko),

    //Earth Benders
    Aiwei(CardsInfo.Aiwei),
    BaatarJr(CardsInfo.BaatarJr),
    BaSingSeCaptain(CardsInfo.BaSingSeCaptain),
    BigBadHippo(CardsInfo.BigBadHippo),
    Bolin(CardsInfo.Bolin),
    Boqin(CardsInfo.Boqin),
    Boulder(CardsInfo.Boulder),
    Bumi(CardsInfo.Bumi),
    CabbageMerchant(CardsInfo.CabbageMerchant),
    CalmMan(CardsInfo.CalmMAn),
    Colossus(CardsInfo.Colossus),
    Jet(CardsInfo.Jet),
    Kenji(CardsInfo.Kenji),
    KiyoshiWarriors(CardsInfo.KiyoshiWarriors),
    Kong(CardsInfo.Kong),
    Kuei(CardsInfo.Kuei),
    Kuji(CardsInfo.Kuji),
    Kuvira(CardsInfo.Kuvira),
    LinBeifong(CardsInfo.LinBeifong),
    LongFeng(CardsInfo.LongFeng),
    OldTophBeifong(CardsInfo.OldTophBeifong),
    OmashuCaptain(CardsInfo.OmashuCaptain),
    OutpostSoldiers(CardsInfo.OutpostSoldiers),
    Suki(CardsInfo.Suki),
    SuyinBeifong(CardsInfo.SuyinBeifong),
    TophBeifong(CardsInfo.TophBeifong),
    Baatar(CardsInfo.Baatar),

    //Air Benders
    AirBoy(CardsInfo.AirBoy),
    BumiAir(CardsInfo.BumiAir),
    Daw(CardsInfo.Daw),
    Gyatso(CardsInfo.Gyatso),
    Iio(CardsInfo.Iio),
    Ikki(CardsInfo.Ikki),
    Jinora(CardsInfo.Jinora),
    Kai(CardsInfo.Kai),
    Kelsang(CardsInfo.Kelsang),
    Koun(CardsInfo.Koun),
    Meelo(CardsInfo.Meelo),
    Opal(CardsInfo.Opal),
    Otak(CardsInfo.Otaku),
    Ryu(CardsInfo.Ryu),
    Tashi(CardsInfo.Tashi),
    Tenzin(CardsInfo.Tenzin),
    Yung(CardsInfo.Yung),
    Zaheer(CardsInfo.Zaheer),
    Yangchen(CardsInfo.Yangchen),
    Pasang(CardsInfo.Pasang),
    Aang(CardsInfo.Aang),
    Afiko(CardsInfo.Afiko),
    AvatarAang(CardsInfo.AvatarAang),
    AvatarKorra(CardsInfo.AvatarKorra),
    AvatarKyoshi(CardsInfo.AvatarKyoshi),
    AvatarRoku(CardsInfo.AvatarRoku),
    Decoy(CardsInfo.Decoy),
    Clear(CardsInfo.Clear),
    Rain(CardsInfo.Rain),
    Fog(CardsInfo.Fog),
    Frost(CardsInfo.Frost),
    Storm(CardsInfo.Storm),
    Bear(CardsInfo.Bear),
    Scorch(CardsInfo.Scorch),
    Mardroeme(CardsInfo.Mardroeme),
    CommandersHorn(CardsInfo.Commander),

    //leader cards
    WaterTribeLeader1(CardsInfo.WaterTribeLeader1),
    WaterTribeLeader2(CardsInfo.WaterTribeLeader2),
    WaterTribeLeader3(CardsInfo.WaterTribeLeader3),
    WaterTribeLeader4(CardsInfo.WaterTribeLeader4),
    WaterTribeLeader5(CardsInfo.WaterTribeLeader5),
    FireNationLeader1(CardsInfo.FireNationLeader1),
    FireNationLeader2(CardsInfo.FireNationLeader2),
    FireNationLeader3(CardsInfo.FireNationLeader3),
    FireNationLeader4(CardsInfo.FireNationLeader4),
    FireNationLeader5(CardsInfo.FireNationLeader5),
    EarthKingdomLeader1(CardsInfo.EarthKingdomLeader1),
    EarthKingdomLeader2(CardsInfo.EarthKingdomLeader2),
    EarthKingdomLeader3(CardsInfo.EarthKingdomLeader3),
    EarthKingdomLeader4(CardsInfo.EarthKingdomLeader4),
    EarthKingdomLeader5(CardsInfo.EarthKingdomLeader5),
    AirNomadsLeader1(CardsInfo.AirTemplesLeader1),
    AirNomadsLeader2(CardsInfo.AirTemplesLeader2),
    AirNomadsLeader3(CardsInfo.AirTemplesLeader3),
    AirNomadsLeader4(CardsInfo.AirTemplesLeader4),
    AirNomadsLeader5(CardsInfo.AirTemplesLeader5),
    AirNomadsLeader6(CardsInfo.AirTemplesLeader6),
    AirNomadsLeader7(CardsInfo.AirTemplesLeader7);

    private Card card;

    CardObjects(CardsInfo cardInfo) {
        this.card = new Card(cardInfo.getAbility(), cardInfo.getName(), cardInfo.getDescription(), cardInfo.getPower(), cardInfo.isHero(), new Texture(cardInfo.getPictureLocation()), cardInfo.getPlayingRow());
    }

    public Card getCard() {
        return card;
    }

    public static ArrayList<Card> getWaterCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(getNeutralCards());
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

    public static ArrayList<Card> getAirCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(getNeutralCards());
        cards.add(AirBoy.getCard().clone());
        cards.add(AirBoy.getCard().clone());
        cards.add(AirBoy.getCard().clone());
        cards.add(BumiAir.getCard().clone());
        cards.add(Daw.getCard().clone());
        cards.add(Gyatso.getCard().clone());
        cards.add(Iio.getCard().clone());
        cards.add(Ikki.getCard().clone());
        cards.add(Jinora.getCard().clone());
        cards.add(Kai.getCard().clone());
        cards.add(Kelsang.getCard().clone());
        cards.add(Kelsang.getCard().clone());
        cards.add(Koun.getCard().clone());
        cards.add(Koun.getCard().clone());
        cards.add(Meelo.getCard().clone());
        cards.add(Opal.getCard().clone());
        cards.add(Otak.getCard().clone());
        cards.add(Ryu.getCard().clone());
        cards.add(Tashi.getCard().clone());
        cards.add(Tenzin.getCard().clone());
        cards.add(Yung.getCard().clone());
        cards.add(Zaheer.getCard().clone());
        cards.add(Yangchen.getCard().clone());
        cards.add(Pasang.getCard().clone());
        cards.add(Aang.getCard().clone());
        cards.add(Afiko.getCard().clone());
        return cards;
    }

    public static ArrayList<Card> getEarthCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(getNeutralCards());
        cards.add(Aiwei.getCard().clone());
        cards.add(BaatarJr.getCard().clone());
        cards.add(BaSingSeCaptain.getCard().clone());
        cards.add(BigBadHippo.getCard().clone());
        cards.add(Bolin.getCard().clone());
        cards.add(Boqin.getCard().clone());
        cards.add(Boulder.getCard().clone());
        cards.add(Bumi.getCard().clone());
        cards.add(CabbageMerchant.getCard().clone());
        cards.add(CalmMan.getCard().clone());
        cards.add(Colossus.getCard().clone());
        cards.add(Jet.getCard().clone());
        cards.add(Kenji.getCard().clone());
        cards.add(KiyoshiWarriors.getCard().clone());
        cards.add(KiyoshiWarriors.getCard().clone());
        cards.add(KiyoshiWarriors.getCard().clone());
        cards.add(Kong.getCard().clone());
        cards.add(Kuei.getCard().clone());
        cards.add(Kuji.getCard().clone());
        cards.add(Kuvira.getCard().clone());
        cards.add(LinBeifong.getCard().clone());
        cards.add(LongFeng.getCard().clone());
        cards.add(OldTophBeifong.getCard().clone());
        cards.add(OmashuCaptain.getCard().clone());
        cards.add(OutpostSoldiers.getCard().clone());
        cards.add(OutpostSoldiers.getCard().clone());
        cards.add(Suki.getCard().clone());
        cards.add(SuyinBeifong.getCard().clone());
        cards.add(TophBeifong.getCard().clone());
        cards.add(Baatar.getCard().clone());
        return cards;
    }

    public static ArrayList<Card> getFireCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(getNeutralCards());
        cards.add(Azula.getCard().clone());
        cards.add(Azulon.getCard().clone());
        cards.add(Buijing.getCard().clone());
        cards.add(BullyGuard.getCard().clone());
        cards.add(BullyGuard.getCard().clone());
        cards.add(ChitSang.getCard().clone());
        cards.add(CircusTrainer.getCard().clone());
        cards.add(CombustionMan.getCard().clone());
        cards.add(Councilwoman.getCard().clone());
        cards.add(Druk.getCard().clone());
        cards.add(FireSages.getCard().clone());
        cards.add(FireSages.getCard().clone());
        cards.add(FireSages.getCard().clone());
        cards.add(Hide.getCard().clone());
        cards.add(Iroh.getCard().clone());
        cards.add(Jee.getCard().clone());
        cards.add(JeongJeong.getCard().clone());
        cards.add(Mako.getCard().clone());
        cards.add(OldZuko.getCard().clone());
        cards.add(Ozai.getCard().clone());
        cards.add(Pli.getCard().clone());
        cards.add(RoyalSoldiers.getCard().clone());
        cards.add(RoyalSoldiers.getCard().clone());
        cards.add(RoyalSoldiers.getCard().clone());
        cards.add(Sozin.getCard().clone());
        cards.add(SunWarriors.getCard().clone());
        cards.add(SunWarriors.getCard().clone());
        cards.add(TaxCollector.getCard().clone());
        cards.add(TwoToedPing.getCard().clone());
        cards.add(Ursa.getCard().clone());
        cards.add(Warden.getCard().clone());
        cards.add(Zhao.getCard().clone());
        cards.add(Zuko.getCard().clone());
        return cards;
    }

    public static ArrayList<Card> getNeutralCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(AvatarAang.getCard().clone());
        cards.add(AvatarKorra.getCard().clone());
        cards.add(AvatarKyoshi.getCard().clone());
        cards.add(AvatarRoku.getCard().clone());
        cards.add(Decoy.getCard().clone());
        cards.add(Clear.getCard().clone());
        cards.add(Rain.getCard().clone());
        cards.add(Fog.getCard().clone());
        cards.add(Frost.getCard().clone());
        cards.add(Storm.getCard().clone());
        cards.add(Scorch.getCard().clone());
        cards.add(Mardroeme.getCard().clone());
        cards.add(CommandersHorn.getCard().clone());
        return cards;
    }

    public static ArrayList<Card> getWaterLeaderCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(WaterTribeLeader1.getCard().clone6());
        cards.add(WaterTribeLeader2.getCard().clone6());
        cards.add(WaterTribeLeader3.getCard().clone6());
        cards.add(WaterTribeLeader4.getCard().clone6());
        cards.add(WaterTribeLeader5.getCard().clone6());
        return cards;
    }

    public static ArrayList<Card> getFireLeaderCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(FireNationLeader1.getCard().clone6());
        cards.add(FireNationLeader2.getCard().clone6());
        cards.add(FireNationLeader3.getCard().clone6());
        cards.add(FireNationLeader4.getCard().clone6());
        cards.add(FireNationLeader5.getCard().clone6());
        return cards;
    }

    public static ArrayList<Card> getEarthLeaderCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(EarthKingdomLeader1.getCard().clone6());
        cards.add(EarthKingdomLeader2.getCard().clone6());
        cards.add(EarthKingdomLeader3.getCard().clone6());
        cards.add(EarthKingdomLeader4.getCard().clone6());
        cards.add(EarthKingdomLeader5.getCard().clone6());
        return cards;
    }

    public static ArrayList<Card> getAirLeaderCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(AirNomadsLeader1.getCard().clone6());
        cards.add(AirNomadsLeader2.getCard().clone6());
        cards.add(AirNomadsLeader3.getCard().clone6());
        cards.add(AirNomadsLeader4.getCard().clone6());
        cards.add(AirNomadsLeader5.getCard().clone6());
        cards.add(AirNomadsLeader6.getCard().clone6());
        cards.add(AirNomadsLeader7.getCard().clone6());
        return cards;
    }

    public static Card getEvilDruk() {
        return EvilDruk.getCard().clone2();
    }

    public static Card getBear() {
        return Bear.getCard().clone2();
    }
}
