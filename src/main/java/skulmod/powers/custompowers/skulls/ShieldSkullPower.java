package skulmod.powers.custompowers.skulls;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import skulmod.character.LittleBone;
import skulmod.powers.BasePower;
import skulmod.util.animation.AtlasPaths;

import static skulmod.SkulMod.makeID;

public class ShieldSkullPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("ShieldSkullPower");
    private static final PowerType TYPE = NeutralPowertypePatch.NEUTRAL;

    private static final boolean TURN_BASED = false;

    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public ShieldSkullPower(AbstractCreature owner, int amount) {

        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.priority = 100;
    }


    @Override
    public void atStartOfTurn() {


    }
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.type == AbstractCard.CardType.ATTACK){
            if(owner instanceof LittleBone){
                ///Play that Skull's animations
                ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.ShieldSkullAnim(), "ATTACK");
            }}
        super.onUseCard(card, action);
    }
    @Override
    public void atStartOfTurnPostDraw() {

    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != this.owner && info.type != DamageInfo.DamageType.THORNS && damageAmount - owner.currentBlock > 0) {// 372
            ///Play that Skull's animations
            if(owner instanceof LittleBone){
                ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.ShieldSkullAnim(), "DODGE");
            }
            ///We've found a skull, so we no longer need to default
            //
        }
        if(owner.hasPower(DexterityPower.POWER_ID)){
            if(owner.getPower(DexterityPower.POWER_ID).amount >= 1){
    if(info.owner != null && info.owner != owner && info.type != DamageInfo.DamageType.THORNS && !info.owner.isDeadOrEscaped() && !info.owner.halfDead){
        int DexDamage = owner.getPower(DexterityPower.POWER_ID).amount;
        addToBot(new DamageAction(info.owner, new DamageInfo(owner, (DexDamage), DamageInfo.DamageType.THORNS)));
        if(owner instanceof LittleBone) {
            ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.ShieldSkullAnim(), "ATTACK");
        }
    }
        }
        }
        return super.onAttacked(info, damageAmount);
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
        return new ShieldSkullPower(owner, amount);
    }
}
