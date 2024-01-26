package skulmod.util.CustomActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;

public class BlizzardOrbAction extends AbstractGameAction {
    private final float i;

    public BlizzardOrbAction(int passiveAmount) {
    this.i = passiveAmount;
    }

    @Override
    public void update() {
        ///For each monster
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            ///If it's a valid target
            if(!mo.isDying && !mo.isDead && !mo.isDeadOrEscaped() && !mo.halfDead){
                ///And has more than 0 Block
                if(mo.currentBlock > 0){
                    ///The base loss is what we passed
                    float Loss = i;
                    ///But goes up by 50% if the target has Lock-On.
                    if(mo.hasPower(LockOnPower.POWER_ID)){
                        Loss *= 1.5f;
                    }
                    ///It loses HP equal to the lower of its Block or the amount we passed
                    addToBot(new LoseHPAction(mo, AbstractDungeon.player, Math.min(mo.currentBlock, (int) Loss)));
                }
            }
        }
        isDone = true;
    }
}

