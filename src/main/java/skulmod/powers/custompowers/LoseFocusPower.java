package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import skulmod.powers.BasePower;

import static skulmod.SkulMod.makeID;

public class LoseFocusPower extends BasePower implements CloneablePowerInterface{
    public static final String POWER_ID = makeID("LoseFocusPower");
    private static final PowerType TYPE = PowerType.DEBUFF;

    private static final boolean TURN_BASED = false;
    private int TotalHealth;

    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public LoseFocusPower(AbstractCreature owner, int amount) {
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
    public void atEndOfRound() {
        this.flash();
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new FocusPower(this.owner, -this.amount), -this.amount));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, LoseFocusPower.POWER_ID));
        super.atEndOfRound();
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
        return new LoseFocusPower(owner, amount);
    }
}
