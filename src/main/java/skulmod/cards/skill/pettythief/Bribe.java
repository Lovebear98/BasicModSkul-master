package skulmod.cards.skill.pettythief;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;
import skulmod.util.CustomActions.SkullActions.BonepileAction;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;
import static skulmod.util.CustomActions.SkullActions.ChooseASkull.ActiveSkull;

public class Bribe extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Bribe",
            0,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.COMMON,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 0;

    private int GoldCost;
    private int BaseGoldCost = 20;
    private int UpgradedGoldCost = 10;


    public Bribe() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
        this.cantUseMessage = "I'm Broke!";
    }

    @Override
    public void update() {
        super.update();

    }

    @Override
    public void applyPowers() {
        if(this.upgraded == FALSE){
            GoldCost = BaseGoldCost;
        } else {
            this.GoldCost = UpgradedGoldCost;
        }
        super.applyPowers();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

    public Bribe(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.gold >= GoldCost){
            return super.canUse(p, m);
        }else {

            return false;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.loseGold(GoldCost);
        for(int Loops = magicNumber; Loops > 0; Loops -= 1)
        {
            addToBot(new BonepileAction(p, this, m, ActiveSkull));
        }
        addToBot(new DrawCardAction(1));

    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Bribe();
    }
}