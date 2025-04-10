package skulmod.util.CustomActions.SkullActions;

import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.cards.Cardmods.CannonballMod;
import skulmod.cards.generated.Cannonfire;

public class SetUpCannonball extends AbstractGameAction {

    private int i;
    private AbstractPlayer p;
    private boolean Upgrade;

    private boolean BuffedACannonball;



    public SetUpCannonball(AbstractPlayer p, boolean b) {
        this.i = i;
        this.p = p;
        this.Upgrade = b;
        this.setValues(this.p = AbstractDungeon.player, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FAST;

    }








    @Override
    public void update() {
        ///Perform the cannonball making process
        MakeACannonball();
        this.isDone = true;
    }

    void MakeACannonball(){
        AbstractCard c;
        if(CannonballInHand()){
            c = GetCannonball();
            if(Upgrade && !c.upgraded){
                c.upgrade();
            }else{
                CardModifierManager.addModifier(c, new CannonballMod());
            }
        }else{
            if(AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE){
                c = new Cannonfire();
                if(Upgrade){
                    c.upgrade();
                    addToTop(new MakeTempCardInHandAction(c));
                }else{
                    addToTop(new MakeTempCardInHandAction(c));
                }
            }
        }
    }

    boolean CannonballInHand(){
        ///For each card in our hand
        for(AbstractCard c : AbstractDungeon.player.hand.group){
            ///If that card is a cannonfire
            if(c instanceof Cannonfire){
                ///Return true
                return true;
            }
        }
        return false;
    }
    AbstractCard GetCannonball(){
        for(AbstractCard c : AbstractDungeon.player.hand.group){
            ///If that card is a cannonfire
            if(c instanceof Cannonfire){
                ///Return that cannonfire
                return c;
            }
        }
        ///Otherwise return null
        return null;
    }



}
