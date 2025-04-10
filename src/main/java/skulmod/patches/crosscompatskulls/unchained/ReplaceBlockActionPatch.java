package skulmod.patches.crosscompatskulls.unchained;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.util.othermodskulls.unchained.powers.RiderPower;
import theUnchainedMod.actions.GainRelayAction;

@SpirePatch(
        clz = GainBlockAction.class,
        method = "update",
        requiredModId = "TheUnchainedMod",
        optional = true)
public class ReplaceBlockActionPatch {

    @SpirePrefixPatch
    public static SpireReturn<Void> Postfix(AbstractGameAction __instance) {
        if(__instance.target instanceof AbstractPlayer){
            if(AbstractDungeon.player.hasPower(RiderPower.POWER_ID)){
                AbstractDungeon.actionManager.addToTop(new GainRelayAction(AbstractDungeon.player, __instance.amount));
                __instance.amount = 0;
                __instance.isDone = true;
                return SpireReturn.Return();
            }
        }
            return SpireReturn.Continue();

    }
}