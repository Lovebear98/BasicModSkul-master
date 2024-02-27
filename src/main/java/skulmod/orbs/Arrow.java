package skulmod.orbs;


import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.LockOnPower;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbPassiveEffect;

import java.util.Collections;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class Arrow extends CustomOrb {

    // Standard ID/Description
    public static final String ORB_ID = makeID("Arrow");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;

    private static final int PASSIVE_AMOUNT = 3;
    private static final int EVOKE_AMOUNT = 1;

    // Animation Rendering Numbers - You can leave these at default, or play around with them and see what they change.
    private float vfxTimer = 1.0f;
    private float vfxIntervalMin = 0.1f;
    private float vfxIntervalMax = 0.4f;
    private static final float ORB_WAVY_DIST = 0.04f;
    private static final float PI_4 = 12.566371f;
    private static int EvokedCannonballs;

    public Arrow() {
        // The passive/evoke description we pass in here, specifically, don't matter
        // You can ctrl+click on CustomOrb from the `extends CustomOrb` above.
        // You'll see below we override CustomOrb's updateDescription function with our own, and also, that's where the passiveDescription and evokeDescription
        // parameters are used. If your orb doesn't use any numbers/doesn't change e.g "Evoke: shuffle your draw pile."
        // then you don't need to override the update description method and can just pass in the parameters here.
        super(ORB_ID, orbString.NAME, PASSIVE_AMOUNT, EVOKE_AMOUNT, DESCRIPTIONS[1], DESCRIPTIONS[3], "skulmod/orbs/Arrow.png");

        updateDescription();

        angle = MathUtils.random(360.0f); // More Animation-related Numbers
        channelAnimTimer = 0.5f;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void updateDescription() { // Set the on-hover description of the orb
        applyFocus(); // Apply Focus (Look at the next method)
        description = DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1]+ passiveAmount + DESCRIPTIONS[2];
        {

        }
    }

    @Override
    public void applyFocus() {
        super.applyFocus();
    }


    @Override
    public void onStartOfTurn() {// 1.At the start of your turn.
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Arrow()));

    }

    @Override
    public void updateAnimation() {// You can totally leave this as is.
        // If you want to create a whole new orb effect - take a look at conspire's Water Orb. It includes a custom sound, too!
        super.updateAnimation();
        angle += Gdx.graphics.getDeltaTime() * 45.0f;
        vfxTimer -= Gdx.graphics.getDeltaTime();
        if (vfxTimer < 0.0f) {
            AbstractDungeon.effectList.add(new PlasmaOrbPassiveEffect(cX, cY)); // This is the purple-sparkles in the orb. You can change this to whatever fits your orb.
            vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
        }
    }

    // Render the orb.



    @Override
    public void triggerEvokeAnimation() { // The evoke animation of this orb is the dark-orb activation effect.
        AbstractDungeon.effectsQueue.add(new PlasmaOrbActivateEffect(cX, cY));

    }

    @Override
    public void playChannelSFX() { // When you channel this orb, the ATTACK_FIRE effect plays ("Fwoom").
        CardCrawlGame.sound.play("SKUL_ARROW_READY", 0.1f);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Arrow();
    }
@Override
    public void onEvoke() {
        ///Deal the arrow's damage
        AbstractDungeon.actionManager.addToBottom(new ArrowDamage());
        ///Then attempt to evoke another arrow
        AbstractDungeon.actionManager.addToBottom(new EvokeNextArrowAction());

}

    private class EvokeNextArrowAction extends AbstractGameAction {
        @Override
        public void update() {
            ///Check all our orbs
            for(AbstractOrb orb: AbstractDungeon.player.orbs){
                ///And if we find an arrow
                if(orb instanceof Arrow){
                    ///Play a sound
                    CardCrawlGame.sound.play("SKUL_ARROW_READY", 0.3f);
                    ///If the arrow hasn't already been fired
                    if(orb.evokeAmount != 0){
                        ///Mark the arrow as fired
                        orb.evokeAmount = 0;
                        ///Then evoke it
                        EvokeSpecificOrb(orb);
                    }
                    break;
                }
            }
            this.isDone = TRUE;
        }
    }

    private class ArrowDamage extends AbstractGameAction{
        @Override
        public void update() {
            ///If the arrow will actually deal damage
            if(passiveAmount > 0){
                ////Get a random ALIVE target
                AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                ///If we actually got a target
                if(mo != null && !mo.halfDead && !mo.isDeadOrEscaped() && !mo.isDying){
                    int damage;
                    ///Check if it has lock on
                    if(mo.hasPower(LockOnPower.POWER_ID)){
                        ///And if it does, adjust the dmaage accordingly
                        damage = (int)((float)passiveAmount * 1.5F);
                    }else{
                        damage = passiveAmount;
                    }
                    ///Then deal damage
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS)));
                    ///And play a sound
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("SKUL_ARROW")); // 3.And play a Jingle Sound.
                }
            }
            isDone = true;
        }
    }

    public static void EvokeSpecificOrb(AbstractOrb o){
        o.onEvoke();
        AbstractPlayer p = AbstractDungeon.player;
        ///If the player has orbs and the orb we passed in isn't an empty orb slot
        if (!p.orbs.isEmpty() && !(o instanceof EmptyOrbSlot)) {// 2819
            ///Make an orb that's an empty orb at that orb's location
            AbstractOrb orbSlot = new EmptyOrbSlot((o).cX, (o).cY);// 2820

            int i;
            ///This pushes the orbs forward in the list, but we don't need to do that
            for(i = 1; i < p.orbs.size(); ++i) {// 2822
               Collections.swap(p.orbs, i, i - 1);// 2823
            }
            ///We set that old orb to an empty orb
            p.orbs.set((p.orbs.indexOf(o)), orbSlot);// 2826
            ///Then we SetSlot on all orbs to position them
            for(i = 0; i < p.orbs.size(); ++i) {// 2828
                ((AbstractOrb)p.orbs.get(i)).setSlot(i, p.maxOrbs);// 2829
            }
        }
    }
    }





