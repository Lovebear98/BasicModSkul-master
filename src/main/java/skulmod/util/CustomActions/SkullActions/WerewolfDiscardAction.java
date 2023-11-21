package skulmod.util.CustomActions.SkullActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class WerewolfDiscardAction extends AbstractGameAction {
    @Override
    public void update() {
            CardCrawlGame.sound.play("CARD_REJECT");
            AbstractDungeon.player.hand.moveToDiscardPile(AbstractDungeon.player.hand.getRandomCard(true));
            isDone = true;
    }
}
