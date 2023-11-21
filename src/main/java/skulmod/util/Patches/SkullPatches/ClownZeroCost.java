package skulmod.util.Patches.SkullPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import skulmod.powers.custompowers.skulls.ClownPower;

import static java.lang.Boolean.TRUE;

    public class ClownZeroCost {
        @SpirePatch(clz = AbstractCard.class, method = "freeToPlay")
        public static class RecklessAttackPatch {
            @SpirePostfixPatch
            public static boolean patch(boolean __result, AbstractCard __instance) {
                return __result || (isIndeedWithoutADoubtInCombat() && AbstractDungeon.player.hasPower(ClownPower.POWER_ID) && ClownPower.NoCardsThisTurn == TRUE);
            }
        }

        private static boolean isIndeedWithoutADoubtInCombat() {
            return (AbstractDungeon.player != null && AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT);
        }
    }

