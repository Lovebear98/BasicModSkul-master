package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import skulmod.powers.BasePower;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class JackInTheBox extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("JackInTheBox");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;
    private boolean CausedOutside = TRUE;
    public static Boolean FirstDarkAttack = TRUE;
    private boolean PlayingAsX = false;
    private boolean LoseLater = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public JackInTheBox(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }






    @Override
    public void onInitialApplication() {
        super.onInitialApplication();

    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        super.onPlayCard(card, m);
        if(card.costForTurn != -1){
            int RepeatCost;
            if(card.costForTurn == 0 || card.costForTurn == -2){
                LoseLater = true;
                RepeatCost = 1;
            }else{
                RepeatCost = card.costForTurn;
            }


            if(EnergyPanel.totalCount-RepeatCost >= RepeatCost){
                int TimesToPlay = (EnergyPanel.totalCount-RepeatCost) / RepeatCost;
            if(AbstractDungeon.player.hasRelic(ChemicalX.ID)){
                TimesToPlay += 2;
            }
                int Loops;
                for(Loops = TimesToPlay; Loops > 0; Loops -= 1){
                addToTop(new AbstractGameAction() {
                    @Override
                    public void update() {
                        card.use(AbstractDungeon.player, m);
                        isDone = true;
                    }
                });
                addToBot(new LoseEnergyAction(RepeatCost));
            }
            }

            if(LoseLater){
                addToBot(new LoseEnergyAction(1));
                LoseLater = false;
            }
            addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, JackInTheBox.POWER_ID));


        }

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
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new JackInTheBox(owner, amount);
    }
}
