package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;
import skulmod.powers.custompowers.skulls.AbsoluteSkullPower;

import static skulmod.SkulMod.makeID;

public class Labors extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("Labors");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public Labors(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }


    @Override
    public void atStartOfTurn() {


    }



    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        if(this.amount >= 12){
            do{
                this.amount -= 12;
                addToBot(new MakeTempCardInHandAction(AbsoluteSkullPower.RandomGrapple(true)));
            }while(this.amount >= 12);

            if (this.amount <= 0) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if(this.amount >= 12){
            do{
                this.amount -= 12;
                addToBot(new MakeTempCardInHandAction(AbsoluteSkullPower.RandomGrapple(true)));
            }while(this.amount >= 12);

            if (this.amount <= 0) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
        }
    }
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {

        return super.atDamageGive(damage, type);

    }
    @Override
    public void atStartOfTurnPostDraw() {

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


    }

    public void updateDescription() {

        this.description = DESCRIPTIONS[0]+(12-this.amount)+DESCRIPTIONS[1];



    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new Labors(owner, amount);
    }
}
