package skulmod.cards.attack.greatwarlock;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.BaseCard;
import skulmod.cards.generated.AbyssSpike;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;
import skulmod.util.CustomActions.Cardgen.MakeTempUpgradedCardinHandAction;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class AbyssOrb extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "AbyssOrb",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 0;
    private AbstractCard CardToMake;


    public AbyssOrb() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        setEthereal(true);
        PurgeField.purge.set(this, true);
        this.cardsToPreview = new AbyssSpike();


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
        if (cardsToPreview != null){
            cardsToPreview.upgrade();
        }
        super.upgrade();
    }

    public AbyssOrb(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
        CardToMake = new AbyssSpike();
        CardModifierManager.copyModifiers(this, CardToMake, true, false, false);
        if (this.upgraded == TRUE) {
            addToBot(new MakeTempUpgradedCardinHandAction(CardToMake, 1));
        }else{
            addToBot(new MakeTempCardInHandAction(CardToMake));
        }
        CardToMake = null;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new AbyssOrb();
    }
}