package skulmod.cards.generated.unrivaledstrike;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import skulmod.cards.BaseCard;
import skulmod.cards.Cardmods.UnrivaledStrikeMod;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;

import static skulmod.SkulMod.makeID;

public class MatterOfState extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "MatterOfState",
            -2,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.SPECIAL,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 5;

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 0;



    public MatterOfState() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        if(StrategyBook.HasUnrivaled()){
            AbstractCard c = StrategyBook.UnrivaledInHand();
            addToBot(new DiscardSpecificCardAction(c));
            if(UnrivaledStrikeMod.IsBlock(c)){
                addToBot(new GainBlockAction(p, block));
                addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, block)));
            }else{
                addToBot(new GainBlockAction(p, block));
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

    public MatterOfState(CardInfo cardInfo) {
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
        return new MatterOfState();
    }
}