package skulmod.util.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.DollysMirror;
import skulmod.character.LittleBone;

public class MirrorPatch {
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "initializeRelicList"
    )
    public static class MirrorBreak
    {
        @SpirePrefixPatch
        public static void PrefixPatch(AbstractDungeon __instance)
        {
            if (CardCrawlGame.chosenCharacter == LittleBone.Enums.Little_Bone)
            {
                ///Temporarily remove Dolly's Mirror while we work on a patch to make our Skulls play nice with it.
                    AbstractDungeon.relicsToRemoveOnStart.add(DollysMirror.ID);
            }
        }
    }
}