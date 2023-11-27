package skulmod.util.CustomDynamicVariables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static skulmod.SkulMod.makeID;

public class VigorNum extends DynamicVariable
{
    @Override
    public String key()
    {
        return makeID("VIG");
        // What you put in your localization file between ! to show your value. Eg, !myKey!.
    }

    @Override
    public boolean isModified(AbstractCard card)
    {
        if(AbstractDungeon.player != null){
            if(AbstractDungeon.player.hasPower(VigorPower.POWER_ID)){
                return true;
            }
        }
        return false;
        // Set to true if the value is modified from the base value.
    }

    @Override
    public void setIsModified(AbstractCard card, boolean v)
    {

        // Do something such that isModified will return the value v.
        // This method is only necessary if you want smith upgrade previews to function correctly.
    }

    @Override
    public int value(AbstractCard card)
    {
        int VigAmount;
        if(AbstractDungeon.player != null){
            if(AbstractDungeon.player.hasPower(VigorPower.POWER_ID)){
                VigAmount = AbstractDungeon.player.getPower(VigorPower.POWER_ID).amount;
            }else{
                VigAmount = 0;
            }
        }else{
            VigAmount = 0;
        }
        return VigAmount;
        // What the dynamic variable will be set to on your card. Usually uses some kind of int you store on your card.
    }

    @Override
    public int baseValue(AbstractCard card)
    {
        int VigAmount;
        if(AbstractDungeon.player != null){

            if(AbstractDungeon.player.hasPower(VigorPower.POWER_ID)){
                VigAmount = AbstractDungeon.player.getPower(VigorPower.POWER_ID).amount;
            }else{
                VigAmount = 0;
            }
        }else{
            VigAmount = 0;
        }
        return VigAmount;
        // Should generally just be the above.
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return true;
        // Should return true if the card was upgraded and the value was changed
    }
}
