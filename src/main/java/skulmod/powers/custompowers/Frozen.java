package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;

import static skulmod.SkulMod.makeID;

public class Frozen extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("Frozen");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public Frozen(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }






    @Override
    public void onInitialApplication() {
        super.onInitialApplication();

    }


    @Override
    public void atEndOfRound() {
        super.atEndOfRound();
    addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        return super.onAttacked(info, damageAmount);
    }


    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {
if(owner instanceof  AbstractMonster){
    if(damage > 0 && damageType == DamageInfo.DamageType.NORMAL){
        damage -= this.amount;
    }
}
        return super.atDamageReceive(damage, damageType, card);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        if(owner instanceof AbstractPlayer){
            if(damage > 0 && damageType == DamageInfo.DamageType.NORMAL) {
                damage -= this.amount;
            }
        }
        return super.atDamageReceive(damage, damageType);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if(damage > 0 && type == DamageInfo.DamageType.NORMAL){
            damage -= this.amount;
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
    public void onRemove() {

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
        return new Frozen(owner, amount);
    }
}
