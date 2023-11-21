package skulmod.cards.Cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import skulmod.CustomTags;

import static skulmod.SkulMod.makeID;

public class ClownModifier extends AbstractCardModifier {
    private static boolean MadeClownGlow;
    public static final String ID = makeID("NonCardKeywords");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    @Override
    public String identifier(AbstractCard card) {
        return "ClownMod";
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        PurgeField.purge.set(card, true);
        if(card.cost != -1 && card.cost != -2){
            card.costForTurn = 0;
            card.cost = 0;
            card.isCostModified = true;
        }


    }


    @Override
    public boolean isInherent(AbstractCard card) {
        if(card.hasTag(CustomTags.GeneratedCard)){
            return true;
        }
        return super.isInherent(card);
    }

    @Override
    public void onOtherCardPlayed(AbstractCard card, AbstractCard otherCard, CardGroup group) {
        if(AbstractDungeon.player.hand.contains(card)){
            AbstractDungeon.actionManager.addToTop(new ShowCardAndPoofAction(card));
            AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractDungeon.player.hand.removeCard(card);
                    isDone = true;
                }
            });
        }
        super.onOtherCardPlayed(card, otherCard, group);
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + rawDescription;
        return super.modifyDescription(rawDescription, card);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ClownModifier();
    }
}
