package skulmod.powers.custompowers.forms;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import skulmod.powers.BasePower;
import skulmod.util.CustomActions.SkullActions.GambleAction;
import skulmod.util.CustomActions.SkullActions.GambleEnemyAction;

import java.util.Iterator;

import static skulmod.SkulMod.makeID;

public class AllInForm extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("AllInForm");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public AllInForm(AbstractCreature owner, int amount) {
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
    public void atStartOfTurn() {
        super.atStartOfTurn();
if(this.amount > 0){
    for(int Loops = this.amount; Loops > 0; Loops -= 1){
        addToBot(new GambleAction());
    }

}
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

            AbstractMonster mo;
            while(var3.hasNext()) {
                mo = (AbstractMonster)var3.next();
                if(!mo.isDeadOrEscaped() && !mo.isDying && !mo.isDead && !mo.halfDead){
                    addToBot(new GambleEnemyAction(this.amount, mo));
                }
            }
        }

    }

    public void updateDescription() {
    this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new AllInForm(owner, amount);
    }



}
