package skulmod.cards.generated;

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
import skulmod.CustomTags;
import skulmod.cards.BaseCard;
import skulmod.cards.attack.greatwarlock.AbyssOrb;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;
import skulmod.util.CustomActions.Cardgen.MakeTempUpgradedCardinHandAction;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

//@NoCompendium
public class AbyssSpike extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "AbyssSpike",
            0,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.SPECIAL,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;
    private AbstractCard CardToMake;


    public AbyssSpike() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        PurgeField.purge.set(this, true);
        tags.add(CustomTags.GeneratedCard);


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

    public AbyssSpike(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
        CardToMake = new AbyssOrb();
        CardModifierManager.copyModifiers(this, CardToMake, false, false, false);
        if (this.upgraded == TRUE) {
            addToBot(new MakeTempUpgradedCardinHandAction(CardToMake, 1));
        }else{
            addToBot(new MakeTempCardInHandAction(CardToMake));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new AbyssSpike();
    }
}