package skulmod.powers.custompowers.skulls;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BlurPower;
import skulmod.character.LittleBone;
import skulmod.powers.BasePower;
import skulmod.powers.custompowers.DarkPower;
import skulmod.util.animation.AtlasPaths;

import static skulmod.SkulMod.makeID;

public class DarkPaladinPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("DarkPaladinPower");
    private static final PowerType TYPE = NeutralPowertypePatch.NEUTRAL;

    private static final boolean TURN_BASED = false;

    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public DarkPaladinPower(AbstractCreature owner, int amount) {

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
    public int onPlayerGainedBlock(int blockAmount) {
        return super.onPlayerGainedBlock(blockAmount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != this.owner && info.type != DamageInfo.DamageType.THORNS && damageAmount - owner.currentBlock > 0) {// 372
            ///Play that Skull's animations
            if(owner instanceof LittleBone){
                ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.DarkPaladinAnim(), "DODGE");
            }
            ///We've found a skull, so we no longer need to default
            //
        }
        if (damageAmount <= owner.currentBlock) {// 372
             addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DarkPower(AbstractDungeon.player, 4)));
            }
        return super.onAttacked(info, damageAmount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
if(card.type == AbstractCard.CardType.ATTACK){
    if(owner instanceof LittleBone){
        ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.DarkPaladinAnim(), "ATTACK");
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
        return new DarkPaladinPower(owner, amount);
    }
}
