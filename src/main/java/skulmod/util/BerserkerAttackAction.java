package skulmod.util;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.HashMap;

public class BerserkerAttackAction extends AbstractGameAction {
    private static final float DURATION = 0.1F;
    private final AbstractPlayer p;

    public static HashMap<String, Integer> BossList = new HashMap<String, Integer>();
    private final int i;


    public BerserkerAttackAction(int damageAmount, AbstractCreature target) {
        this.p = AbstractDungeon.player;
        this.i = damageAmount;
        this.target = target;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
    }

    public void update() {
            if(IsInList(target.id)){
                int OldHP = BossList.get(target.id);
                BossList.put(target.id, OldHP - i);
                if(BossList.get(target.id) <= 0){
                    BossList.remove(target.id);
                    addToBot(new RemoveSpecificPowerAction(p, p, skulmod.powers.custompowers.BoneScreamPower.POWER_ID));
                }
            }

        if (this.duration == 0.1F && this.target != null) {

            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();

            }
        this.tickDuration();
    }

    private boolean IsInList(String id){
                if(!BossList.isEmpty()){
                    return BossList.containsKey(id);
                }
        return false;
    }
}