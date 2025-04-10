package skulmod.util.skullutils;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.cards.generated.Cannonfire;

public class DavyUtils {
    public static boolean HaveFireInHand(){
        if(AbstractDungeon.player != null){
            for(AbstractCard c: AbstractDungeon.player.hand.group){
                if(c instanceof Cannonfire){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean HaveFireInDiscard(){
        if(AbstractDungeon.player != null){
            for(AbstractCard c: AbstractDungeon.player.discardPile.group){
                if(c instanceof Cannonfire){
                    return true;
                }
            }
        }
        return false;
    }
}
