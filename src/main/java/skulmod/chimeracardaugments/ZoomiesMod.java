package skulmod.chimeracardaugments;

import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import skulmod.CustomTags;
import skulmod.SkulMod;
import skulmod.powers.custompowers.Charge;
import skulmod.util.CustomActions.SkullActions.MillAction;

import static skulmod.SkulMod.makeID;

public class ZoomiesMod extends AbstractAugment {
    private static final CardStrings CARD_TEXT;
    public static final String ID = SkulMod.makeID(ZoomiesMod.class.getSimpleName());
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.COMMON;
    }


    @Override
    public boolean validCard(AbstractCard card) {
        return !card.hasTag(CustomTags.Skull) && card.cost > 0;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ZoomiesMod();
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
        return insertAfterText(rawDescription, String.format(CARD_TEXT.EXTENDED_DESCRIPTION[13], 2));
    }


    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
            addToBot(new MillAction(2));
            addToBot(new DrawCardAction(1));
        super.onUse(card, target, action);
    }




    static {
        CARD_TEXT = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));
    }
}