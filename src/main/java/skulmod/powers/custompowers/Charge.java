package skulmod.powers.custompowers;

import basemod.helpers.CardModifierManager;
import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.cards.Cardmods.ChargeModifier;
import skulmod.powers.BasePower;
import skulmod.util.vars;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class Charge extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("Charge");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;
    private Boolean HitMaxStacks = FALSE;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public Charge(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }


    @Override
    public void atStartOfTurn() {


    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
            if(this.amount >= 5){
                this.amount = 5;
                if(HitMaxStacks == FALSE){
                    CardCrawlGame.sound.play("SKUL_CHARGECAP", 0.6f);
                    addToBot(new SelectCardsInHandAction(1, vars.Charge(), true, true, card -> card.type.equals(AbstractCard.CardType.ATTACK) && card.cost != -1 && card.cost != -2, ToExhaust -> {
                        for (AbstractCard c : ToExhaust) {
                            CardModifierManager.addModifier(c, new ChargeModifier());

                        }
                    }));
                    addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, Charge.POWER_ID));
                    HitMaxStacks = TRUE;
                }


        }else{
                CardCrawlGame.sound.play("SKUL_CHARGEGAIN", 0.6f);

            }

    }

    @Override
    public void onInitialApplication() {
        if(this.amount >= 5){
            if(HitMaxStacks == FALSE){
                CardCrawlGame.sound.play("SKUL_CHARGECAP", 0.6f);
                addToBot(new SelectCardsInHandAction(1, vars.Charge(), true, true, card -> card.type.equals(AbstractCard.CardType.ATTACK) && card.cost != -1 && card.cost != -2, ToExhaust -> {
                    for (AbstractCard c : ToExhaust) {
                        CardModifierManager.addModifier(c, new ChargeModifier());

                    }
                }));
                addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, Charge.POWER_ID));
                HitMaxStacks = TRUE;
            }
        }else{
            CardCrawlGame.sound.play("SKUL_CHARGEGAIN", 0.6f);
        }
        super.onInitialApplication();

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
HitMaxStacks = FALSE;
    }

    @Override
    public void onRemove() {
HitMaxStacks = FALSE;
    }

    @Override
    public void onCardDraw(AbstractCard card) {

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {

        //addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new Charge(owner, amount);
    }
}
