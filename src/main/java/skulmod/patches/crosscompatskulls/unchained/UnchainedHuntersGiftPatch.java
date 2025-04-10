package skulmod.patches.crosscompatskulls.unchained;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import skulmod.relics.HuntersGift;
import skulmod.util.othermodskulls.unchained.cards.RiderSkull;

public class UnchainedHuntersGiftPatch {
    @SpirePatch(
            clz= HuntersGift.class,
            method="onEquip",
            requiredModId = "TheUnchainedMod",
            optional = true
    )
    public static class HunterSkulls {

        @SpireInsertPatch(
                loc = 66
        )
 public static void HunterSkulls(AbstractRelic __instance, CardGroup ___SkullList){
            ___SkullList.addToBottom(new RiderSkull());
            ___SkullList.sortAlphabetically(true);
        }

    }
}
