package skulmod.cards.generated.unrivaledstrike;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.SkulMod;

import java.util.ArrayList;
import java.util.Objects;

public class UnrivaledAction extends AbstractGameAction {
    private int i;
    private final boolean UpgradeCards;


    public UnrivaledAction(boolean upgraded, int i) {
        this.UpgradeCards = upgraded;
        this.i = i;

    }

    @Override
    public void update() {
        ///If the player doesn't already have an Unrivaled Strike in hand
        if(!StrategyBook.HasUnrivaled()){
            ///Add one instead of anything else
            addToTop(new MakeTempCardInHandAction(new UnrivaledStrike()));
            ///Otherwise
        }else{
            ///If we're passed a bigger magic number than our tactic list's cap
            if(i > SkulMod.TacticsList.size()){
                ///Cap it at the list size
                i = SkulMod.TacticsList.size();
            }
            ///Make a blank list
            ArrayList<AbstractCard> BookList = new ArrayList<AbstractCard>();
            do{
                ///Get a random number, bounded by our tactics list size
                int RandNum = AbstractDungeon.cardRandomRng.random(SkulMod.TacticsList.size() - 1);
                ///Use that random number to pull a card from the list
                AbstractCard RandCard = SkulMod.TacticsList.get(RandNum);
                ///If our BookList doesn't already contain that card
                if(NotInList(BookList, RandCard)){
                    ///Add it to it
                    BookList.add(RandCard.makeCopy());
                }
                ///Repeat this until our BookList is equal to i
            }while(BookList.size() < i);
            ///if we're told to upgrade
            if (UpgradeCards) {
                ///Upgrade all cards in the group
            for(AbstractCard c: BookList){
                c.upgrade();
            }}

            ///Then choose a card from the list.
            this.addToBot(new ChooseOneAction(BookList));
        }



        isDone = true;
    }

    private boolean NotInList(ArrayList<AbstractCard> bookList, AbstractCard randCard) {
        for(AbstractCard c : bookList){
            if(Objects.equals(c.cardID, randCard.cardID)){
                return false;
            }
        }
        return true;
    }
}
