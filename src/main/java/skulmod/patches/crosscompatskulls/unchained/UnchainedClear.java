package skulmod.patches.crosscompatskulls.unchained;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.util.CustomActions.SkullActions.ClearSkullPowers;
import skulmod.util.othermodskulls.unchained.powers.RiderPower;

public class UnchainedClear {
    @SpirePatch(
            clz= ClearSkullPowers.class,
            method="ExternalClears",
            requiredModId = "TheUnchainedMod",
            optional = true
    )
    public static class MoreBonepiles {

        @SpirePrefixPatch
 public static void NewClears(){
            AbstractPlayer p = AbstractDungeon.player;
            if(p.hasPower(RiderPower.POWER_ID)){
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p,p, RiderPower.POWER_ID));
            }
        }

    }
}
