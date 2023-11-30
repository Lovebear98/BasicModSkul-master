package skulmod.powers.custompowers.skulls;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import skulmod.powers.BasePower;
import skulmod.powers.custompowers.Gunpowder;
import skulmod.util.CustomActions.SkullActions.ChooseASkull;

import java.util.Iterator;
import java.util.Objects;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class BomberSkullPower extends BasePower implements CloneablePowerInterface, OnReceivePowerPower {
    public static final String POWER_ID = makeID("BomberSkullPower");
    private static final PowerType TYPE = NeutralPowertypePatch.NEUTRAL;

    private static final boolean TURN_BASED = false;

    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public BomberSkullPower(AbstractCreature owner, int amount) {

        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.priority = 100;
    }


    @Override
    public void atStartOfTurn() {


    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new ChooseASkull(AbstractDungeon.player, TRUE));
    }


    //After playing a card
    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        super.onAfterUseCard(card, action);
        ///If we're not Entangled
        if(!owner.hasPower(EntanglePower.POWER_ID)){
            ///Try to Entangle us
            addToBot(new ApplyPowerAction(owner, owner, new EntanglePower(owner)));
        }
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        int GunpowerPlus = 0;
        if(owner.hasPower(StrengthPower.POWER_ID)){
            int StrengthPlus = owner.getPower(StrengthPower.POWER_ID).amount;
            if(StrengthPlus > 0){
                GunpowerPlus += StrengthPlus;
            }
            addToBot(new RemoveSpecificPowerAction(owner, owner, StrengthPower.POWER_ID));
        }
        if(owner.hasPower(VigorPower.POWER_ID)){
            int VigPlus = owner.getPower(VigorPower.POWER_ID).amount;
            if(VigPlus > 0){
                GunpowerPlus += VigPlus;
            }
            addToBot(new RemoveSpecificPowerAction(owner, owner, VigorPower.POWER_ID));
        }
        addToBot(new ApplyPowerAction(owner, owner, new Gunpowder(owner, GunpowerPlus)));
    }

    @Override
    public void onRemove() {
        super.onRemove();
        Iterator<AbstractMonster> var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = var3.next();
            if(!mo.isDead && !mo.halfDead && !mo.isDying && !mo.isDeadOrEscaped()){
                addToBot(new DamageAction(mo, new DamageInfo(owner, GunpowderNum(), DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }







    @Override
    public void onCardDraw(AbstractCard card) {

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+GunpowderNum()+DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new BomberSkullPower(owner, amount);
    }

    private static int GunpowderNum(){
        ///If we have Gunpowder
        if (AbstractDungeon.player.hasPower(Gunpowder.POWER_ID)) {
            ///Return its amount
            return AbstractDungeon.player.getPower(Gunpowder.POWER_ID).amount;
        }
        ///Otherwise return 1
        return 1;
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        if(Objects.equals(abstractPower.ID, StrengthPower.POWER_ID) || Objects.equals(abstractPower.ID, VigorPower.POWER_ID)){
        if(abstractPower.amount > 0){
            addToBot(new ApplyPowerAction(abstractCreature, abstractCreature1, new Gunpowder(abstractCreature, abstractPower.amount)));
        }
            return false;
        }
        return true;
    }
}
