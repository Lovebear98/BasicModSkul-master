package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import jdk.nashorn.internal.runtime.NumberToString;
import skulmod.powers.BasePower;

import static skulmod.SkulMod.makeID;

public class HerculeanStrength extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("HerculeanStrength");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public HerculeanStrength(AbstractCreature owner, int amount) {
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
    public void atStartOfTurnPostDraw() {

    }


    @Override
    public void onVictory() {

    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if(this.amount == 3){
            CardCrawlGame.sound.play("HERC_PROC", 0.2f);
        }
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
        String HercText = TruncateNum(NumberToString.stringFor(Math.pow(1.5, this.amount)), 5);
     if(this.amount >= 3){
         this.description = DESCRIPTIONS[0] + HercText + DESCRIPTIONS[2];
     }else{
         this.description = DESCRIPTIONS[0] + HercText + DESCRIPTIONS[1];
     }

    }

    private String TruncateNum(String text, int length){
        if (text.length() <= length) {
            return text;
        } else {
            return text.substring(0, length);
        }
    }



    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new HerculeanStrength(owner, amount);
    }
}
