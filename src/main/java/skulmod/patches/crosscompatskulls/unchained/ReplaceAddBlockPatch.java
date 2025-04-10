package skulmod.patches.crosscompatskulls.unchained;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.util.othermodskulls.unchained.powers.RiderPower;
import theUnchainedMod.actions.GainRelayAction;

@SpirePatch(
        clz = AbstractCreature.class,
        method = "addBlock",
        requiredModId = "TheUnchainedMod",
        optional = true)
public class ReplaceAddBlockPatch {

    @SpirePrefixPatch
    public static SpireReturn<Void> Postfix(AbstractCreature __instance, @ByRef int[] amount) {
        if(__instance instanceof AbstractPlayer){
            if(AbstractDungeon.player.hasPower(RiderPower.POWER_ID)){
                AbstractDungeon.actionManager.addToTop(new GainRelayAction(__instance, amount[0]));
                amount[0] = 0;
                return SpireReturn.Return();
            }
        }
            return SpireReturn.Continue();

    }
}