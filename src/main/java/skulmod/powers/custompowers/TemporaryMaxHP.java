package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class TemporaryMaxHP extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("TemporaryMaxHP");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;
    private int TotalHealth;

    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public TemporaryMaxHP(AbstractCreature owner, int amount) {
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
    public void wasHPLost(DamageInfo info, int damageAmount) {
        super.wasHPLost(info, damageAmount);
        int RemoveCount = 0;
        if(damageAmount > this.amount){RemoveCount = this.amount;} else {RemoveCount = damageAmount;}
        addToTop(new HealAction(owner, owner, RemoveCount));
        if(this.amount == RemoveCount){
            addToTop(new RemoveSpecificPowerAction(owner, owner, this.POWER_ID));
        } else {
            addToTop(new ApplyPowerAction(owner, owner, new TemporaryMaxHP(owner, -RemoveCount)));
        }
    }
    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);

    }

    @Override
    public void atStartOfTurnPostDraw() {

    }

    @Override
    public void onVictory() {
        super.onVictory();

    }

    @Override
    public void onRemove() {
        super.onRemove();

    }

    @Override
    public void onCardDraw(AbstractCard card) {

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new TemporaryMaxHP(owner, amount);
    }
}
