package skulmod.cards.skill.davyjones;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;

import static skulmod.SkulMod.makeID;

public class EyeOfTheStorm extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "EyeOfTheStorm",
            1,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.RARE,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 3  ;

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 0;
    private int VigAmount;


    public EyeOfTheStorm() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);

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

    public EyeOfTheStorm(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.hasPower(VigorPower.POWER_ID)){
            VigAmount = AbstractDungeon.player.getPower(VigorPower.POWER_ID).amount;
        }else{
            VigAmount = 0;
        }
        addToBot(new GainBlockAction(p, this.block + VigAmount));
        addToBot(new RemoveSpecificPowerAction(p, p, VigorPower.POWER_ID));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new EyeOfTheStorm();
    }
}