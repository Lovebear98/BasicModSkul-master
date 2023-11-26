package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import skulmod.powers.custompowers.forms.AbyssForm;
import skulmod.util.TextureLoader;

import static java.lang.Boolean.FALSE;
import static skulmod.SkulMod.makeID;

public class Concentration extends TwoAmountPower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("Concentration");
    private static final PowerType TYPE = PowerType.DEBUFF;

    private static final boolean TURN_BASED = false;
    private Boolean HitMaxStacks = FALSE;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("skulmod/powers/large/Concentration.png");
    private static final Texture tex32 = TextureLoader.getTexture("skulmod/powers/Concentration.png");
    public static Color myColor = new Color(0.529F, 0.922F, 0, 1);
    public static Color myColor2 = new Color(0.529F, 0.922F, 0.7f, 1);
    public static boolean WarlockSwap = FALSE;

    public Concentration(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        amount2 = 0;
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
        this.amount2 = 0;
        super.onInitialApplication();
        if (this.amount < 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
            if(stackAmount < 0){
                int SpellNum;
                AbstractGameAction.AttackEffect ConcEffect;
                if(!owner.isDeadOrEscaped() && !owner.isDying && !owner.isDead && !owner.halfDead){
                    SpellNum = MathUtils.random(1);
                    if(SpellNum == 1){
                        ConcEffect = AbstractGameAction.AttackEffect.POISON;
                    }else{
                        ConcEffect = AbstractGameAction.AttackEffect.FIRE;
                    }
                addToBot(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, stackAmount * -1, DamageInfo.DamageType.THORNS), ConcEffect));
                    this.amount2 += stackAmount * -1;
            }
            }


        if (this.amount <= 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);

    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        super.onAfterCardPlayed(usedCard);
    }

    @Override
    public void atStartOfTurnPostDraw() {



    }

    public static Color getMyColor() {
        return myColor.cpy();
    }


    @Override
    public void onVictory() {
        amount2 = 0;
    }

    @Override
    public void onRemove() {
if(this.amount2 > 0){
    if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
        RemovedAction();
        if(AbstractDungeon.player.hasPower(AbyssForm.POWER_ID)){
            for(int Loops = AbstractDungeon.player.getPower(AbyssForm.POWER_ID).amount; Loops > 0; Loops -= 1){
                RemovedAction();
            }
        }

    }
}
        this.amount2 = 0;
    }

    private void RemovedAction() {
        int SpellNum;
        AbstractGameAction.AttackEffect ConcEffect;
        SpellNum = MathUtils.random(1);
        if(SpellNum == 1){
            ConcEffect = AbstractGameAction.AttackEffect.POISON;
        }else{
            ConcEffect = AbstractGameAction.AttackEffect.FIRE;
        }

        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.amount2, DamageInfo.DamageType.THORNS, ConcEffect));
    }

    @Override
    public void onCardDraw(AbstractCard card) {

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        WarlockSwap = FALSE;

        //addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]+this.amount2+DESCRIPTIONS[2];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new Concentration(owner, amount);
    }
}
