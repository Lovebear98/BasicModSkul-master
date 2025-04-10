package skulmod.util;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import skulmod.character.LittleBone;

import static skulmod.SkulMod.SpawnRelic;
import static skulmod.SkulMod.makeID;

public class vars {
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("SkulUI"));
    private static String[] DESCRIPTIONS = uiStrings.TEXT;
    public static String Discard(){
        return DESCRIPTIONS[1];
    }

    public static String Charge(){
        return DESCRIPTIONS[2];
    }

    public static String EnableMusic(){
        return DESCRIPTIONS[3];
    }
    public static String AddSkulls() {
        return DESCRIPTIONS[4];
    }

    public static String AddEvent() {
        return DESCRIPTIONS[5];
    }

    public static String AddRelics() {
        return DESCRIPTIONS[6];
    }

    public static boolean RelicSpawn(){
        if(AbstractDungeon.player != null){
            if(AbstractDungeon.player instanceof LittleBone || AbstractDungeon.player.chosenClass == LittleBone.Enums.Little_Bone){
                return true;
            }
                return SpawnRelic;
        }
        return SpawnRelic;
    }


}
