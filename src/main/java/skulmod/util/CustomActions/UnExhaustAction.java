package skulmod.util.CustomActions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UnExhaustAction extends AbstractGameAction
{
    private AbstractPlayer p;
    private AbstractCard c;

    public UnExhaustAction(final AbstractCard c) {
        this.c = c;
        this.setValues(this.p = AbstractDungeon.player, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.p.hand.size() >= BaseMod.MAX_HAND_SIZE) {
            AbstractDungeon.player.createHandIsFullDialog();
            this.isDone = true;
            return;
        }
        if (this.p.exhaustPile.isEmpty()) {
            this.isDone = true;
            return;
        }
        if (this.p.exhaustPile.contains(c)) {
            c.unfadeOut();
            this.p.discardPile.addToTop(c);
            if (AbstractDungeon.player.hasPower("Corruption") && c.type == AbstractCard.CardType.SKILL) {
                c.setCostForTurn(-9);
            }
            this.p.exhaustPile.removeCard(c);
            c.unhover();
            c.fadingOut = false;
        }
        this.isDone = true;
    }
}

