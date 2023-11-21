package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class Souls extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("Souls");
    private static final PowerType TYPE = NeutralPowertypePatch.NEUTRAL;

    private static final boolean TURN_BASED = false;
    private boolean CausedOutside = TRUE;
    public static Boolean FirstDarkAttack = TRUE;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public Souls(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
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

    @Override
    public int onLoseHp(int damageAmount) {
        updateDescription();
        return super.onLoseHp(damageAmount);
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        updateDescription();
        super.wasHPLost(info, damageAmount);
    }

    @Override
    public int onHeal(int healAmount) {
        updateDescription();
        return super.onHeal(healAmount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]+DESCRIPTIONS[2]+(int)(Math.max(((AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) * ((this.amount)/100.0F)), 1));
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new Souls(owner, amount);
    }
}
