package skulmod.cards.generated;

import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.CustomTags;
import skulmod.cards.BaseCard;
import skulmod.cards.Cardmods.ClownModifier;
import skulmod.cards.attack.littlebone.ThrowHead;
import skulmod.character.LittleBone;
import skulmod.orbs.EmptyHead;
import skulmod.util.CardInfo;
import skulmod.util.CustomActions.Cardgen.MakeTempUpgradedCardinHandAction;

import javax.smartcardio.Card;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;
@NoCompendium
public class RecallHead extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "RecallHead",
            0,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.SPECIAL,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    private AbstractCard CardToMake;


    public RecallHead() {
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
        if (cardsToPreview != null){
            cardsToPreview.upgrade();
        }
        super.upgrade();
    }

    public RecallHead(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        for (int Loops = magicNumber; Loops > 0; Loops -= 1) {
            addToBot(new ChannelAction(new EmptyHead()));
        }
        CardToMake = new ThrowHead();
        CardModifierManager.copyModifiers(this, CardToMake, false, false, false);
        CardModifierManager.removeModifiersById(CardToMake, "ClownMod", true);
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
        return new RecallHead();
    }
}