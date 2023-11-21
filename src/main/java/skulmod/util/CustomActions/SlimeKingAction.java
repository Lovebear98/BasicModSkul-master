package skulmod.util.CustomActions;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import skulmod.CustomTags;
import skulmod.cards.Cardmods.SlimeMod;

import java.util.Iterator;

import static java.lang.Boolean.TRUE;

public class SlimeKingAction extends AbstractGameAction {
    private AbstractPlayer p;



    public SlimeKingAction(AbstractPlayer p) {
        this.p = p;
        this.setValues(this.p = AbstractDungeon.player, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FAST;

    }

    @Override
    public void update() {
    int index = MathUtils.random(0, AbstractDungeon.player.masterDeck.size() - 1);
    AbstractCard card = AbstractDungeon.player.masterDeck.group.get(index).makeCopy();
    if(card.hasTag(CustomTags.Skull)){
        boolean GotReplacement = false;
        if(index != AbstractDungeon.player.masterDeck.size()-1){
            if(!AbstractDungeon.player.masterDeck.group.get(index+1).hasTag(CustomTags.Skull)){
                AbstractCard card2 = AbstractDungeon.player.masterDeck.group.get(index+1).makeCopy();
                CardModifierManager.addModifier(card2, new SlimeMod());
                addToBot(new MakeTempCardInHandAction(card2));
                GotReplacement = true;
            }
        }
        if(index != AbstractDungeon.player.masterDeck.size()-1 && index != AbstractDungeon.player.masterDeck.size()-2 && !GotReplacement){
            if(!AbstractDungeon.player.masterDeck.group.get(index+2).hasTag(CustomTags.Skull)){
                AbstractCard card2 = AbstractDungeon.player.masterDeck.group.get(index+2).makeCopy();
                CardModifierManager.addModifier(card2, new SlimeMod());
                addToBot(new MakeTempCardInHandAction(card2));
                GotReplacement = true;
            }
        }
        if(index != 0 && !GotReplacement){
            if(!AbstractDungeon.player.masterDeck.group.get(index-1).hasTag(CustomTags.Skull)){
                AbstractCard card2 = AbstractDungeon.player.masterDeck.group.get(index-1).makeCopy();
                CardModifierManager.addModifier(card2, new SlimeMod());
                addToBot(new MakeTempCardInHandAction(card2));
                GotReplacement = true;
            }
        }
        if(index != 0 && index != 1 && !GotReplacement){
            if(!AbstractDungeon.player.masterDeck.group.get(index-2).hasTag(CustomTags.Skull)){
                AbstractCard card2 = AbstractDungeon.player.masterDeck.group.get(index-2).makeCopy();
                CardModifierManager.addModifier(card2, new SlimeMod());
                addToBot(new MakeTempCardInHandAction(card2));
                GotReplacement = true;
            }
        }


}else {
        CardModifierManager.addModifier(card, new SlimeMod());
        addToBot(new MakeTempCardInHandAction(card));
    }
        ///Due to how the hand updates, we chain actions so that the hand gets a chance to refresh
        ///before attempting to generate more cannonballs.

        isDone = TRUE;
    }

}

