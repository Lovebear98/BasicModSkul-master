package skulmod.powers.custompowers.forms;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import skulmod.orbs.Blizzard;
import skulmod.powers.BasePower;

import static skulmod.SkulMod.makeID;

public class FrozenSoulForm extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("FrozenSoulForm");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;
    private int BallGen;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public FrozenSoulForm(AbstractCreature owner, int amount) {
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
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        if(EnergyPanel.totalCount > 0){
            int En = EnergyPanel.totalCount;
            addToBot(new LoseEnergyAction(En));
            for(int Loops = En * this.amount; Loops > 0; Loops -= 1){
                addToBot(new ChannelAction(new Blizzard()));
            }
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {

    }





    public void updateDescription() {
    this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new FrozenSoulForm(owner, amount);
    }



}
