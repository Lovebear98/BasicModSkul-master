package skulmod.patches.crosscompatskulls.unchained;


import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.util.CustomActions.SkullActions.SwapAction;
import skulmod.util.othermodskulls.unchained.cards.RideOrDie;
import skulmod.util.othermodskulls.unchained.cards.RiderSkull;

import static skulmod.SkulMod.hasUnchained;

public class UnchainedSwaps {

    @SpirePatch(
            clz= SwapAction.class,
            method="ExternalSwaps",
            requiredModId = "TheUnchainedMod",
            optional = true
    )
    public static class MoreSwaps {

        @SpireInsertPatch(
                rloc = 0,
                localvars = {"c"}
        )
        public static void NewSwaps(AbstractCard c){
            AbstractPlayer p = AbstractDungeon.player;
            if(hasUnchained){
                ///RIDER SWAP
                if(c.cardID.equals(RiderSkull.ID)){
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new RideOrDie()));
                }
            }
        }
    }
}
