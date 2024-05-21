package ir.ap.probending.Model.Card;

import ir.ap.probending.Model.Card.Abilities.*;

public enum CardsInfo {
    //WaterBenders
    Amon(new Scorch(), "Amon", 8, false, "Cards/Water Tribes/Amon.png", 2 , "The era of bending is over! A new era of equality has begun!"),
    DesnaAndEska(new TightBonds() , "Desna And Eska" , 3 , false , "Cards/Water Tribes/Desna and Eska.png" , 1 , "But i will not miss him at all. In the end , he became a deplorable man."),
    Due(new Mardroeme() , "Due" , 2 , false , "Cards/Water Tribes/Due.png" , 2 , "You too ! That means we're kin!"),
    Hakoda(new Berseker() , "Hakoda" , 4 , false , "Cards/Water Tribes/Hakoda.png" , 2 , "every night when i went to sleep , i would lie awake missing you so much"),
    Hama(new Spy() , "Hama" , 5 , false , "Cards/Water Tribes/Hama.png", 0 , "They threw me in prison to rot, along with my brothers and sisters! They deserve the same! You must carry on the work."),
    Hasook(null , "Hasook" , 4 , false , "Cards/Water Tribes/Hassok.png" , 1 , "Get off my case pal!"),
    Huu(new Morale() , "Huu" , 2 , false , "Cards/Water Tribes/Huu" , 2 , "We all have the same roots, and we are all branches of the same tree."),
    Kanna(null , "Kanna" , 1 , false , "Cards/Water Tribes/Kanna.png" , 1 , "It's been so long since I've had hope, but you brought it back to life."),
    Katara(new Medic() , "Katara" , 10 , true , "Cards/Water Tribes/Katara.png" , 2 , "I will never , ever turn my back on people who need me!"),
    Kya1(null , "Kya" , 2 , false , "Cards/Water Tribes/Kya Kataras Mom.png" , 0 , "Just let her go , and i'll give you the information you want."),
    Kya2(new Agile() , "Kya" , 6 , false , "Cards/Water Tribes/Kya.png" , 0 , "You thing you're the responsible one? Where were you after dad died and mom was all alone?"),
    MingHua(null , "Ming Hua" , 7 , false , "Cards/Water Tribes/Ming Hua.png" , 1 , "We could have just taken the elevator. Show-off."),
    OldKatara(new Medic() , "Old Katara" , 5 , false , "Cards/Water Tribes/Old Katara.png" , 0 , "I will , turn my back on people who need me!"),
    Pakku(new Agile() , "Pakku" , 9 , false , "Cards/Water Tribes/Pakku.png" , 2 , "It is respectful to bow to an old master , but how about a hug for your new grandfather?"),
    Senna(null , "Senna" , 2  , false , "Cards/Water Tribes/Senna.png" , 0, "We knew one day the world would need you , and you wouldn't need us anymore."),
    ShadyShin(null , "Shady Shin" , 2 , false , "Cards/Water Tribes/Shady Shin.png" , 2 , "Seeing those young crooks really gives me hope for the future."),
    Sokka(new Morale() , "Sokka" , 10 , true , "Cards/Water Tribes/Sokka.png" , 2 , "That's all i got. it's pretty much my whole identity. Sokka , the meat and sarcasm guy"),
    Taqukaq(null , "Taqukaq" , 7 , false , "Cards/Water Tribes/Taqukaq.png" , 0 , "Kindness is the path to understanding."),
    Tarrlok(new Spy() , "Tarrlok" , 6 , false , "Cards/Water Tribes/Tarrlok.png" , 2 ,"That's what i admire about you , Korra. Your Willingness  to go extremes in order to get what you want. it is a quality we both share."),
    Thano(new Agile() , "Thano" , 4 , false ,"Cards/Water Tribes/Thano.png" , 1 , "You boys smell something in here? Wait , I know what that is; yeah, that's the scent of losers"),
    Tho(new Mardroeme() , "Tho" , 2 , false , "Cards/Water Tribes/Tho.png" , 0 , "Didn't know there was waterbenders anywhere but here. They got a nice swamp there , do they?"),
    Tonraq(new Morale() , "Tonraq" , 6 , false , "Cards/Water Tribes/Tonraq.png" , 1 , "I was protecting you from the shame i brought on the family."),
    Unalaq(new Berseker() , "Unalaq" , 5 , false , "Cards/Water Tribes/Unalaq.png" , 0 , "You call yourself the bridge between the two worlds , but there shouldn't be a bridge ; we should live together as one."),
    Viper(null , "Viper" , 3 , false , "Cards/Water Tribes/Viper.png" , 0 , "Since you're obviously fresh of the boat , let me explain a couple things."),
    WaterKid(new Muster() , "Water Kid" , 3 , false , "Cards/Water Tribes/Water Kid.png" , 1 , "A couple of more years and you might be ready to fight a sea sponge"),
    WaterSoldiers(new Muster() , "Water Soldiers" , 2 , false , "Cards/Water Tribes/Water Soldiers.png" , 2 , "Onga Bonga"),
    Yagoda(new Medic() , "Yagoda" , 5 , false , "Cards/Water Tribes/Yagoda.png" , 1 , "Are you here for the healing lesson?"),
    Yakone(new Scorch() , "Yakone" , 7 , false , "Cards/Water Tribes/Yakone.png" , 2 , "Republic City is mine, Avatar. I'll be back one day to claim it."),
    Yue(new Morale() , "Yue" , 9 , false , "Cards/Water Tribes/Yue.png" , 0 , "It gave me life. Maybe i can give it back.");

    //Fire benders


    private Ability ability;
    String name;
    String description;
    int Power;
    boolean isHero;
    String pictureLocation;
    int playingRow;
    CardsInfo(Ability ability, String name, int power, boolean isHero, String pictureLocation, int playingRow , String description) {
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
