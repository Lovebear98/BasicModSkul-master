package skulmod.util.CustomActions.SkullActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import skulmod.relics.depreciated.DamageAltar;
import skulmod.relics.depreciated.HealthAltar;
import skulmod.relics.depreciated.SpeedAltar;

public class BoneFragmentsActions extends AbstractGameAction{



    public static AbstractGameAction LoseHealthAltar(AbstractPlayer player) {
        if(AbstractDungeon.player.hasRelic(HealthAltar.ID)){

        }
    return null;}
    public static AbstractGameAction LoseDamageAltar(AbstractPlayer player) {
        if(AbstractDungeon.player.hasRelic(DamageAltar.ID)){
            AbstractDungeon.player.loseRelic(DamageAltar.ID);
        }

        return null;}
    public static AbstractGameAction LoseSpeedAltar(AbstractPlayer player) {
        if(AbstractDungeon.player.hasRelic(SpeedAltar.ID)){
            AbstractDungeon.player.loseRelic(SpeedAltar.ID);
        }
        return null;}
    public static AbstractGameAction GetAltars(AbstractPlayer player){
        int index = AbstractDungeon.player.relics.size();
        if(!AbstractDungeon.player.hasRelic(HealthAltar.ID)){
            RelicLibrary.getRelic(HealthAltar.ID).makeCopy().instantObtain(AbstractDungeon.player, index, true);
        }
        if(!AbstractDungeon.player.hasRelic(DamageAltar.ID)){
            RelicLibrary.getRelic(DamageAltar.ID).makeCopy().instantObtain(AbstractDungeon.player, index+1, true);
        }
        if(!AbstractDungeon.player.hasRelic(SpeedAltar.ID)){
            RelicLibrary.getRelic(SpeedAltar.ID).makeCopy().instantObtain(AbstractDungeon.player, index+2, true);
        }
    return null;}

    public static AbstractGameAction GetHealthAltar(AbstractPlayer player){
        int index = AbstractDungeon.player.relics.size();
        if(!AbstractDungeon.player.hasRelic(HealthAltar.ID)){

        }

        return null;}
    public static AbstractGameAction GetDamageAltar(AbstractPlayer player){
        int index = AbstractDungeon.player.relics.size();
        if(!AbstractDungeon.player.hasRelic(DamageAltar.ID)){

        }

        return null;}
    public static AbstractGameAction GetSpeedAltar(AbstractPlayer player){
        int index = AbstractDungeon.player.relics.size();
        if(!AbstractDungeon.player.hasRelic(SpeedAltar.ID)){
            RelicLibrary.getRelic(SpeedAltar.ID).makeCopy().instantObtain(AbstractDungeon.player, index+2, true);

        }
        return null;}




    @Override
    public void update() {

    }
}
