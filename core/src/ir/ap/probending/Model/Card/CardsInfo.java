package ir.ap.probending.Model.Card;

import ir.ap.probending.Model.Card.Abilities.*;
import ir.ap.probending.Model.Card.Abilities.LeaderAbilities.*;

public enum CardsInfo {
    //WaterBenders
    Amon(new Scorch(), "Amon", 8, false, "Cards/Water Tribes/Amon.png", 2, "The era of bending is over! A new era of equality has begun!"),
    DesnaAndEska(new TightBonds(), "Desna And Eska", 3, false, "Cards/Water Tribes/Desna and Eska.png", 1, "But i will not miss him at all. In the end , he became a deplorable man."),
    Due(new Mardroeme(), "Due", 2, false, "Cards/Water Tribes/Due.png", 2, "You too ! That means we're kin!"),
    Hakoda(new Berseker(), "Hakoda", 4, false, "Cards/Water Tribes/Hakoda.png", 2, "every night when i went to sleep , i would lie awake missing you so much"),
    Hama(new Spy(), "Hama", 5, false, "Cards/Water Tribes/Hama.png", 0, "They threw me in prison to rot, along with my brothers and sisters! They deserve the same! You must carry on the work."),
    Hasook(null, "Hasook", 4, false, "Cards/Water Tribes/Hasook.png", 1, "Get off my case pal!"),
    Huu(new Morale(), "Huu", 2, false, "Cards/Water Tribes/Huu.png", 2, "We all have the same roots, and we are all branches of the same tree."),
    Kanna(null, "Kanna", 1, false, "Cards/Water Tribes/Kanna.png", 1, "It's been so long since I've had hope, but you brought it back to life."),
    Katara(new Medic(), "Katara", 10, true, "Cards/Water Tribes/Katara.png", 2, "I will never , ever turn my back on people who need me!"),
    Kya1(null, "Kya ", 2, false, "Cards/Water Tribes/Kya Kataras Mom.png", 0, "Just let her go , and i'll give you the information you want."),
    Kya2(new Agile2(), "Kya", 6, false, "Cards/Water Tribes/Kya.png", 0, "You thing you're the responsible one? Where were you after dad died and mom was all alone?"),
    MingHua(null, "Ming Hua", 7, false, "Cards/Water Tribes/Ming Hua.png", 1, "We could have just taken the elevator. Show-off."),
    OldKatara(new Medic(), "Old Katara", 5, false, "Cards/Water Tribes/Old Katara.png", 0, "I will , turn my back on people who need me!"),
    Pakku(new Agile(), "Pakku", 9, false, "Cards/Water Tribes/Pakku.png", 2, "It is respectful to bow to an old master , but how about a hug for your new grandfather?"),
    Senna(null, "Senna", 2, false, "Cards/Water Tribes/Senna.png", 0, "We knew one day the world would need you , and you wouldn't need us anymore."),
    ShadyShin(null, "Shady Shin", 2, false, "Cards/Water Tribes/Shady Shin.png", 2, "Seeing those young crooks really gives me hope for the future."),
    Sokka(new Morale(), "Sokka", 10, true, "Cards/Water Tribes/Sokka.png", 2, "That's all i got. it's pretty much my whole identity. Sokka , the meat and sarcasm guy"),
    Taqukaq(null, "Taqukaq", 7, false, "Cards/Water Tribes/Taqukaq.png", 0, "Kindness is the path to understanding."),
    Tarrlok(new Spy(), "Tarrlok", 6, false, "Cards/Water Tribes/Tarrlok.png", 2, "That's what i admire about you , Korra. Your Willingness  to go extremes in order to get what you want. it is a quality we both share."),
    Thano(new Agile(), "Thano", 4, false, "Cards/Water Tribes/Thano.png", 1, "You boys smell something in here? Wait , I know what that is; yeah, that's the scent of losers"),
    Tho(new Mardroeme(), "Tho", 2, false, "Cards/Water Tribes/Tho.png", 0, "Didn't know there was waterbenders anywhere but here. They got a nice swamp there , do they?"),
    Tonraq(new Morale(), "Tonraq", 6, false, "Cards/Water Tribes/Tonraq.png", 1, "I was protecting you from the shame i brought on the family."),
    Unalaq(new Berseker(), "Unalaq", 5, false, "Cards/Water Tribes/Unalaq.png", 0, "You call yourself the bridge between the two worlds , but there shouldn't be a bridge ; we should live together as one."),
    Viper(null, "Viper", 3, false, "Cards/Water Tribes/Viper.png", 0, "Since you're obviously fresh of the boat , let me explain a couple things."),
    WaterKid(new Muster(), "Water Kid", 3, false, "Cards/Water Tribes/Water Kid.png", 1, "A couple of more years and you might be ready to fight a sea sponge"),
    WaterSoldiers(new Muster(), "Water Soldiers", 2, false, "Cards/Water Tribes/Water Soldiers.png", 2, "Onga Bonga"),
    Yagoda(new Medic(), "Yagoda", 5, false, "Cards/Water Tribes/Yagoda.png", 1, "Are you here for the healing lesson?"),
    Yakone(new Scorch(), "Yakone", 7, false, "Cards/Water Tribes/Yakone.png", 2, "Republic City is mine, Avatar. I'll be back one day to claim it."),
    Yue(new Morale(), "Yue", 9, false, "Cards/Water Tribes/Yue.png", 0, "It gave me life. Maybe i can give it back."),

    //Fire benders
    Azula(null, "Azula", 9, false, "Cards/Fire Nation/Azula.png", 2, "Well what choice do I have? Trust is for fools! Fear is the only reliable way. Even you fear me."),
    Azulon(null, "Azulon", 4, false, "Cards/Fire Nation/Azulon.png", 2, "your punishment has scarcely begun!"),
    Buijing(new Morale(), "Buijing", 2, false, "Cards/Fire Nation/Bujing.png", 1, "What better to use as bait than fresh meat?"),
    BullyGuard(new TightBonds(), "Bully Guard", 2, false, "Cards/Fire Nation/Bully Guard.png", 1, "He wants to know what he did. Isn't that cute?"),
    ChitSang(new Spy(), "Chit Sang", 6, false, "Cards/Fire Nation/Chit Sang.png", 1, "You're lucky I didn't rat you out, but my generosity comes with a price"),
    CircusTrainer(new Spy(), "Circus Trainer", 1, false, "Cards/Fire Nation/Circus Trainer.png", 2, "But don't worry, you won't anymore ... Because I am going to break you."),
    CombustionMan(new Scorch(), "Combustion Man", 6, false, "Cards/Fire Nation/Combustion Man.png", 0, "Sparky Sparky Boom Man"),
    Councilwoman(new Medic(), "Councilwoman", 3, false, "Cards/Fire Nation/Councilwoman.png", 0, "Don't wait up for me; this council session will probably go late into the night."),
    Druk(new Berseker(), "Druk", 2, false, "Cards/Fire Nation/Druk.png", 0, "Grrrrrr."),
    EvilDruk(null, "Evil Druk", 6, false, "Cards/Fire Nation/Evil Druk.png", 0, "very Grrrrrr."),
    FireSages(new Muster(), "Fire Sages", 2, false, "Cards/Fire Nation/Fire Sages.png", 1, "We are the Fire Sages, gaurdians of the temple of the Avatar."),
    Hide(new Agile(), "Hide", 2, false, "Cards/Fire Nation/Hide.png", 1, "on Ji. Is. My. Girlfriend. Don't forget it"),
    Iroh(new Medic(), "Iroh", 8, false, "Cards/Fire Nation/Iroh.png", 1, "After I reconquer Ba Sing Se, I'm going to reconquer my tea shop, and I'm going to play Pai Sho every day!"),
    Jee(new Agile2(), "Jee", 3, false, "Cards/Fire Nation/jee.png", 1, "Then again, what should I expect from a spoiled prince?"),
    JeongJeong(new Spy(), "Jeong Jeong", 7, false, "Cards/Fire Nation/Jeong Jeong.png", 2, "Fire brings only destruction and pain. It forces those of us burdened with its care to walk a razor's edge between humanity and savagery. Eventually, we are torn apart."),
    Mako(new Morale(), "Mako", 5, false, "Cards/Fire Nation/Mako.png", 2, "I did what I had to do to survive and protect my little brother."),
    OldZuko(null, "Old Zuko", 10, true, "Cards/Fire Nation/Old Zuko.png", 0, "No one knew better than aang that in times of turmoil the world needs it's avatar the most"),
    Ozai(null, "Ozai", 9, false, "Cards/Fire Nation/Ozai.png", 2, "You are the Fire Lord. What you choose, by definition, is right."),
    Pli(new Scorch(), "P'Li", 5, false, "Cards/Fire Nation/Pli.png", 1, "Years apart only made my love for you stronger."),
    RoyalSoldiers(new TightBonds(), "Royal Soldiers", 3, false, "Cards/Fire Nation/Royal Soldiers.png", 1, ""),
    Sozin(null, "Sozin", 8, false, "Cards/Fire Nation/Sozin.png", 2, "I know he's hiding out there somewhere. The Fire Nation's greatest threat ... the last airbender."),
    SunWarriors(new TightBonds(), "Sun Warriors", 2, false, "Cards/Fire Nation/Sun Warriors.png", 1, "Fire is life, not just destruction"),
    TaxCollector(new Medic(), "Tax Collector", 2, false, "Cards/Fire Nation/Tax Collector.png", 0, "Fire is sometimes so hard to control."),
    TwoToedPing(new Spy(), "Two Toed Ping", 2, false, "Cards/Fire Nation/Two Toed Ping.png", 2, "I mean, I am a gangster, after all, and ..."),
    Ursa(new Medic(), "Ursa", 4, false, "Cards/Fire Nation/Ursa.png", 0, "Everything I've done, I've dont to protect you. Remember this, Zuko. No matter how things may seem to change, never forget who you are."),
    Warden(null, "Warden", 4, false, "Cards/Fire Nation/Warden.png", 1, "Take him below! One week in solitary will improve his manners."),
    Zhao(new Morale(), "Zhao", 4, false, "Cards/Fire Nation/Zhao.png", 1, "I am a legend now! The Fire Nation will for generations tell stories about the great Zhao who darkened the moon!"),
    Zuko(null, "Zuko", 8, true, "Cards/Fire Nation/Zuko.png", 2, " the mark of the banished prince, cursed to chase the Avatar forever."),

    //Earth benders
    Baatar(new CommandersHorn(), "Baatar",3,false,"Cards/Earth Kingdom/Baatar.png",0,""),
    BaatarJr(new Spy(),"Baatar Jr",5,false,"Cards/Earth Kingdom/BaatarJr.png",0,""),
    BaSingSeCaptain(null,"Ba Sing Se Captain",4,false,"Cards/Earth Kingdom/BaSingSeCaptain.png",1,""),
    BigBadHippo(null,"Big Bad Hippo",3,false,"Cards/Earth Kingdom/BigBadHippo.png",2,""),
    Bolin(new Agile2(),"Bolin",6,false,"Cards/Earth Kingdom/Bolin.png",1,""),
    Boqin(null,"Boqin",3,false,"Cards/Earth Kingdom/Boqin.png",1,""),
    Boulder(null,"Boulder",3,false,"Cards/Earth Kingdom/Boulder.png",2,""),
    Bumi(new Scorch(),"Bumi",9,false,"Cards/Earth Kingdom/Bumi.png",2,""),
    CabbageMerchant(null,"Cabbage merchant",2,false,"Cards/Earth Kingdom/CabbageMerchant.png",1,""),
    CalmMAn(null,"Calm Man",1,false,"Cards/Earth Kingdom/CalmMan.png",1,""),
    Colossus(null,"Colossus",8,false,"Cards/Earth Kingdom/Colossus.png",0,""),
    Jet(new Agile(),"Jet",4,false,"Cards/Earth Kingdom/Jet.png",2,""),
    Kenji(null,"Kenji",2,false,"Cards/Earth Kingdom/Kenji.png",2,""),
    KiyoshiWarriors(new Muster(),"Kiyoshi Warriors",3,false,"Cards/Earth Kingdom/KiyoshiWarriors.png",2,""),
    Kong(null,"Kong",2,false,"Cards/Earth Kingdom/Kong.png",0,""),
    Kuei(new CommandersHorn(),"Kuei",1,false,"Cards/Earth Kingdom/Kuei.png",0,""),
    Kuji(null,"Kuji",2,false,"Cards/Earth Kingdom/Kuji.png",1,""),
    Kuvira(null,"Kuvira",9,false,"Cards/Earth Kingdom/Kuvira.png",2,""),
    LinBeifong(null,"Lin Beifong",9,false,"Cards/Earth Kingdom/LinBeifong.png",2,""),
    LongFeng(new Spy(),"Long Feng",3,false,"Cards/Earth Kingdom/LongFeng.png",2,""),
    OldTophBeifong(new Medic(),"Old Toph Beifong",6,false,"Cards/Earth Kingdom/OldTophBeifong.png",2,""),
    OmashuCaptain(null,"Omashu Captain",3,false,"Cards/Earth Kingdom/OmashuCaptain.png",1,""),
    OutpostSoldiers(new TightBonds(),"Outpost Soldiers",2,false,"Cards/Earth Kingdom/OutpostSoldiers.png",1,""),
    Suki(null,"Suki",7,false,"Cards/Earth Kingdom/Suki.png",2,""),
    SuyinBeifong(new Morale(),"Suyin Beifong",6,false,"Cards/Earth Kingdom/SuyinBeifong.png",1,""),
    TophBeifong(null,"Toph Beifong",10,true,"Cards/Earth Kingdom/TophBeifong.png",2,""),
    Aiwei(new Spy(), "Aiwei",2,false,"Cards/Earth Kingdom/Aiwei.png",1,""),

    //Air Benders
    Afiko(new Spy(),"Afico",5,false,"Cards/Air Temples/Afiko.png",2,""),
    AirBoy(new Muster(),"Air Boy",2,false,"Cards/Air Temples/AirBoy.png",2,""),
    BumiAir(new Morale(),"Bumi ",9,false,"Cards/Air Temples/Bumi.png",2,""),
    Daw (new Medic(),"Daw",3,false,"Cards/Air Temples/Daw.png",1,""),
    Gyatso(new Medic(),"Gyatso",5,false,"Cards/Air Temples/Gyatso.png",1,""),
    Iio(new Morale(),"Iio",6,false,"Cards/Air Temples/Iio.png",0,""),
    Ikki(new Mardroeme(),"Ikki",1,false,"Cards/Air Temples/Ikki.png",2,""),
    Jinora(new Morale(),"Jinora",7,false,"Cards/Air Temples/Jinora.png",0,""),
    Kai(new Berseker(),"Kai",3,false,"Cards/Air Temples/Kai.png",2,""),
    Kelsang(new TightBonds(),"Kelsang",5,false,"Cards/Air Temples/Kelsang.png",0,""),
    Koun(new TightBonds(),"Koun",4,false,"Cards/Air Temples/Koun.png",1,""),
    Meelo(new CommandersHorn(),"Meelo",4,false,"Cards/Air Temples/Meelo.png",7,""),
    Opal(new Morale(),"Opal",2,false,"Cards/Air Temples/Opal.png",0,""),
    Otaku(new Morale(),"Otaku",3,false,"Cards/Air Temples/Otaku.png",0,""),
    Ryu(new Spy(),"Ryu",5,false,"Cards/Air Temples/Ryu.png",2,""),
    Tashi(new Medic(),"Tashi",8,false,"Cards/Air Temples/Tashi.png",0,""),
    Tenzin(new Morale(),"Tenzin",8,false,"Cards/Air Temples/Tenzin.png",1,""),
    Yung(new Scorch(),"Yung",6,false,"Cards/Air Temples/Yung.png",1,""),
    Zaheer(new Scorch(),"Zaheer",9,false,"Cards/Air Temples/Zaheer.png",1,""),
    Yangchen(null,"Yangchen",8,false,"Cards/Air Temples/Yangchen.png",1,""),
    Pasang(null,"Pasang",8,false, "Cards/Air Temples/Pasang.png",0,""),
    Aang(null,"Aang",10,true,"Cards/Air Temples/Aang.png",2,""),

    //Neutral
    AvatarAang(null,"Avatar Aang",15,true,"Cards/Neutral/Aang.png",2,""),
    AvatarKorra(null,"Avatar Korra",15,true,"Cards/Neutral/Korra.png",2,""),
    AvatarRoku(null,"Avatar Roku",15,true,"Cards/Neutral/Roku.png",2,""),
    AvatarKyoshi(null,"Avatar Kyoshi",15,true,"Cards/Neutral/Kyoshi.png",2,""),

    Decoy(new Decoy(),"Decoy",0,false,"Cards/Neutral/Decoy.png",2,""),

    //Weather
    Clear(new Clear(),"Clear",0,false,"Cards/Neutral/weather_clear.png",6,""),
    Fog(new Fog(),"Fog",0,false,"Cards/Neutral/weather_fog.png",6,""),
    Frost(new Frost(),"Frost",0,false,"Cards/Neutral/weather_frost.png",6,""),
    Rain(new Rain(),"Rain",0,false,"Cards/Neutral/weather_rain.png",6,""),
    Storm(new Storm(),"Storm",0,false,"Cards/Neutral/weather_storm.png",6,""),

    //leaders
    WaterTribeLeader1(new LeaderAbility1(),"Water1",0,false,"Cards/Leaders/WaterLeader.png",100,"Pick a fog card from your deck and plays it"),
    WaterTribeLeader2(new LeaderAbility2(),"Water2",0,false,"Cards/Leaders/WaterLeader.png",100,"Clears Any weather effect"),
    WaterTribeLeader3(new LeaderAbility3(),"Water3",0,false,"Cards/Leaders/WaterLeader.png",100,"Double the power of siege units (unless commander is there)"),
    WaterTribeLeader4(new LeaderAbility4(),"Water4",0,false,"Cards/Leaders/WaterLeader.png",100,"Destroy enemies strongest siege if sum of siege is more than 10"),
    WaterTribeLeader5(new LeaderAbility5(),"Water5",0,false,"Cards/Leaders/WaterLeader.png",100,"Destroy enemies strongest ranged if sum of ranged is more than 10"),
    FireNationLeader1(new LeaderAbility6(),"Fire1",0,false,"Cards/Leaders/FireLeader.png",100,"Double the power of close combat units (unless commander is there)"),
    FireNationLeader2(new LeaderAbility7(),"Fire2",0,false,"Cards/Leaders/FireLeader.png",100,"Restore a card from discard pile to your hand"),
    FireNationLeader3(new LeaderAbility8(),"Fire3",0,false,"Cards/Leaders/FireLeader.png",100,"Discard 2 card and and draw 1 card from deck by choice"),
    FireNationLeader4(new LeaderAbility9(),"Fire4",0,false,"Cards/Leaders/FireLeader.png",100,"Plays a weather card from your deck"),
    FireNationLeader5(new LeaderAbility10(),"Fire5",0,false,"Cards/Leaders/FireLeader.png",100,"Doubles the power of all spy's"),
    EarthKingdomLeader1(new LeaderAbility11(),"Earth1",0,false,"Cards/Leaders/EarthLeader.png",100,"Destroy enemies strongest close combat if sum of ranged is more than 10"),
    EarthKingdomLeader2(new LeaderAbility12(),"Earth2",0,false,"Cards/Leaders/EarthLeader.png",100,"Double the power of ranged units (unless commander is there)"),
    EarthKingdomLeader3(new LeaderAbility13(),"Earth3",0,false,"Cards/Leaders/EarthLeader.png",100,"Draw and extra card"),
    EarthKingdomLeader4(new LeaderAbility14(),"Earth4",0,false,"Cards/Leaders/EarthLeader.png",100,"Pick a frost card from deck and play it instantly"),
    EarthKingdomLeader5(new LeaderAbility15(),"Earth5",0,false,"Cards/Leaders/EarthLeader.png",100,"Moves agile units to the best row"),
    AirTemplesLeader1(new LeaderAbility16(),"Air1",0,false,"Cards/Leaders/WindLeader.png",100,"Pick a rain card from deck and play it instantly"),
    AirTemplesLeader2(new LeaderAbility17(),"Air2",0,false,"Cards/Leaders/WindLeader.png",100,"Look at 3 random cards from you opponent hand"),
    AirTemplesLeader3(new LeaderAbility18(),"Air3",0,false,"Cards/Leaders/WindLeader.png",100,"Cancel your opponents leader ability"),
    AirTemplesLeader4(new LeaderAbility19(),"Air4",0,false,"Cards/Leaders/WindLeader.png",100,"Draw a card from your opponents discard pile"),
    AirTemplesLeader5(new LeaderAbility20(),"Air5",0,false,"Cards/Leaders/WindLeader.png",100,"Restore Abilities Restores Randomly"),
    AirTemplesLeader6(new LeaderAbility21(),"Air6",0,false,"Cards/Leaders/WindLeader.png",100,"shuffle cards from burnt cards to deck"),
    AirTemplesLeader7(new LeaderAbility22(),"Air7",0,false,"Cards/Leaders/WindLeader.png",100,"Units only lose half the strength in bad weather condition");


    private Ability ability;
    String name;
    String description;
    int Power;
    boolean isHero;
    String pictureLocation;
    int playingRow;

    CardsInfo(Ability ability, String name, int power, boolean isHero, String pictureLocation, int playingRow, String description) {
        this.ability = ability;
        this.name = name;
        this.description = description;
        Power = power;
        this.isHero = isHero;
        this.pictureLocation = pictureLocation;
        this.playingRow = playingRow;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPower() {
        return Power;
    }

    public void setPower(int power) {
        Power = power;
    }

    public boolean isHero() {
        return isHero;
    }

    public void setHero(boolean hero) {
        isHero = hero;
    }

    public String getPictureLocation() {
        return pictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        this.pictureLocation = pictureLocation;
    }

    public int getPlayingRow() {
        return playingRow;
    }

    public void setPlayingRow(int playingRow) {
        this.playingRow = playingRow;
    }
}
