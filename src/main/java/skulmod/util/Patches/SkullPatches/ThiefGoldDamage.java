package skulmod.util.Patches.SkullPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import skulmod.powers.custompowers.skulls.PettyThiefPower;

public class ThiefGoldDamage {
    @SpirePatch(clz = AbstractPlayer.class, method = "gainGold", paramtypez = {int.class} )
    public static class RecklessAttackPatch {
        public static int GoldInCombat;

        @SpirePostfixPatch
        public static void patch(AbstractPlayer __instance, int amount) {
            if(CardCrawlGame.isInARun() && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){
                CardCrawlGame.sound.play("SKUL_GOLD", 0.2f);
                if (AbstractDungeon.player.hasPower(PettyThiefPower.POWER_ID)) {
                    AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                if(mo != null){
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(AbstractDungeon.player, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
                }

                }
                GoldInCombat += amount;
            }
        }
    }


}

