package skulmod.chimeracardaugments;

import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import skulmod.CustomTags;

import static skulmod.SkulMod.makeID;

public class CrookMod extends AbstractAugment {
    public static final String ID = makeID(CrookMod.class.getSimpleName());
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));
    private static final CardStrings CARD_TEXT;
    private int StartingCost;

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.UNCOMMON;
    }


    @Override
    public boolean validCard(AbstractCard card) {
        return (!card.hasTag(CustomTags.Skull) && card.cost >= 1);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        StartingCost = card.cost;
        card.cost = 0;
        card.costForTurn = 0;
        super.onInitialApplication(card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new CrookMod();
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
    public boolean canPlayCard(AbstractCard card) {
        if(AbstractDungeon.player.gold >= (StartingCost * 12)) {
            return super.canPlayCard(card);
        }
        return false;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
            AbstractDungeon.player.loseGold(StartingCost * 12);
        super.onUse(card, target, action);
    }
    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return insertBeforeText(rawDescription, String.format(CARD_TEXT.EXTENDED_DESCRIPTION[5] + (StartingCost * 12) + CARD_TEXT.EXTENDED_DESCRIPTION[6], 2));
    }


    static {
        CARD_TEXT = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));
    }
}