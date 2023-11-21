package skulmod.powers.custompowers.forms;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.WrathStance;
import skulmod.powers.BasePower;
import skulmod.powers.custompowers.TemporaryMaxHP;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class RageMode extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("RageMode");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public RageMode(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }


    @Override
    public void atStartOfTurn() {


    }



    @Override
    public void onInitialApplication() {
        super.onInitialApplication();

    }




    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        super.onPlayCard(card, m);

    }






    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);

    }



    @Override
    public void atStartOfTurnPostDraw() {

    }



    @Override
    public void onDeath() {
        super.onDeath();
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
if(AbstractDungeon.player.stance.ID.equals(WrathStance.STANCE_ID)){
    if(damageAmount >= owner.currentHealth+owner.currentBlock + TempHPField.tempHp.get(owner)){
        damageAmount = 0;
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }
}
        return super.onAttackedToChangeDamage(info, damageAmount);
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType damageType) {
        if(AbstractDungeon.player.stance.ID.equals(WrathStance.STANCE_ID)){
            if(damage > 0 && damageType == DamageInfo.DamageType.NORMAL){
                damage *= this.amount;
            }
        }
        return super.atDamageReceive(damage, damageType);
    }
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
       if(AbstractDungeon.player.stance.ID.equals(WrathStance.STANCE_ID)){
           if(damage > 0 && type == DamageInfo.DamageType.NORMAL){
               damage *= this.amount;
           }
       }
        return super.atDamageGive(damage, type);

    }


    @Override
    public void onRemove() {

    }

    @Override
    public void onCardDraw(AbstractCard card) {

    }


    public void updateDescription() {

    this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];

    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new RageMode(owner, amount);
    }
}
