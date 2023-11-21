package skulmod.powers.custompowers.forms;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;
import skulmod.util.CustomActions.SkullActions.MillAction;

import static skulmod.SkulMod.makeID;

public class EternalForm extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("EternalForm");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public EternalForm(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
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
    public void onCardDraw(AbstractCard card) {
            addToBot(new MillAction(this.amount));

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {


    }

    public void updateDescription() {
if(this.amount == 1){
    this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[2];
}else{
    this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
}
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new EternalForm(owner, amount);
    }



}
