package skulmod.cards.Cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import skulmod.CustomTags;

import static skulmod.SkulMod.makeID;

public class ClownModifier extends AbstractCardModifier {
    private static boolean MadeClownGlow;
    public static final String ID = makeID("NonCardKeywords");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private boolean LockCard = false;

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

        ///When we play a card
    @Override
    public void onOtherCardPlayed(AbstractCard card, AbstractCard otherCard, CardGroup group) {
        ///Delay this action in order to make it handcheck more accurately
        AbstractDungeon.actionManager.addToBottom(new ImitationRemovalAction(card));
        super.onOtherCardPlayed(card, otherCard, group);
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        ///Lock the card based on whether it's locked or not
        return !LockCard;
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

    private class ImitationRemovalAction extends AbstractGameAction {
        private final AbstractCard card;

        public ImitationRemovalAction(AbstractCard card) {
            this.card = card;
        }

        @Override
        public void update() {
            ///If this is in our hand
            if(AbstractDungeon.player.hand.contains(card)){
                ///Lock it so it can't be played
                LockCard = true;
                ///Play the exhaust animation
                AbstractDungeon.actionManager.addToBottom(new ShowCardAndPoofAction(card));
                ///Then if it's in the hand, remove it
                AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if(AbstractDungeon.player.hand.contains(card)){
                            AbstractDungeon.player.hand.removeCard(card);
                        }
                        isDone = true;
                    }
                });
            }
            ///Then ALWAYS unlock it to make sure it's only locked during the queue resolving
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    LockCard = false;
                    isDone = true;
                }
            });
            isDone = true;
        }
    }
}
