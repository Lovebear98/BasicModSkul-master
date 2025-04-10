package skulmod.patches.crosscompatskulls.unchained;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.util.othermodskulls.unchained.stances.RidingStance;
import theUnchainedMod.patches.RelayHelpers;

@SpirePatch(
        clz = RelayHelpers.class,
        method = "addThisTurnRelayedDamage",
        requiredModId = "TheUnchainedMod",
        optional = true)
public class ApplyPowersTTRPatch {

    @SpirePostfixPatch
    public static void Postfix() {
        if (AbstractDungeon.player.stance.ID.equals(RidingStance.STANCE_ID)) {
            AbstractDungeon.player.stance.updateDescription();
            for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
                mo.applyPowers();
            }
        }
    }
}
