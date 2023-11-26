package skulmod.util.CustomActions.SkullActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static java.lang.Boolean.TRUE;

public class CannonballEvokeAction extends AbstractGameAction {
    private int i;
    private AbstractPlayer p;
    private boolean Upgrade;



    public CannonballEvokeAction(AbstractPlayer p, int i, boolean b) {
        this.i = i;
        this.p = p;
        this.Upgrade = b;
        this.setValues(this.p = AbstractDungeon.player, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FAST;

    }

    @Override
    public void update() {
        ///Due to how the hand updates, we chain actions so that the hand gets a chance to refresh
        ///before attempting to generate more cannonballs.
for(int Loops = i; Loops > 0; Loops -= 1){
addToBot(new SetUpCannonball(p, Upgrade));
}
        isDone = TRUE;
    }

}
