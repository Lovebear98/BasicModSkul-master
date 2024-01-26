package skulmod.cards.Cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import skulmod.util.CustomActions.SkullActions.BonepileAction;
import skulmod.util.icons.CardmodIcons;

import java.util.List;

import static skulmod.SkulMod.makeID;
import static skulmod.util.CustomActions.SkullActions.ChooseASkull.ActiveSkull;

public class UnrivaledStrikeMod extends AbstractCardModifier {

    public static final String ID = makeID("UnrivaledStrike");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    ///Modifications
    ///If the card is in the 'Laughing Arrow' state
    public boolean LaughingArrow = false;

    ///How many times to apply Strength from the 'Strength of 10,000' state.
    public int StrengthOf10K = 0;

    ///How much damage has been added by the 'Parasol Charge' state
    public int ParasolCharge = 0;

    ///How many times to Bonepile through the GReen Dragon Crescent state.
    public int GreenDragonCrescent = 0;
    ///How many times the cost has been increased  by the Peerless Blow State
    public int PeerlessBlow = 0;

    public UnrivaledStrikeMod(boolean LArrow, int StrengthMult, int Dmg, int BonePile, int Peerless) {
        this.LaughingArrow = LArrow;
        this.StrengthOf10K = StrengthMult;
        this.ParasolCharge = Dmg;
        this.GreenDragonCrescent = BonePile;
        this.PeerlessBlow = Peerless;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        if(LaughingArrow){
            ExtraIcons.icon(CardmodIcons.BigLaughingArrow).render(card);
        }
    }

    @Override
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        if(LaughingArrow){
            ExtraIcons.icon(CardmodIcons.BigLaughingArrow).render(card);
        }
    }
    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    ///Modifications
    @Override
    public boolean shouldApply(AbstractCard targetCard) {
        ///Noelle casually saving me an embarrassing amount of times
        List<AbstractCardModifier> tensionMod = CardModifierManager.getModifiers(targetCard, identifier(null));
        if (!tensionMod.isEmpty()) {
            if (this.LaughingArrow) {
                ((UnrivaledStrikeMod) tensionMod.get(0)).LaughingArrow = !((UnrivaledStrikeMod) tensionMod.get(0)).LaughingArrow;
            }
            ((UnrivaledStrikeMod) tensionMod.get(0)).StrengthOf10K += this.StrengthOf10K;
            ((UnrivaledStrikeMod) tensionMod.get(0)).ParasolCharge += this.ParasolCharge;
            ((UnrivaledStrikeMod) tensionMod.get(0)).GreenDragonCrescent += this.GreenDragonCrescent;
            ((UnrivaledStrikeMod) tensionMod.get(0)).PeerlessBlow += this.PeerlessBlow;
            if(this.PeerlessBlow >= 1){
                if(targetCard.cost != -1 && targetCard.cost != -2){
                    targetCard.cost += PeerlessBlow;
                    targetCard.costForTurn += PeerlessBlow;
                }
            }
            if(this.ParasolCharge >= 1){
                targetCard.applyPowers();
            }
            targetCard.initializeDescription();
            targetCard.applyPowers();
            return false;
        }
        return true;
    }


    @Override
    public String identifier(AbstractCard card) {
        return "UnrivaledStrikeMod";
    }

    @Override
    public void onInitialApplication(AbstractCard card) {


    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractPlayer p = AbstractDungeon.player;
        int damage = card.damage;
        int[] multiDamage = card.multiDamage;
        super.onUse(card, target, action);
        if (LaughingArrow) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, damage));
            if (PeerlessBlow >= 1) {
                AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(card.costForTurn * 2));
            }
        } else {
            if (PeerlessBlow >= 1) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(card.costForTurn * 2));
            } else {
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }


        if (GreenDragonCrescent >= 1) {
            for (int Loops = GreenDragonCrescent; Loops > 0; Loops -= 1) {
                AbstractDungeon.actionManager.addToBottom(new BonepileAction(p, null, (AbstractMonster) target, ActiveSkull));
            }
        }
    }


    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        if(ParasolCharge >= 1){
            damage += ParasolCharge;
        }

        if(StrengthOf10K >= 1){
            if (AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)){
                for(int Loops = StrengthOf10K; Loops > 0; Loops -= 1){
                    damage += AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount;
                }
            }
        }



        return super.modifyDamage(damage, type, card, target);
    }


    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        ///Determining what the primary effect of the card is
        String BaseEffect;
        if(LaughingArrow){
            if(PeerlessBlow>= 1){
                BaseEffect = cardStrings.EXTENDED_DESCRIPTION[3];
            }else{
                BaseEffect = cardStrings.EXTENDED_DESCRIPTION[2];
            }
        }else{
            if(PeerlessBlow >= 1){
                BaseEffect = cardStrings.EXTENDED_DESCRIPTION[1];
            }else{
                BaseEffect = cardStrings.EXTENDED_DESCRIPTION[0];
            }
        }
        ///Setting the base description to what we found
        rawDescription = BaseEffect;

        ///Determining what extra effects are added and adding them
        if(StrengthOf10K >= 1){
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[4] + StrengthOf10K + cardStrings.EXTENDED_DESCRIPTION[5];
        }
        if(GreenDragonCrescent >= 1){
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[6] + GreenDragonCrescent + cardStrings.EXTENDED_DESCRIPTION[7];
        }








        return super.modifyDescription(rawDescription, card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new UnrivaledStrikeMod(LaughingArrow, StrengthOf10K, ParasolCharge, GreenDragonCrescent, PeerlessBlow);
    }


    public static boolean IsBlock(AbstractCard targetCard){
        ///Noelle casually saving me an embarrassing amount of times
        List<AbstractCardModifier> tensionMod = CardModifierManager.getModifiers(targetCard, "UnrivaledStrikeMod");
        if (!tensionMod.isEmpty()) {
            return ((UnrivaledStrikeMod) tensionMod.get(0)).LaughingArrow;
        }
        return false;
    }

}
