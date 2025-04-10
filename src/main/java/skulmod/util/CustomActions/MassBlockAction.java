package skulmod.util.CustomActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class MassBlockAction extends AbstractGameAction {

    public MassBlockAction(int amount) {
        this.amount = amount;// 15
        this.actionType = ActionType.BLOCK;// 16
        this.duration = 0.25F;// 17
        this.startDuration = 0.25F;// 18
        if (Settings.FAST_MODE) {// 30
            this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;// 31
        }
    }

    public void update() {
        if(!AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead() && this.duration == startDuration){
            AbstractDungeon.player.addBlock(this.amount);
            AbstractDungeon.effectsQueue.add(new FlashAtkImgEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AttackEffect.SHIELD));// 46
            for(AbstractMonster mo : AbstractDungeon.getMonsters().monsters){
                if(!mo.isDeadOrEscaped()){
                    mo.addBlock(this.amount);// 47
                    AbstractDungeon.effectsQueue.add(new FlashAtkImgEffect(mo.hb.cX, mo.hb.cY, AttackEffect.SHIELD));// 46
                }
            }
            // 50
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                c.applyPowers();// 51
            }
        }
        this.tickDuration();// 56
    }// 57
}
