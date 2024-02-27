package skulmod.util.CustomActions.SkullActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import skulmod.CustomTags;

import java.util.Iterator;

import static skulmod.SkulMod.makeID;
import static skulmod.relics.depreciated.BoneFragments.FragmentGain;
import static skulmod.util.CustomActions.SkullActions.ChooseASkull.ActiveSkull;

public class GotSkullAction extends AbstractGameAction {
    ///Thank you, Aris. I spent more time on this than I would have liked or was probably healthy
    // before ending up finding your github showing exactly what I was trying to do.

    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("SkulUI"));
    private static String[] DESCRIPTIONS = uiStrings.TEXT;


    private AbstractPlayer p;
    private AbstractCard except;
    private int OwnedSkulls;

    public static AbstractCard NewestSkull;

    public GotSkullAction() {
        this.p = AbstractDungeon.player;
        this.except = except;
        this.setValues(this.p, AbstractDungeon.player, 1);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {

        OwnedSkulls = countSkullsInDeck(AbstractDungeon.player.masterDeck);
if(OwnedSkulls > 2){

    AbstractCard card;
    if (this.duration == Settings.ACTION_DUR_MED) {
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        CardGroup purgeable = getSkullsInDeck(AbstractDungeon.player.masterDeck);
        Iterator var5 = purgeable.group.iterator();

        while(var5.hasNext()) {
            AbstractCard c = (AbstractCard)var5.next();


            tmp.addToRandomSpot(c);

        }

        if (tmp.size() == 0) {
            this.isDone = true;
        } else if (tmp.size() == 1) {
            card = tmp.getTopCard();

            AbstractDungeon.effectList.add(new PurgeCardEffect(card));
            AbstractDungeon.player.masterDeck.removeCard(card);
            FragmentGain += AbstractDungeon.cardRandomRng.random(5, 23);

            this.isDone = true;
        } else {
            AbstractDungeon.gridSelectScreen.open(tmp, 1, DESCRIPTIONS[0], false);
            this.tickDuration();
        }
    } else {
        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            Iterator var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

            while(var1.hasNext()) {
                card = (AbstractCard) var1.next();
                AbstractDungeon.effectList.add(new PurgeCardEffect(card));
                AbstractDungeon.player.masterDeck.removeCard(card);
                FragmentGain += AbstractDungeon.cardRandomRng.random(3, 11);
            }


        }

        this.tickDuration();
    }
    AbstractDungeon.gridSelectScreen.selectedCards.clear();
}
this.tickDuration();
    }




    //Check how many card tagged Skull we currently own
    public static int countSkullsInDeck(CardGroup group) {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Iterator var2 = group.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.hasTag(CustomTags.Skull) && c.type == AbstractCard.CardType.POWER) {
                retVal.addToTop(c);
            }
        }

        return retVal.size();}
    //Get a list of all ards tagged Skull in the deck that aren't in a bottled tornado.
    public static CardGroup getSkullsInDeck(CardGroup group) {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Iterator var2 = group.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.hasTag(CustomTags.Skull) && !c.inBottleTornado && c.type == AbstractCard.CardType.POWER) {
                retVal.addToTop(c);
            }
        }

        return retVal;}


    public static CardGroup getInactiveSkull(CardGroup group) {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Iterator var2 = group.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (!(c.cardID == ActiveSkull.cardID)) {
                retVal.addToTop(c);
            }
        }

        return retVal;}

}