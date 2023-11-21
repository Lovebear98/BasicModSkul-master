package skulmod.util.CustomActions.SkullActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.powers.custompowers.skulls.*;

import static java.lang.Boolean.TRUE;

public class ClearSkullPowers extends AbstractGameAction {

    private AbstractPlayer p;



    public ClearSkullPowers(AbstractPlayer p) {
        this.p = p;
        this.setValues(this.p, AbstractDungeon.player, 1);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        if(p.hasPower(AbsoluteSkullPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, AbsoluteSkullPower.POWER_ID));
        }
        if(p.hasPower(GodOfWarPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, GodOfWarPower.POWER_ID));
        }
        if(p.hasPower(DavyJonesPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, DavyJonesPower.POWER_ID));
        }
        if(p.hasPower(ChampionPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, ChampionPower.POWER_ID));
        }
        if(p.hasPower(LittleBonePower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, LittleBonePower.POWER_ID));
        }
        if(p.hasPower(GamblerPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, GamblerPower.POWER_ID));
        }
        if(p.hasPower(ShieldSkullPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, ShieldSkullPower.POWER_ID));
        }
        if(p.hasPower(WarriorSkullPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, WarriorSkullPower.POWER_ID));
        }
        if(p.hasPower(ClownPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, ClownPower.POWER_ID));
        }
        if(p.hasPower(MagePower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, MagePower.POWER_ID));
        }
        if(p.hasPower(PettyThiefPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, PettyThiefPower.POWER_ID));
        }
        if(p.hasPower(HunterPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, HunterPower.POWER_ID));
        }
        if(p.hasPower(DarkPaladinPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, DarkPaladinPower.POWER_ID));
        }
        if(p.hasPower(GreatWarlockPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, GreatWarlockPower.POWER_ID));
        }
        if(p.hasPower(BomberSkullPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, BomberSkullPower.POWER_ID));
        }
        if(p.hasPower(WerewolfPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, WerewolfPower.POWER_ID));
        }
        if(p.hasPower(FrostSkullPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(p,p, FrostSkullPower.POWER_ID));
        }






        isDone = TRUE;
    }








}
