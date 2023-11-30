package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;
import skulmod.powers.custompowers.skulls.BomberSkullPower;

import static skulmod.SkulMod.makeID;

public class Gunpowder extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("Gunpowder");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public Gunpowder(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }


    @Override
    public void atStartOfTurn() {


    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        if(owner.hasPower(BomberSkullPower.POWER_ID)){
            AbstractPower BomberPow = owner.getPower(BomberSkullPower.POWER_ID);
            if(BomberPow instanceof BomberSkullPower){
                BomberPow.updateDescription();
            }
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if(owner.hasPower(BomberSkullPower.POWER_ID)){
            AbstractPower BomberPow = owner.getPower(BomberSkullPower.POWER_ID);
            if(BomberPow instanceof BomberSkullPower){
                BomberPow.updateDescription();
            }
        }
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
        this.description = DESCRIPTIONS[0];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new Gunpowder(owner, amount);
    }
}
