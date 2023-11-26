package skulmod.powers.custompowers.forms;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.LockOnPower;
import skulmod.powers.BasePower;
import skulmod.powers.custompowers.LoseFocusPower;

import static skulmod.SkulMod.makeID;

public class SniperForm extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("SniperForm");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;

    private boolean FirstStrike;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public SniperForm(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }






    @Override
    public void onInitialApplication() {
        super.onInitialApplication();

    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
            if(card.type.equals(AbstractCard.CardType.SKILL)){
                addToBot(new ApplyPowerAction(owner, owner, new FocusPower(owner, this.amount)));
                addToBot(new ApplyPowerAction(owner, owner, new LoseFocusPower(owner, this.amount)));
            }
        super.onUseCard(card, action);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
if(info.type == DamageInfo.DamageType.NORMAL){
    addToBot(new ApplyPowerAction(target, owner, new LockOnPower(target, this.amount)));
}
        super.onAttack(info, damageAmount, target);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if(type == DamageInfo.DamageType.NORMAL){
            damage = 3;
        }
        return super.atDamageGive(damage, type);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);

    }







    @Override
    public void atStartOfTurnPostDraw() {

    }






    @Override
    public void onCardDraw(AbstractCard card) {

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {


    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]+this.amount+DESCRIPTIONS[2];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new SniperForm(owner, amount);
    }
}
