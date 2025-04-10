package skulmod.util.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.custompowers.skulls.DavyJonesPower;

///A patch that checks for when we manually discard down to 0 cards in hand to triger Blackout
@SpirePatch(
        clz = GameActionManager.class,
        method = "incrementDiscard"
)
public class OnDiscardHook {
///Another patch learned from Packmaster
    @SpirePostfixPatch
    public static void YaManualDiscard(boolean endOfTurn) {
        ///If we're not end-of-turn discarding
        if (!AbstractDungeon.actionManager.turnHasEnded && !endOfTurn) {
            for(AbstractPower po: AbstractDungeon.player.powers){
                if(po instanceof DavyJonesPower){
                    ((DavyJonesPower)po).onDiscard();
                }
            }
        }
    }
}