package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;

import static skulmod.SkulMod.makeID;

public class RippedPocket extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("RippedPocket");
    private static final PowerType TYPE = PowerType.DEBUFF;

    private static final boolean TURN_BASED = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public RippedPocket(AbstractCreature owner, int amount) {
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
        if (this.amount <= 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
        updateDescription();
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


        @Override
        public int onAttacked(DamageInfo info, int damageAmount) {
            if (info.owner != this.owner && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && damageAmount > 0 &&info.owner != null) {
                int GoldStolen;
                if(this.amount > 5){
                    GoldStolen = Math.max(5, this.amount / 2);
                }else{
                    GoldStolen = this.amount;
                }
                info.owner.gainGold(GoldStolen);
                addToTop(new ApplyPowerAction(owner, owner, new RippedPocket(owner, -GoldStolen)));
            }
            return super.onAttacked(info, damageAmount);
    }



    public void updateDescription() {
        int GoldStolen;
        if(this.amount > 5){
            GoldStolen = Math.max(5, this.amount / 2);
        }else{
            GoldStolen = this.amount;
        }
        this.description = DESCRIPTIONS[0]+GoldStolen+DESCRIPTIONS[1]+this.amount+DESCRIPTIONS[2];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new RippedPocket(owner, amount);
    }
}
