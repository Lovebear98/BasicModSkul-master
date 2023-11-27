package skulmod.powers.custompowers.skulls;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import skulmod.character.LittleBone;
import skulmod.powers.BasePower;
import skulmod.util.animation.AtlasPaths;

import static skulmod.SkulMod.makeID;

public class BerserkerSkullPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("BerserkerSkullPower");
    private static final PowerType TYPE = NeutralPowertypePatch.NEUTRAL;

    private static final boolean TURN_BASED = false;

    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public BerserkerSkullPower(AbstractCreature owner, int amount) {

        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.priority = 100;
    }


    @Override
    public void atStartOfTurn() {


    }

    @Override
    public void atStartOfTurnPostDraw() {

    }

    @Override
    public int onLoseHp(int damageAmount) {
        addToBot(new ApplyPowerAction(owner, owner, new GainStrengthPower(owner, damageAmount)));
        return super.onLoseHp(damageAmount);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        ///Only if we have Strength and the damage is greater than or equal to our current HP
        if(owner.hasPower(StrengthPower.POWER_ID) && damageAmount >= owner.currentHealth){
            ///If we would take fatal damage LESS THAN our Strength
            if(owner.getPower(StrengthPower.POWER_ID).amount > damageAmount){
                ///Set the damage to what would leave us at 1 HP
                damageAmount = owner.currentHealth - 1;
                ///Set the heal amount to half our max health minus 1 to account for us being at 1 HP
                int AmountToHeal = (owner.maxHealth/2 - 1);
                ///Heal us to half HP
                addToBot(new HealAction(owner, owner, AmountToHeal));

            }
        }
        return super.onAttackedToChangeDamage(info, damageAmount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != this.owner && info.type != DamageInfo.DamageType.THORNS && damageAmount - owner.currentBlock > 0) {// 372
            ///Play that Skull's animations
            if(owner instanceof LittleBone){
                ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.Berserker(), "DODGE");
            }
            ///We've found a skull, so we no longer need to default
            //
        }
        return super.onAttacked(info, damageAmount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
if(card.type == AbstractCard.CardType.ATTACK){
    if(owner instanceof LittleBone){
        ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.Berserker(), "ATTACK");
    }
}
        super.onUseCard(card, action);
    }

    @Override
    public void onCardDraw(AbstractCard card) {

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new BerserkerSkullPower(owner, amount);
    }
}
