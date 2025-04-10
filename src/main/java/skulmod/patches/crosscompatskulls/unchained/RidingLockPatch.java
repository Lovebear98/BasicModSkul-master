package skulmod.patches.crosscompatskulls.unchained;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import skulmod.SkulMod;
import theUnchainedMod.patches.RelayedDamageCalculations;

@SpirePatch(
        clz = RelayedDamageCalculations.class,
        method = "calculateAndApplyRelayedDamage",
        requiredModId = "TheUnchainedMod",
        optional = true)
public class RidingLockPatch {

    @SpirePrefixPatch
    public static void Prefix() {
            SkulMod.LockRelay = true;
    }
}

