package skulmod.cards.generated.unrivaledstrike;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.BaseCard;
import skulmod.cards.Cardmods.UnrivaledStrikeMod;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;
import skulmod.util.CustomActions.DelayedAddCardModAction;

import static skulmod.SkulMod.makeID;

public class ParasolCharge extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ParasolCharge",
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

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 3;



    public ParasolCharge() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void onChoseThisOption() {
        if(StrategyBook.HasUnrivaled()){
            AbstractCard c = StrategyBook.UnrivaledInHand();
            if(UnrivaledStrikeMod.IsBlock(c)){
                addToBot(new DelayedAddCardModAction(c, new UnrivaledStrikeMod(false, 0, magicNumber * 2, 0, 0)));
            }else{
                addToBot(new DelayedAddCardModAction(c, new UnrivaledStrikeMod(false, 0, magicNumber, 0, 0)));
            }
        }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

    public ParasolCharge(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ParasolCharge();
    }
}