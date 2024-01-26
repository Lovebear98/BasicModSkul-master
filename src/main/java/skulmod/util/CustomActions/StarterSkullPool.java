package skulmod.util.CustomActions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import skulmod.cards.power.SkullPowers.*;

import java.util.ArrayList;

public class StarterSkullPool {
        ///As Abstract Dungeon isn't initialized yet and this only runs at the start of the run before and RNG seed is set
    ///We just use full random to pick a card out of the list we pass in.
        public static String RandomCardInList(ArrayList<AbstractCard> List){
            AbstractCard RandomSkull = List.get(MathUtils.random(List.size() - 1));
            return RandomSkull.cardID;
        }


        ///Here, we create a list of all valid skulls. This way, it can easily be postfix patched
        ///to include something else before we pull from it
    public static ArrayList<AbstractCard> StarterSkullList(){
        ArrayList<AbstractCard> starterSkull = new ArrayList<>();
        starterSkull.add(new AbsoluteSkull());
        starterSkull.add(new ChampionSkull());
        starterSkull.add(new ClownSkull());
        starterSkull.add(new DarkPaladinSkull());
        starterSkull.add(new DavyJonesSkull());
        starterSkull.add((new FrostSkull()));
        starterSkull.add(new GamblerSkull());
        starterSkull.add((new GodOfWarSkull()));
        starterSkull.add(new GreatWarlockSkull());
        starterSkull.add(new HunterSkull());
        starterSkull.add((new MageSkull()));
        starterSkull.add(new PettyThiefSkull());
        starterSkull.add(new ShieldSkull());
        starterSkull.add(new WarriorSkull());
        starterSkull.add(new WerewolfSkull());
        //One
        // Two
        // Three
        return starterSkull;
    }
}
