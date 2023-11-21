package skulmod.powers.custompowers.forms;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;
import skulmod.util.CustomActions.SkullActions.BonepileAction;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;
import static skulmod.util.CustomActions.SkullActions.ChooseASkull.ActiveSkull;

public class OutlawForm extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("OutlawForm");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;
    private boolean CausedOutside = TRUE;
    public static Boolean FirstDarkAttack = TRUE;
    private boolean FirstTime = true;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public OutlawForm(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
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
    public void onCardDraw(AbstractCard card) {

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
    addToBot(new GainGoldAction(this.amount));

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new OutlawForm(owner, amount);
    }
}
