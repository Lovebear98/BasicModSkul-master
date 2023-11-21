package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import skulmod.powers.BasePower;

import static skulmod.SkulMod.makeID;

public class DoomedDance extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("DoomedDance");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;
    public static boolean powerupgraded;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public DoomedDance(AbstractCreature owner, int amount) {
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
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);

    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {

    }



    @Override
    public void onVictory() {
        powerupgraded = false;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
     if(damageAmount > 0){
         if(powerupgraded){
             addToTop(new ApplyPowerAction(owner, owner, new ConstrictedPower(owner, owner, this.amount)));
         }else{
             addToTop(new ApplyPowerAction(owner, owner, new PoisonPower(owner, owner, this.amount)));
         }
         addToTop(new RemoveSpecificPowerAction(owner, owner, this));
     }
        return super.onAttacked(info, damageAmount);
    }

    @Override
    public void onRemove() {
        powerupgraded = false;
    }

    @Override
    public void onCardDraw(AbstractCard card) {

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
    addToBot(new GainBlockAction(owner, this.amount));

    }

    public void updateDescription() {
if(powerupgraded){
    this.description = DESCRIPTIONS[0]+this.amount +DESCRIPTIONS[1]+this.amount+DESCRIPTIONS[3]+DESCRIPTIONS[4];
}else{
    this.description = DESCRIPTIONS[0]+this.amount +DESCRIPTIONS[1]+this.amount+DESCRIPTIONS[2]+DESCRIPTIONS[4];
}
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new DoomedDance(owner, amount);
    }
}
