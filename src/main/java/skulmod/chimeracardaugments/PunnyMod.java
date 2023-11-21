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
import skulmod.SkulMod;
import skulmod.util.CustomActions.SkullActions.BonepileAction;

import static skulmod.SkulMod.makeID;
import static skulmod.util.CustomActions.SkullActions.BonepileAction.ClownMakeImitation;
import static skulmod.util.CustomActions.SkullActions.ChooseASkull.ActiveSkull;

public class PunnyMod extends AbstractAugment {
    private static final CardStrings CARD_TEXT;
    public static final String ID = SkulMod.makeID(PunnyMod.class.getSimpleName());
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.RARE;
    }


    @Override
    public boolean validCard(AbstractCard card) {
        return (card.cost > 0 && !card.hasTag(CustomTags.Skull));
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.cost += 1;
        card.costForTurn = card.cost;
        super.onInitialApplication(card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new PunnyMod();
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
        return insertAfterText(rawDescription, String.format(CARD_TEXT.EXTENDED_DESCRIPTION[2], 2));
    }

    @Override
    public float modifyBaseDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {

        return super.modifyBaseDamage(damage, type, card, target);
    }

    @Override
    public float modifyBaseBlock(float block, AbstractCard card) {

        return super.modifyBaseBlock(block, card);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
            for (int Loops = 2; Loops > 0; Loops -= 1){
                ClownMakeImitation();
            }
        super.onUse(card, target, action);
    }




    static {
        CARD_TEXT = CardCrawlGame.languagePack.getCardStrings(makeID("NonCardKeywords"));
    }
}