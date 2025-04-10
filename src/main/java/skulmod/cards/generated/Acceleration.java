package skulmod.cards.generated;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

//@NoCompendium
public class Acceleration extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Acceleration",
            -2,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.SPECIAL,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    private Boolean DrawnOnce = false;



    public Acceleration() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        purgeOnUse = TRUE;



    }

    @Override
    public void update() {
        super.update();

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

    public Acceleration(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void triggerWhenDrawn() {
        addToBot(new DrawCardAction(magicNumber));
        if(DrawnOnce){
            addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        }else{
            DrawnOnce = true;
            addToBot(new DiscardSpecificCardAction(this, AbstractDungeon.player.hand));
                this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
                this.initializeDescription();
        }
        super.triggerWhenDrawn();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Acceleration();
    }
}