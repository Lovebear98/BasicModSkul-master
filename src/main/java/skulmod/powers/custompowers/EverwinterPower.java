package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.orbs.Blizzard;
import skulmod.powers.BasePower;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class EverwinterPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("IAmTheSeaPower");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;
    private boolean CausedOutside = TRUE;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public EverwinterPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }


    @Override
    public void atStartOfTurn() {
        for(int Loops = this.amount; Loops > 0; Loops -= 1){
            addToBot(new ChannelAction(new Blizzard()));
        }
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
    public void onVictory() {

    }

    @Override
    public void onRemove() {

    }

    @Override
    public void onCardDraw(AbstractCard card) {

    }



    public void updateDescription() {
        if(this.amount == 1){
            this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]+DESCRIPTIONS[3];
        }else{
            this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]+DESCRIPTIONS[2]+DESCRIPTIONS[3];
        }
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new EverwinterPower(owner, amount);
    }
}
