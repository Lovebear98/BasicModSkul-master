package skulmod.util.CustomActions.SkullActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import skulmod.powers.custompowers.skulls.GamblerPower;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class GambleEnemyAction extends AbstractGameAction {

    private final AbstractMonster mo;
    private int gambletimes;
    private AbstractPlayer p;



    public GambleEnemyAction(int gambleTimes, AbstractMonster mo) {
        this.setValues(this.p, AbstractDungeon.player, 1);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.gambletimes = gambleTimes;
        this.mo = mo;
    }

    public void update() {
        int procchance;
        int effect;

        int StrengthPlus = 0;
        int DexPlus = 0;
        int FocusPlus = 0;
        int Failed = 0;

        for(int Loops = gambletimes; Loops > 0; Loops -= 1){
            procchance = AbstractDungeon.cardRandomRng.random(100);
            if(procchance <= 19){
                effect = AbstractDungeon.cardRandomRng.random(2);
                if(effect == 0){
                    StrengthPlus += 1;
                }
                if(effect == 1){
                    DexPlus += 1;
                }
                if(effect == 2){
                    FocusPlus += 1;
                }
            }else{
                Failed += 1;
            }
        }
        if((StrengthPlus + DexPlus + FocusPlus) > 0){
            CardCrawlGame.sound.play("SKUL_GAMBLE", 0.6f);
        }

        if(StrengthPlus > 0){
            addToTop(new ApplyPowerAction(mo, AbstractDungeon.player, (new StrengthPower(mo, StrengthPlus))));
            if(AbstractDungeon.player.hasPower(GamblerPower.POWER_ID)){
                addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new StrengthPower(AbstractDungeon.player, StrengthPlus))));
            }
        }
        if(DexPlus > 0){
            addToTop(new ApplyPowerAction(mo, AbstractDungeon.player, (new DexterityPower(mo, DexPlus))));
            if(AbstractDungeon.player.hasPower(GamblerPower.POWER_ID)){
                addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new DexterityPower(AbstractDungeon.player, DexPlus))));
            }
        }
        if(FocusPlus > 0){
            addToTop(new ApplyPowerAction(mo, AbstractDungeon.player, (new FocusPower(mo, FocusPlus))));
            if(AbstractDungeon.player.hasPower(GamblerPower.POWER_ID)){
                addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new FocusPower(AbstractDungeon.player, FocusPlus))));
            }
        }
        if(Failed > 0){
            addToBot(new FailedGambleAction(Failed));
        }

        isDone = TRUE;
    }








}
