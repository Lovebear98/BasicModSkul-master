package skulmod.cards.skill.pettythief;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.powers.custompowers.RippedPocket;
import skulmod.util.CardInfo;

import static java.lang.Boolean.FALSE;
import static skulmod.SkulMod.makeID;

public class Backroll extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Backroll",
            1,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 4;

    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 4;

    private int GoldCost;
    private int BaseGoldCost = 20;
    private int UpgradedGoldCost = 10;


    public Backroll() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        this.cantUseMessage = "I'm Broke!";

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

    public Backroll(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
AbstractDungeon.player.loseGold(GoldCost);
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new ApplyPowerAction(m, p, new RippedPocket(m, magicNumber)));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Backroll();
    }
}