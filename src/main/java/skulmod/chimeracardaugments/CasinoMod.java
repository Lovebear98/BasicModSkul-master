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
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.CustomTags;
import skulmod.util.CustomActions.SkullActions.BonepileAction;
import skulmod.util.CustomActions.SkullActions.GambleAction;

import static skulmod.SkulMod.makeID;
import static skulmod.util.CustomActions.SkullActions.ChooseASkull.ActiveSkull;

public class CasinoMod extends AbstractAugment {
    public static final String ID = makeID(CasinoMod.class.getSimpleName());
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));

    private static final CardStrings CARD_TEXT;
    private int GambleNum;

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.COMMON;
    }


    @Override
    public boolean validCard(AbstractCard card) {
        return (!card.hasTag(CustomTags.Skull) && card.cost >= 0);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        card.cost += 1;
        card.costForTurn += 1;
        GambleNum = 2 * card.cost;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new CasinoMod();
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
        return insertAfterText(rawDescription, String.format(CARD_TEXT.EXTENDED_DESCRIPTION[3] + GambleNum + CARD_TEXT.EXTENDED_DESCRIPTION[4], 2));
    }


    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        for(int Loops = GambleNum; Loops > 0; Loops -= 1){
            addToBot(new GambleAction());
        }
    }




    static {
        CARD_TEXT = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));
    }
}