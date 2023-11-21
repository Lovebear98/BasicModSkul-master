package skulmod.util.Patches.SkullPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import skulmod.CustomTags;
import skulmod.character.LittleBone;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static skulmod.SkulMod.SkullsSpawn;
import static skulmod.SkulMod.logger;

public class AddSkulls {
    @SpirePatch(clz = AbstractDungeon.class, method = "initializeCardPools")
    public static class AddSkullsPatch {
        @SpirePostfixPatch
        public static void patch() {
            if((CardCrawlGame.chosenCharacter != LittleBone.Enums.Little_Bone) && SkullsSpawn){
                modifyCardPool();
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
