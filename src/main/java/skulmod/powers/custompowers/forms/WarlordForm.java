package skulmod.powers.custompowers.forms;

import basemod.helpers.CardModifierManager;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class WarlordForm extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("WarlordForm");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;
    private boolean CausedOutside = TRUE;
    public static Boolean FirstDarkAttack = TRUE;
    private boolean FirstTime = true;
    private boolean FirstStrike;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public WarlordForm(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }






    @Override
    public void onInitialApplication() {
        super.onInitialApplication();

    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(FirstStrike){
            if(card.hasTag(AbstractCard.CardTags.STRIKE) || CardModifierManager.hasModifier(card, "ChargeModifier")){
                card.cost -= this.amount;
                if(card.cost < 0){
                    card.cost = 0;
                }
                FirstStrike = false;
            }
        }
        super.onUseCard(card, action);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);

    }

    @Override
    public void atStartOfTurn() {
        FirstStrike = true;
        super.atStartOfTurn();
    }

    @Override
    public void onRemove() {
        FirstStrike = true;
        super.onRemove();
    }

    @Override
    public void onVictory() {
        FirstStrike = true;
        super.onVictory();
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
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new WarlordForm(owner, amount);
    }
}
