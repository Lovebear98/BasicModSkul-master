package skulmod.util.CustomActions.SkullActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import skulmod.relics.LuckAddiction;

public class FailedGambleAction extends AbstractGameAction {
    private final int count;

    public FailedGambleAction(int count) {
        this.count = count;
    }

    @Override
    public void update() {
        if(AbstractDungeon.player.hasRelic(LuckAddiction.ID)){
            for(int Loops = count; Loops > 0; Loops -= 1){
                int Buffs = 0;
                if(AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)){
                    if(AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount != 0){
                        Buffs += AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount;
                    }
                }
                if(AbstractDungeon.player.hasPower(DexterityPower.POWER_ID)){
                    if(AbstractDungeon.player.getPower(DexterityPower.POWER_ID).amount != 0){
                        Buffs += AbstractDungeon.player.getPower(DexterityPower.POWER_ID).amount;
                    }
                }
                if(AbstractDungeon.player.hasPower(FocusPower.POWER_ID)){
                    if(AbstractDungeon.player.getPower(FocusPower.POWER_ID).amount != 0){
                        Buffs += AbstractDungeon.player.getPower(FocusPower.POWER_ID).amount;
                    }
                }
                    addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, Math.max((Buffs/2), 1), DamageInfo.DamageType.THORNS), AttackEffect.NONE));
            }
        }
        isDone = true;
    }
}
