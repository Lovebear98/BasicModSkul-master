package skulmod.patches.crosscompatskulls.unchained;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import theUnchainedMod.patches.RelayHelpers;

@SpirePatch(
        clz= RelayHelpers.class,
       method="relayDamageWhenAttacked",
        requiredModId = "TheUnchainedMod",
     optional = true)

public class UnchainedRiderPatch {
    @SpireInstrumentPatch
    public static ExprEditor Foobar() {
        return new ExprEditor() {
            ///Thank you, Autumn. Didn't realize I had to be super specific for this to play nice
            ///Allows us to add a condition to the "Can Relay Relay" clause
            public void edit(MethodCall m) throws CannotCompileException {
                if (m.getMethodName().equals("hasPower")) {
                    m.replace("{$_ = $proceed($$) || com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.hasPower(skulmod.util.othermodskulls.unchained.powers.RiderPower.POWER_ID);}");
                }
            }
        };
    }
}