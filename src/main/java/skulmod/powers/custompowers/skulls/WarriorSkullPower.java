package skulmod.powers.custompowers.skulls;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import skulmod.character.LittleBone;
import skulmod.powers.BasePower;
import skulmod.powers.custompowers.Charge;
import skulmod.util.animation.AtlasPaths;

import static skulmod.SkulMod.makeID;

public class WarriorSkullPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("WarriorSkullPower");
    private static final PowerType TYPE = NeutralPowertypePatch.NEUTRAL;

    private static final boolean TURN_BASED = false;
    public static Boolean AttackedThisTurn;

    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public WarriorSkullPower(AbstractCreature owner, int amount) {

        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.priority = 100;
    }
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.hasTag(AbstractCard.CardTags.STRIKE)){
            addToBot(new GainEnergyAction(1));
        }
        if(card.type == AbstractCard.CardType.ATTACK){
            if(owner instanceof LittleBone){
                ///Play that Skull's animations
                ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.WarriorAnim(), "ATTACK");
            }}
        super.onUseCard(card, action);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != this.owner && info.type != DamageInfo.DamageType.THORNS && damageAmount - owner.currentBlock > 0) {// 372
            ///Play that Skull's animations
            if(owner instanceof LittleBone){
                ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.WarriorAnim(), "DODGE");
            }
            ///We've found a skull, so we no longer need to default
            //
        }
        return super.onAttacked(info, damageAmount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);

    }

    @Override
    public void atStartOfTurn() {


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
        this.description = DESCRIPTIONS[0];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new WarriorSkullPower(owner, amount);
    }
}
