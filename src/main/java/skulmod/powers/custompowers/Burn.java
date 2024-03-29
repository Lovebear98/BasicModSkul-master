package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class Burn extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("Burn");
    private static final PowerType TYPE = PowerType.DEBUFF;

    private static final boolean TURN_BASED = false;
    private boolean CausedOutside = TRUE;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public Burn(AbstractCreature owner, int amount) {
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
    public int onAttacked(DamageInfo info, int damageAmount) {
            if(info.type != DamageInfo.DamageType.NORMAL && damageAmount > 0){
                if(this.amount <= 1){
                    addToTop(new RemoveSpecificPowerAction(owner, owner, this));
                }else{
                    addToTop(new ApplyPowerAction(owner, owner, new Burn(AbstractDungeon.player, -1)));
                }
                damageAmount *= 2;
            }

        return super.onAttacked(info, damageAmount);
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
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new Burn(owner, amount);
    }
}
