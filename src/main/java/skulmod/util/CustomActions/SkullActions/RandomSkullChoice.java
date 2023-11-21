package skulmod.util.CustomActions.SkullActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.HashMap;

import static skulmod.CustomTags.Skull;
import static skulmod.SkulMod.returnStarterSkull;

public class RandomSkullChoice extends AbstractGameAction {
    ///Thank you, Aris. I spent more time on this than I would have liked or was probably healthy
    // before ending up finding your github showing exactly what I was trying to do.

    private AbstractPlayer p;

    public static int CannonballsMade;
    public static Boolean RemovedBane;
    private AbstractCard CardGot;

    public RandomSkullChoice() {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, 1);
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_MED;
    }
    private static HashMap<AbstractCard.CardTags, ArrayList<AbstractCard>> tagsWithLists = new HashMap<>();
    public void update() {
        AbstractCard card = returnStarterSkull(Skull);
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(card, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        isDone = true;  
    }
    }
