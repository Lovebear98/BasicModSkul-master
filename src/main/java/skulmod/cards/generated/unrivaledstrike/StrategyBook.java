package skulmod.cards.generated.unrivaledstrike;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;

import static skulmod.SkulMod.makeID;

public class StrategyBook extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "StrategyBook",
            0,
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

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 0;



    public StrategyBook() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        cardsToPreview = new UnrivaledStrike();
        setSelfRetain(true);
        setExhaust(true);
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

    public StrategyBook(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new UnrivaledAction(this.upgraded, magicNumber));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new StrategyBook();
    }


    public static boolean HasUnrivaled(){
        for(AbstractCard c: AbstractDungeon.player.hand.group){
            if(c instanceof UnrivaledStrike){
                return true;
            }
        }
        return false;
    }
    public static AbstractCard UnrivaledInHand(){
        for(AbstractCard c: AbstractDungeon.player.hand.group){
            if(c instanceof UnrivaledStrike){
                return c;
            }
        }
        return null;
    }

}