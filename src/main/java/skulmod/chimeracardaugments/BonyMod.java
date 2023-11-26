package skulmod.chimeracardaugments;

import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.CustomTags;
import skulmod.util.CustomActions.SkullActions.BonepileAction;

import static skulmod.SkulMod.makeID;
import static skulmod.util.CustomActions.SkullActions.ChooseASkull.ActiveSkull;

public class BonyMod extends AbstractAugment {
    public static final String ID = makeID(BonyMod.class.getSimpleName());
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));
    private int LostBlock;
    private int LostDamage;
    private int BonePileCount;
    private static final CardStrings CARD_TEXT;

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.COMMON;
    }


    @Override
    public boolean validCard(AbstractCard card) {
        //&& card.color == LittleBone.Enums.CARD_COLOR
        return ((card.baseDamage >= 4 || card.baseBlock >= 4) && !card.description.contains("Bonepile") && !card.hasTag(CustomTags.Skull) && card.cost >=  0);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        BonePileCount = (int) ((card.baseDamage + card.baseBlock) * 0.25f);
        LostDamage = (int) (card.baseDamage * 0.25f);
        LostBlock = (int) (card.baseBlock * 0.25f);
        super.onInitialApplication(card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new BonyMod();
    }

    @Override
    public String getPrefix() {
        return uiStrings.TEXT[0];
    }

    @Override
    public String getSuffix() {
        return uiStrings.TEXT[1];
    }
    @Override
    public String getAugmentDescription() {
        return uiStrings.TEXT[2];
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }


    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return insertAfterText(rawDescription, String.format(CARD_TEXT.EXTENDED_DESCRIPTION[1] + (LostBlock+LostDamage) + ".", 2));
    }

    @Override
    public float modifyBaseDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        damage -= LostDamage;
        return super.modifyBaseDamage(damage, type, card, target);
    }

    @Override
    public float modifyBaseBlock(float block, AbstractCard card) {
        block -= LostBlock;
        return super.modifyBaseBlock(block, card);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        for(int Loops = BonePileCount; Loops > 0; Loops -= 1){
            addToBot(new BonepileAction(AbstractDungeon.player, null, (AbstractMonster) target, ActiveSkull));
        }
        super.onUse(card, target, action);
    }



    static {
        CARD_TEXT = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));
    }
}