package skulmod.cards.skill.clown;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;

import static skulmod.SkulMod.makeID;

public class SurpriseKnives extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SurpriseKnives",
            0,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.UNCOMMON,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 0;
    private int ExhaustCount = 0;


    public SurpriseKnives() {
        super(cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true, false);
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

    public SurpriseKnives(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.drawPile.size() >= magicNumber){
            ExhaustCount = this.magicNumber;
        }else{
            if(AbstractDungeon.player.drawPile.size() == 0){
                ExhaustCount = 0;
            }else{
                ExhaustCount = AbstractDungeon.player.drawPile.size();
            }
        }
if(ExhaustCount != 0){
    for(int Loops = 0; Loops < ExhaustCount; Loops += 1){
        AbstractCard c = AbstractDungeon.player.drawPile.getNCardFromTop(Loops);
        addToBot(new ExhaustSpecificCardAction(AbstractDungeon.player.drawPile.getNCardFromTop(Loops), AbstractDungeon.player.drawPile, true));
if(c.cost > 0){
    addToBot(new GainEnergyAction(c.cost));
}
    }
}
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SurpriseKnives();
    }
}