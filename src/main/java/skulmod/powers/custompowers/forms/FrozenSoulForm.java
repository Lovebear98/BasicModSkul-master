package skulmod.powers.custompowers.forms;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;
import skulmod.powers.custompowers.Frozen;
import skulmod.util.CustomActions.SkullActions.GambleEnemyAction;
import skulmod.util.CustomActions.SkullActions.GenerateCannonballAction;

import java.util.Iterator;

import static skulmod.SkulMod.makeID;

public class FrozenSoulForm extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("FrozenSoulForm");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;
    private int BallGen;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public FrozenSoulForm(AbstractCreature owner, int amount) {
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
    public void atStartOfTurn() {
        addToBot(new ApplyPowerAction(owner, owner, new Frozen(owner, this.amount)));
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = (AbstractMonster)var3.next();
            if(!mo.isDeadOrEscaped() && !mo.isDying && !mo.isDead && !mo.halfDead){
                addToBot(new ApplyPowerAction(mo, owner, new Frozen(mo, this.amount)));
            }
        }
        super.atStartOfTurn();
    }

    @Override
    public void atStartOfTurnPostDraw() {

    }





    public void updateDescription() {
    this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new FrozenSoulForm(owner, amount);
    }



}
