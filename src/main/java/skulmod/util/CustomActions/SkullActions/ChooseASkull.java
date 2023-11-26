package skulmod.util.CustomActions.SkullActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.cards.power.SkullPowers.BomberSkull;
import skulmod.cards.power.SkullPowers.LittleBoneSkull;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static skulmod.util.CustomActions.SkullActions.GotSkullAction.countSkullsInDeck;
import static skulmod.util.CustomActions.SkullActions.GotSkullAction.getSkullsInDeck;

public class ChooseASkull extends AbstractGameAction {

    private final Boolean Swapping;
    private AbstractPlayer p;

    public static AbstractCard ActiveSkull;


    public ChooseASkull(AbstractPlayer p, Boolean Swapping) {
        this.Swapping = Swapping;
        this.p = p;
        this.setValues(this.p, AbstractDungeon.player, 1);
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        int OwnedSkulls = countSkullsInDeck(AbstractDungeon.player.masterDeck);
        if(OwnedSkulls != 0){
if(Swapping == FALSE){
    CardGroup skulls = getSkullsInDeck(AbstractDungeon.player.masterDeck);
    if(skulls.group.isEmpty()){
        skulls.group.add(new LittleBoneSkull());
    }
    ArrayList<AbstractCard> skullChoices = new ArrayList();
    AbstractCard Skull1 = skulls.group.get(0);
    if(skulls.size() == 1){
        Skull1.onChoseThisOption();
    }else{
        skullChoices.add(Skull1.makeCopy());
        AbstractCard Skull2 = skulls.group.get(1);
        skullChoices.add(Skull2.makeCopy());
        if(Skull1.cardID.equals(Skull2.cardID)){
            Skull1.onChoseThisOption();
        }else{
            this.addToBot(new ChooseOneAction(skullChoices));
        }

    }
}else {


    CardGroup skulls = getSkullsInDeck(AbstractDungeon.player.masterDeck);
    CardGroup inactive = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        if(ActiveSkull == null){
            ActiveSkull = new LittleBoneSkull();
        }
    skulls.group.forEach(item -> {
        if (!item.cardID.equals(ActiveSkull.cardID)) {
            inactive.addToTop(item);
            ActiveSkull = item;
        }
    });


    if (inactive.size() == 0) {

        AbstractCard Skull1 = new BomberSkull();
        addToBot(new ClearSkullPowers(p));
        Skull1.onChoseThisOption();
        addToBot(new SwapAction(ActiveSkull));
    } else {
        AbstractCard Skull1 = inactive.group.get(0);
        addToBot(new ClearSkullPowers(p));
        Skull1.onChoseThisOption();
        addToBot(new SwapAction(ActiveSkull));
    }
}










}




        isDone = TRUE;
    }








}
