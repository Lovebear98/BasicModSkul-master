package skulmod.powers.custompowers.forms;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.CalmStance;
import com.megacrit.cardcrawl.stances.WrathStance;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import jdk.jfr.Threshold;
import jdk.nashorn.internal.ir.Block;
import skulmod.powers.BasePower;
import skulmod.powers.custompowers.LaterDexterityDown;
import skulmod.util.TextureLoader;

import static skulmod.SkulMod.makeID;

public class GuardianForm extends TwoAmountPower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("GuardianForm");

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("skulmod/powers/large/GuardianForm.png");
    private static final Texture tex32 = TextureLoader.getTexture("skulmod/powers/GuardianForm.png");
    private static final PowerType TYPE = PowerType.BUFF;

    private static final boolean TURN_BASED = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public GuardianForm(AbstractCreature owner, int amount) {

        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        amount2 = 50;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        this.updateDescription();
    }


    @Override
    public void atStartOfTurn() {


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
    public void onGainedBlock(float blockAmount) {
        boolean BlockThreshold = false;
        if(this.amount2 > blockAmount){
            this.amount2 -= blockAmount;
        }else{
            int Leftover = (int) (blockAmount - this.amount2);
            this.amount2 = 50 - Leftover;
            if(this.amount2 <= 0){
                this.amount2 = 1;
            }
            BlockThreshold = true;
        }

        if(BlockThreshold){
            boolean FoundTemp = false;
            int TempDex = 0;
            int LaterTempDex = 0;
            int AmountToLose = this.amount;
            int PassOff = 0;

            if(owner.hasPower(LoseDexterityPower.POWER_ID)){
                TempDex = owner.getPower(LoseDexterityPower.POWER_ID).amount;
            }
            if(owner.hasPower(LaterDexterityDown.POWER_ID)){
                LaterTempDex = owner.getPower(LaterDexterityDown.POWER_ID).amount;
            }


            if(AmountToLose > TempDex){
                PassOff = AmountToLose - TempDex;
                if(AbstractDungeon.player.hasPower(LoseDexterityPower.POWER_ID)){
                    addToBot(new RemoveSpecificPowerAction(owner, owner, LoseDexterityPower.POWER_ID));
                }
            }else{
                addToBot(new ApplyPowerAction(owner, owner, new LoseDexterityPower(owner, -AmountToLose)));
            }

            if(PassOff > LaterTempDex){
                if(AbstractDungeon.player.hasPower(LoseDexterityPower.POWER_ID)){
                    addToBot(new RemoveSpecificPowerAction(owner, owner, LaterDexterityDown.POWER_ID));
                }
            }else{
                addToBot(new ApplyPowerAction(owner, owner, new LaterDexterityDown(owner, -PassOff)));
            }



            addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, -this.amount)));
            addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, this.amount)));
            BlockThreshold = false;
        }
        updateDescription();
        super.onGainedBlock(blockAmount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);

    }



    @Override
    public void atStartOfTurnPostDraw() {

    }



    @Override
    public void onDeath() {
        super.onDeath();
    }




    @Override
    public void onRemove() {

    }

    @Override
    public void onCardDraw(AbstractCard card) {

    }


    public void updateDescription() {

    this.description = DESCRIPTIONS[0]+this.amount2+DESCRIPTIONS[1]+this.amount+DESCRIPTIONS[2];

    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new GuardianForm(owner, amount);
    }
}
