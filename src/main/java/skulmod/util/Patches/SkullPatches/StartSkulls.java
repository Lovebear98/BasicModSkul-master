package skulmod.util.Patches.SkullPatches;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import skulmod.CustomTags;
import skulmod.cards.power.SkullPowers.*;
import skulmod.character.LittleBone;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static skulmod.SkulMod.SkullsSpawn;
import static skulmod.SkulMod.logger;

public class StartSkulls {
    @SpirePatch(clz = AbstractPlayer.class, method = "initializeStarterDeck")
    public static class StarterSkullsPatch {
        @SpirePostfixPatch
        public static void patch() {
            if((CardCrawlGame.chosenCharacter != LittleBone.Enums.Little_Bone) && SkullsSpawn){
                ArrayList<AbstractCard> starterSkull = new ArrayList<>();
                starterSkull.add(new ChampionSkull());
                starterSkull.add(new ClownSkull());
                starterSkull.add(new DavyJonesSkull());
                starterSkull.add(new GamblerSkull());
                starterSkull.add(new HunterSkull());
                starterSkull.add(new PettyThiefSkull());
                starterSkull.add(new ShieldSkull());
                starterSkull.add(new WarriorSkull());
                starterSkull.add(new DarkPaladinSkull());
                starterSkull.add(new GreatWarlockSkull());
                starterSkull.add((new FrostSkull()));
                starterSkull.add((new MageSkull()));
                AbstractCard RandomSkull = starterSkull.get(MathUtils.random(starterSkull.size() - 1));
                player.masterDeck.addToBottom(RandomSkull);
                player.masterDeck.addToBottom(new LittleBoneSkull());
            }
        }
    }






    public static void modifyCardPool() {
        logger.info("Adding Skulls to character's card pool.");
        ArrayList<AbstractCard> classCards = CardLibrary.getCardList(LittleBone.Enums.LIBRARY_COLOR);
        for(AbstractCard c : classCards){
            if(c.hasTag(CustomTags.Skull)){
                switch (c.rarity){
                    case COMMON:
                            commonCardPool.addToTop(c);
                    case UNCOMMON:
                            uncommonCardPool.addToTop(c);
                    case RARE:
                            rareCardPool.addToTop(c);
                    case SPECIAL:
                            continue;

                }
            }
        }
    }
}
