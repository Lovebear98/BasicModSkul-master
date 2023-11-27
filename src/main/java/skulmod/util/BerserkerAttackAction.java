package skulmod.util;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.powers.custompowers.BoneScreamPower;

public class BerserkerAttackAction extends AbstractGameAction {
    private static final float DURATION = 0.1F;
    private final AbstractPlayer p;


    public BerserkerAttackAction(AbstractCreature target) {
        this.p = AbstractDungeon.player;
        this.target = target;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
    }

    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            ///If we killed something, EVEN MINIONS
            if(TargetIsDying(target)){
                ///Remove Bone Scream
                addToBot(new RemoveSpecificPowerAction(p, p, BoneScreamPower.POWER_ID));
            }
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();

            }
        this.tickDuration();
    }

    private boolean TargetIsDying(AbstractCreature mo){
        if(target != null){
            /// && !this.target.hasPower("Minion")
            return ((mo.isDying || mo.currentHealth <= 0) && !mo.halfDead);
        }
        return false;
    }
}