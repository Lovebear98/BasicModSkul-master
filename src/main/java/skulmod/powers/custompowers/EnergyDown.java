package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import skulmod.powers.BasePower;

import static skulmod.SkulMod.makeID;

public class EnergyDown extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("EnergyDown");
    private static final PowerType TYPE = PowerType.DEBUFF;

    private static final boolean TURN_BASED = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public EnergyDown(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }


    @Override
    public void atStartOfTurn() {


    }

    @Override
    public void onEnergyRecharge() {
        super.onEnergyRecharge();
        if(EnergyPanel.totalCount >= this.amount){
            addToBot(new LoseEnergyAction(this.amount));
            addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
        }else{
            addToBot(new LoseEnergyAction(EnergyPanel.totalCount));
            addToBot(new ApplyPowerAction(owner, owner, new EnergyDown(owner, -EnergyPanel.totalCount)));
        }


    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();

    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
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

        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];



    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new EnergyDown(owner, amount);
    }
}
