package skulmod.powers.custompowers.skulls;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnDrawPileShufflePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.character.LittleBone;
import skulmod.powers.BasePower;
import skulmod.util.animation.AtlasPaths;

import static skulmod.SkulMod.makeID;

public class WerewolfPower extends BasePower implements CloneablePowerInterface, OnDrawPileShufflePower {
    public static final String POWER_ID = makeID("WerewolfPower");
    private static final PowerType TYPE = NeutralPowertypePatch.NEUTRAL;

    private static final boolean TURN_BASED = false;
    private boolean ThisDraw = false;

    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public WerewolfPower(AbstractCreature owner, int amount) {

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
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != this.owner && info.type != DamageInfo.DamageType.THORNS && damageAmount - owner.currentBlock > 0) {// 372
            ///Play that Skull's animations
            if(owner instanceof LittleBone){
                ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.WerewolfAnim(), "DODGE");
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
        ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.WerewolfAnim(), "ATTACK");
    }
}
        super.onUseCard(card, action);
    }





    @Override
    public void atEndOfTurn(boolean isPlayer) {

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+(AbstractDungeon.player.drawPile.size()+AbstractDungeon.player.hand.size()+AbstractDungeon.player.discardPile.size())+DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new WerewolfPower(owner, amount);
    }

    @Override
    public void onShuffle() {
        if(AbstractDungeon.player.drawPile.size()+AbstractDungeon.player.discardPile.size() != 0){
            AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
            if(mo != null){
                if(owner instanceof LittleBone){
                    ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.WerewolfAnim(), "DODGE");
                }
                addToTop(new DamageAction(mo, new DamageInfo(owner, (AbstractDungeon.player.drawPile.size()+AbstractDungeon.player.hand.size()+AbstractDungeon.player.discardPile.size()), DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }
    }

}
