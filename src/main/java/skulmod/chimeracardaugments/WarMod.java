package skulmod.chimeracardaugments;

import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import skulmod.SkulMod;
import skulmod.powers.custompowers.Charge;

import static skulmod.SkulMod.makeID;
import static skulmod.util.CustomActions.SkullActions.BonepileAction.ClownMakeImitation;

public class WarMod extends AbstractAugment {
    private static final CardStrings CARD_TEXT;
    public static final String ID = SkulMod.makeID(WarMod.class.getSimpleName());
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.RARE;
    }


    @Override
    public boolean validCard(AbstractCard card) {

        return !card.hasTag(CustomTags.Skull) && card.costForTurn > 0;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new WarMod();
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
        return insertAfterText(rawDescription, String.format(CARD_TEXT.EXTENDED_DESCRIPTION[8] + card.costForTurn + CARD_TEXT.EXTENDED_DESCRIPTION[12], 2));
    }





    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Charge(AbstractDungeon.player, card.costForTurn)));
        super.onUse(card, target, action);
    }




    static {
        CARD_TEXT = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));
    }
}