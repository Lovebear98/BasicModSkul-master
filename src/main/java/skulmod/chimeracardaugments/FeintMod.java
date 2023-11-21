package skulmod.chimeracardaugments;

import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
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

public class FeintMod extends AbstractAugment {
    public static final String ID = makeID(FeintMod.class.getSimpleName());
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));
    private static final CardStrings CARD_TEXT;

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.UNCOMMON;
    }


    @Override
    public boolean validCard(AbstractCard card) {
        return ((card.baseBlock > 0) && !card.hasTag(CustomTags.Skull));
    }

    @Override
    public void onInitialApplication(AbstractCard card) {

        super.onInitialApplication(card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new FeintMod();
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
        return insertAfterText(rawDescription, String.format(CARD_TEXT.EXTENDED_DESCRIPTION[7], 2));
    }





    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        if(AbstractDungeon.player.currentBlock == 0){
            addToBot(new GainEnergyAction(1));
        }
        super.onUse(card, target, action);
    }



    static {
        CARD_TEXT = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));
    }
}