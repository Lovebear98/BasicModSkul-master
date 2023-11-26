package skulmod.util.CustomActions.SkullActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import skulmod.powers.custompowers.skulls.GamblerPower;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class GambleAction extends AbstractGameAction {

    private AbstractPlayer p;



    public GambleAction() {
        this.setValues(this.p, AbstractDungeon.player, 1);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        int procchance = AbstractDungeon.cardRandomRng.random(100);
        int effect = AbstractDungeon.cardRandomRng.random(2);
        if(GamblerPower.DoubleOrNothing = FALSE){
            if(procchance <= 19){
                CardCrawlGame.sound.play("SKUL_GAMBLE", 0.6f);
                if(effect == 0){
                    addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new StrengthPower(AbstractDungeon.player, 1))));
                }
                if(effect == 1){
                    addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new DexterityPower(AbstractDungeon.player, 1))));
                }
                if(effect == 2){
                    addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new FocusPower(AbstractDungeon.player, 1))));
                }
            }else{
                addToBot(new FailedGambleAction(1));
            }
        }else{
            if(procchance <= 39){
                CardCrawlGame.sound.play("SKUL_GAMBLE", 0.6f);
                if(effect == 0){
                    addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new StrengthPower(AbstractDungeon.player, 1))));
                }
                if(effect == 1){
                    addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new DexterityPower(AbstractDungeon.player, 1))));
                }
                if(effect == 2){
                    addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new FocusPower(AbstractDungeon.player, 1))));
                }
            }else{
                addToBot(new FailedGambleAction(1));
            }
        }

        isDone = TRUE;
    }








}
