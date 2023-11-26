package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;

import static skulmod.SkulMod.makeID;

public class Lucky7 extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("Lucky7");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public Lucky7(AbstractCreature owner, int amount) {
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
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        ///Removed [ && !(damageAmount == 0) ] from the if statement so that it triggers onALL attacks
        if(info.type == DamageInfo.DamageType.NORMAL){
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));

        }
        super.onAttack(info, damageAmount, target);
    }

    @Override
    public void atStartOfTurnPostDraw() {

    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        float BonusDamage = (float) Math.ceil((damage * this.amount) / 100);
            damage = damage + BonusDamage;
        return super.atDamageGive(damage, type);

    }

    @Override
    public void onVictory() {

    }

    @Override
    public void onRemove() {

    }

    @Override
    public void onCardDraw(AbstractCard card) {

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.amount +DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new Lucky7(owner, amount);
    }
}
