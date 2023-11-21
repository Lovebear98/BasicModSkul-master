package skulmod.util.CustomActions.SkullActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import skulmod.CustomTags;
import skulmod.orbs.Cannonball;
import skulmod.orbs.CannonballPlus;

import java.util.Iterator;

public class GenerateCannonballAction extends AbstractGameAction {
    ///Thank you, Aris. I spent more time on this than I would have liked or was probably healthy
    // before ending up finding your github showing exactly what I was trying to do.

    private AbstractPlayer p;

    public static int CannonballsMade;

    public GenerateCannonballAction() {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, 1);
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
CannonballsMade += 1;
if(CannonballsMade >= 4){
    CannonballsMade -=4;
    addToBot(new ChannelAction(new CannonballPlus(), true));
}else{
    addToBot(new ChannelAction(new Cannonball(), true));
}
isDone = true;
    }
}