package skulmod.powers.custompowers;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnPlayerDeathPower;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.purple.Blasphemy;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skulmod.powers.BasePower;
import skulmod.powers.custompowers.skulls.AbsoluteSkullPower;
import skulmod.util.BerserkerAttackAction;

import static skulmod.SkulMod.makeID;

public class BoneScreamPower extends BasePower implements CloneablePowerInterface, OnPlayerDeathPower {
    public static final String POWER_ID = makeID("BoneScreamPower");
    private static final PowerType TYPE = NeutralPowertypePatch.NEUTRAL;

    private static final boolean TURN_BASED = false;


    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public BoneScreamPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

///This amount will always be -1
    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        this.amount = -1;
    }
    @Override
    public void stackPower(int stackAmount) {
        this.amount = -1;
    }



    @Override
    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {
        ///If the hit would kill us and leaves us at 0 HP or less
        if(owner.currentHealth <= 0){
            ///Heal to 1 HP again.
            addToBot(new HealAction(owner, owner, 1));
        }
        ///And refuse to die
        return false;
    }



    ///How we handle the survival mechanics
    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);
        ///If we did more than 0 damage as normal damage
        if(damageAmount > 0 && info.type == DamageInfo.DamageType.NORMAL) {
            ///And we didn't hit ourselves
            if(target != owner) {
                ///Perform an action to do all fatal-like checks
                addToBot(new BerserkerAttackAction(damageAmount, target));
            }
        }

    }




    ///At the end of the turn
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        ///Flash
        this.flash();
        ///If our powers aren't somehow empty
        if(!owner.powers.isEmpty()){
            ///Remove ALL powers that are buffs, debuffs, or Bone Scream
            for(AbstractPower pow : owner.powers){
                if(pow.type == PowerType.BUFF || pow.type == PowerType.DEBUFF || pow instanceof BoneScreamPower){
                    addToBot(new RemoveSpecificPowerAction(owner, owner, pow));
                }
            }
        }
        ///Then kill us
        this.addToBot(new LoseHPAction(this.owner, this.owner, 99999));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new BoneScreamPower(owner, amount);
    }


}
