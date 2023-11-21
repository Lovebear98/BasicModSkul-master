package skulmod.util.CustomActions;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class DelayedAddCardModAction extends AbstractGameAction {
    private final AbstractCard card;
    private final AbstractCardModifier mod;

    public DelayedAddCardModAction(AbstractCard card, AbstractCardModifier mod) {
        this.card = card;
        this.mod = mod;
    }

    @Override
    public void update() {
        ///Apply this CardMod as an AbstractGameAction to prevent concurrent modification
        CardModifierManager.addModifier(card, mod);
        isDone = true;
    }
}
