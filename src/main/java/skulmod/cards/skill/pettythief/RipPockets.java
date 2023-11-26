package skulmod.cards.skill.pettythief;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.powers.custompowers.RippedPocket;
import skulmod.util.CardInfo;

import static skulmod.SkulMod.makeID;

public class RipPockets extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "RipPockets",
            2,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.RARE,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = 8;

    private int GoldCost;
    private int BaseGoldCost = 25;
    private int UpgradedGoldCost = 15;


    public RipPockets() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);

    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
            return super.canUse(p, m);

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

    public RipPockets(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.gainGold(magicNumber);
        addToBot(new ApplyPowerAction(m, p, new RippedPocket(m, magicNumber)));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new RipPockets();
    }
}