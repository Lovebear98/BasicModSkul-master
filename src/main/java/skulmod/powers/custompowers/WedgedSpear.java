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
import skulmod.relics.quintessence.Centauros;

import static skulmod.SkulMod.makeID;

public class WedgedSpear extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("WedgedSpear");
    private static final PowerType TYPE = PowerType.DEBUFF;

    private static final boolean TURN_BASED = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public WedgedSpear(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }


    @Override
    public void atStartOfTurn() {

    }

    @Override
    public void onEnergyRecharge() {
        super.onEnergyRecharge();

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
    public void atStartOfTurnPostDraw() {

    }


    @Override
    public void onVictory() {

    }

    @Override
    public void onRemove() {
        if(AbstractDungeon.player.hasRelic(Centauros.ID)){
            Centauros.GotSpear = true;
        }
    }

    @Override
    public void onCardDraw(AbstractCard card) {

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if(this.amount == 1){
            addToBot(new RemoveSpecificPowerAction(owner, owner, WedgedSpear.POWER_ID));
        }else{
            addToBot(new ApplyPowerAction(owner, owner, new WedgedSpear(owner, -1)));
        }
    }


    @Override
    public void onDeath() {
if(AbstractDungeon.player.hasRelic(Centauros.ID)){
        Centauros.GotSpear = true;
}
        super.onDeath();
    }

    public void updateDescription() {

        this.description = DESCRIPTIONS[0];



    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new WedgedSpear(owner, amount);
    }
}
