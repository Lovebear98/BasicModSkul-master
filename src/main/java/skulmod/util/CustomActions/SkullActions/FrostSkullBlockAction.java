package skulmod.util.CustomActions.SkullActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FrostSkullBlockAction extends AbstractGameAction {
    private final int i;

    public FrostSkullBlockAction(int blockLost) {
        this.i = blockLost;
    }

    @Override
    public void update() {
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!mo.isDying && !mo.isDead && !mo.isDeadOrEscaped() && !mo.halfDead){
                this.addToTop(new GainBlockAction(mo, AbstractDungeon.player, i));
            }
        }
        isDone = true;
    }
}
