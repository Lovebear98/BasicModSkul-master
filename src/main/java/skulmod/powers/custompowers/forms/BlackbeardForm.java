package skulmod.powers.custompowers.forms;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;
import skulmod.util.CustomActions.SkullActions.GenerateCannonballAction;

import java.util.Iterator;

import static skulmod.SkulMod.makeID;

public class BlackbeardForm extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("BlackbeardForm");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;
    private int BallGen;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public BlackbeardForm(AbstractCreature owner, int amount) {
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



    public void atEndOfTurn(boolean isPlayer) {
        AbstractPlayer p = AbstractDungeon.player;
        BallGen = 0;
        if (isPlayer) {
            Iterator var2 = AbstractDungeon.player.hand.group.iterator();

            while(var2.hasNext()) {
                AbstractCard c = (AbstractCard)var2.next();
                if (!c.isEthereal && c.type == AbstractCard.CardType.ATTACK) {
                    c.retain = true;
                    BallGen += 1;
                }
            }
        }
        for(int Loops = BallGen; Loops >0; Loops -= 1){
            addToBot(new GenerateCannonballAction());
        }

    }

    public void updateDescription() {
    this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new BlackbeardForm(owner, amount);
    }



}
