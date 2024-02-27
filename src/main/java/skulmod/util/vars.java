package skulmod.util;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

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
}
