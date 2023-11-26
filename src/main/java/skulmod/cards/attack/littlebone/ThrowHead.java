package skulmod.cards.attack.littlebone;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.BaseCard;
import skulmod.cards.generated.RecallHead;
import skulmod.character.LittleBone;
import skulmod.orbs.EmptyHead;
import skulmod.util.CardInfo;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class ThrowHead extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ThrowHead",
            0,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 3;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    private AbstractCard CardToMake;


    public ThrowHead() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        PurgeField.purge.set(this, true);
        cardsToPreview = new RecallHead();
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

    public ThrowHead(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    for(int Loops = magicNumber; Loops > 0; Loops -=1){
addToBot(new ChannelAction(new EmptyHead()));
    }
        CardToMake = new RecallHead();
        CardModifierManager.copyModifiers(this, CardToMake, false, false, false);
    if(this.upgraded == TRUE){
        AbstractCard thrh = CardToMake;
        thrh.upgrade();
        AbstractDungeon.player.discardPile.addToTop(thrh);
    }else{
    addToBot(new MakeTempCardInDiscardAction(CardToMake, false));
    }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ThrowHead();
    }
}