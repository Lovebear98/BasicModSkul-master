package skulmod.cards.attack.mage;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.powers.custompowers.Burn;
import skulmod.util.CardInfo;

import static skulmod.SkulMod.makeID;

public class FireArrow extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "FireArrow",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 1;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;



    public FireArrow() {
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

    public FireArrow(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        if(m.hasPower(Burn.POWER_ID)){
            int TargetBurn = m.getPower(Burn.POWER_ID).amount/2;
            addToBot(new ApplyPowerAction(p, p, new VigorPower(p, TargetBurn)));
            addToBot(new RemoveSpecificPowerAction(m, p, Burn.POWER_ID));
        }else{
            addToBot(new ApplyPowerAction(m, p, new Burn(m, magicNumber)));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FireArrow();
    }
}