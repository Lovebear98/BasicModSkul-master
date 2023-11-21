package skulmod.powers.custompowers.skulls;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.stances.WrathStance;
import skulmod.character.LittleBone;
import skulmod.powers.BasePower;
import skulmod.util.animation.AtlasPaths;


import static skulmod.SkulMod.makeID;

public class ChampionPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("ChampionPower");
    private static final PowerType TYPE = NeutralPowertypePatch.NEUTRAL;

    private static final boolean TURN_BASED = false;
    
    private float HealthPercent;
    private float DamageMod;
    public static int ChampionBonus;

    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public ChampionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.priority = 100;
    }


    @Override
    public void atStartOfTurn() {


    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        float finaldamage = damageAmount;
        if (info.owner != this.owner && info.type != DamageInfo.DamageType.THORNS && damageAmount - owner.currentBlock > 0) {// 372
            ///Play that Skull's animations
            if(owner instanceof LittleBone){
                ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.ChampionAnim(), "DODGE");
            }
            ///We've found a skull, so we no longer need to default
            //
            if(info.type != DamageInfo.DamageType.HP_LOSS){
                CardCrawlGame.sound.play("SKUL_CHAMPIONATTACK", 0.6f);
                if(owner instanceof LittleBone) {
                    ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.ChampionAnim(), "ATTACK");
                }





                ///Make counterattack obey Wrath
if(!info.owner.isDeadOrEscaped() && !info.owner.isDead && !info.owner.halfDead){
    addToTop(new DamageAction(info.owner, new DamageInfo(AbstractDungeon.player, (int) finaldamage, DamageInfo.DamageType.THORNS),  AbstractGameAction.AttackEffect.BLUNT_HEAVY));
}
            }
        }
        return super.onAttacked(info, damageAmount);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {

        return super.atDamageReceive(damage, damageType, card);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.type == AbstractCard.CardType.ATTACK){
        if(owner instanceof  LittleBone){
            ///Play that Skull's animations
            CardCrawlGame.sound.play("SKUL_CHAMPIONATTACK", 0.6f);
            ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.ChampionAnim(), "ATTACK");
        }}
        super.onUseCard(card, action);
    }

    @Override
    public void atStartOfTurnPostDraw() {

    }



    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);
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
        return new ChampionPower(owner, amount);
    }



}
