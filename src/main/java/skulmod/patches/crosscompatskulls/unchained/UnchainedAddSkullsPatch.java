package skulmod.patches.crosscompatskulls.unchained;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import skulmod.util.CustomActions.StarterSkullPool;
import skulmod.util.othermodskulls.unchained.cards.RiderSkull;

import java.util.ArrayList;

public class UnchainedAddSkullsPatch {
    @SpirePatch(
            clz = StarterSkullPool.class,
            method = "StarterSkullList",
            requiredModId = "TheUnchainedMod",
            optional = true
    )
    public static class NewSkulls
    {
        @SpirePostfixPatch
        public static ArrayList<AbstractCard> Postfix(ArrayList<AbstractCard> __result)
        {
            __result.add(new RiderSkull());
            return __result;
        }
    }
}


