package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class DarkPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("DarkPower");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;
    private boolean CausedOutside = TRUE;
    public static Boolean FirstDarkAttack = TRUE;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public DarkPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }


    @Override
    public void atStartOfTurn() {
        FirstDarkAttack = TRUE;

    }



    @Override
    public void onInitialApplication() {
        super.onInitialApplication();

    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        super.onPlayCard(card, m);

    }
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
if(damage > 0 && type == DamageInfo.DamageType.NORMAL){
    if(FirstDarkAttack == TRUE){
        damage = (float) (damage + (this.amount/2));
    }else{
        damage = damage;
    }

}
        return super.atDamageGive(damage, type);

    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if(damageAmount > 0 && info.type == DamageInfo.DamageType.NORMAL) {
            FirstDarkAttack = FALSE;
        }
        super.onAttack(info, damageAmount, target);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {

if(info.owner != this.owner && damageAmount > 0){
    damageAmount = (damageAmount - this.amount);
    addToTop(new RemoveSpecificPowerAction(owner, owner, this));
}
        return super.onAttackToChangeDamage(info, damageAmount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);

    }



    @Override
    public void atStartOfTurnPostDraw() {

    }



    @Override
    public void onVictory() {
FirstDarkAttack = TRUE;
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
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]+(this.amount/2)+DESCRIPTIONS[2];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new DarkPower(owner, amount);
    }
}
