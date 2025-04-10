package skulmod.util.othermodskulls.unchained.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import theUnchainedMod.patches.RelayHelpers;

import static skulmod.SkulMod.LockRelay;
import static skulmod.SkulMod.makeID;


public class RidingStance extends AbstractStance {
    public static final String STANCE_ID = makeID("Riding");
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static long sfxId = -1L;

    private float HoldMult = 0;

    public RidingStance() {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;
        this.updateDescription();
    }

    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.05F;
                AbstractDungeon.effectsQueue.add(new CombustParticleEffect());

            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.3F, 0.4F);
            AbstractDungeon.effectsQueue.add(new CombustAuraEffect());
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        damage *= (1 + DamageRatio());
        return super.atDamageGive(damage, type);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        damage *= (1 + DamageRatio());
        return super.atDamageReceive(damage, damageType);
    }

    public void onEnterStance() {
        if (sfxId != -1L) {
            this.stopIdleSfx();
        }

        CardCrawlGame.sound.play("SKUL_BIKE_START");
        sfxId = CardCrawlGame.sound.playAndLoop("SKUL_BIKE_LOOP");
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.TEAL, true));
        updateDescription();
    }

    public void onExitStance() {
        this.stopIdleSfx();
        CardCrawlGame.sound.play("SKUL_BIKE_END");
    }

    public void stopIdleSfx() {
        if (sfxId != -1L) {
            CardCrawlGame.sound.stop("SKUL_BIKE_LOOP", sfxId);
            sfxId = -1L;
        }

    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        updateDescription();
    }

    public void updateDescription() {

        this.description = stanceString.DESCRIPTION[0]+(int)(DamageRatio() * 100)+ stanceString.DESCRIPTION[1];
    }

    private float DamageRatio(){

        ///We only bother doing something with this if our turn hasn't been queue'd ended, so that intents don't change based on end of turn damage.
        if(!AbstractDungeon.player.endTurnQueued && !AbstractDungeon.player.isEndingTurn && !AbstractDungeon.actionManager.turnHasEnded && !LockRelay){
            int i = 1;
            int HP = 1;

            HP = Math.max(1, AbstractDungeon.player.currentHealth);
            i = RelayHelpers.nextTurnRelayedDamage.get(AbstractDungeon.player) + RelayHelpers.thisTurnRelayedDamage.get(AbstractDungeon.player);
            HoldMult = ((float)i/(float)HP);
        }

        return HoldMult;
    }
}