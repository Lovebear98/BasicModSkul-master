package skulmod.util.CustomActions.SkullActions;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import skulmod.cards.Cardmods.UnrivaledStrikeMod;
import skulmod.cards.generated.Awakening;
import skulmod.cards.generated.Hibernation;
import skulmod.cards.generated.unrivaledstrike.StrategyBook;
import skulmod.cards.power.SkullPowers.*;
import skulmod.powers.custompowers.*;
import skulmod.powers.custompowers.skulls.AbsoluteSkullPower;
import skulmod.powers.custompowers.skulls.GamblerPower;
import skulmod.powers.custompowers.skulls.PettyThiefPower;
import skulmod.util.CustomActions.PhantomPlayCardAction;

import java.util.Iterator;

import static java.lang.Boolean.TRUE;
import static skulmod.util.CustomActions.SkullActions.ChooseASkull.ActiveSkull;

public class SwapAction extends AbstractGameAction {

    private final AbstractCard c;
    private AbstractPlayer p;


    public SwapAction(AbstractCard c) {
this.p = AbstractDungeon.player;
        this.c = c;
        this.setValues(this.p, AbstractDungeon.player, 1);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        ///AbsoluteSwap
        if(c.cardID.equals(AbsoluteSkull.ID)){
            addToBot(new ApplyPowerAction(p, p, new Labors(p, 12)));
            addToBot(new PhantomPlayCardAction(AbsoluteSkullPower.RandomGrapple(true), null));
        }
        ///GodOfWar
        if(c.cardID.equals(GodOfWarSkull.ID)){
            if(StrategyBook.HasUnrivaled()){
                AbstractCard c = StrategyBook.UnrivaledInHand();
                CardModifierManager.addModifier(c, new UnrivaledStrikeMod(false, 0, 5, 0, 0));
            }
        }
        ///CHAMPION SWAP
        if(c.cardID.equals(ChampionSkull.ID)){
        if(p.stance.ID.equals("Wrath")){
           this.addToBot(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
           addToBot(new ChangeStanceAction("Neutral"));
            }else{
           addToBot(new ChangeStanceAction("Wrath"));
            }
        }
        ///DAVY JONES SWAP
        if(c.cardID.equals(DavyJonesSkull.ID)){
           addToBot(new GenerateCannonballAction());
           addToBot(new GenerateCannonballAction());
           addToBot(new GenerateCannonballAction());
           addToBot(new GenerateCannonballAction());
        }
        ///SHIELD SKULL SWAP
        if(c.cardID.equals(ShieldSkull.ID)){
           AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 5)));
           AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LaterDexterityDown(AbstractDungeon.player, 5)));

        }
        ///GAMBLER SWAP
        if(c.cardID.equals(GamblerSkull.ID)){
            GamblerPower.DoubleOrNothing = TRUE;

        }
        ///WARRIOR SWAP
        if(c.cardID.equals(WarriorSkull.ID)){
            addToBot(new ApplyPowerAction(p, p, (new Charge(p, 5))));
        }
        ///CLOWN SWAP
        if(c.cardID.equals(ClownSkull.ID)){
            addToBot(new BonepileAction(p, null, null, ActiveSkull));
            addToBot(new BonepileAction(p, null, null, ActiveSkull));
        ///MAGE SWAP
        }
        if(c.cardID.equals(MageSkull.ID)){
            addToBot(new MakeTempCardInHandAction(new Awakening()));
        }
        ///PETTY THIEF SWAP
        if(c.cardID.equals(PettyThiefSkull.ID)){
            PettyThiefPower.RippedPocket = TRUE;
        }
        ///HUNTER SWAP
        if(c.cardID.equals(HunterSkull.ID)){
           addToBot(new IncreaseMaxOrbAction(1));
        }
        ///DARK PALADIN SWAP
        if(c.cardID.equals(DarkPaladinSkull.ID)){
            addToBot(new ApplyPowerAction(p, p, (new DarkPower(p, 8))));
        }
        ///GREAT WARLOCK SWAP
        if(c.cardID.equals(GreatWarlockSkull.ID)){
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

                AbstractMonster mo;
                while(var3.hasNext()) {
                    mo = (AbstractMonster)var3.next();
                    if(!mo.isDeadOrEscaped() && !mo.isDying && !mo.isDead && !mo.halfDead){
                        if(mo.hasPower(Concentration.POWER_ID)){
                            this.addToTop(new RemoveSpecificPowerAction(mo, mo, Concentration.POWER_ID));
                        }
                    }
                }
            }
        }
        ///WEREWOLF SWAP
        if(c.cardID.equals(WerewolfSkull.ID)){
            if(AbstractDungeon.player.discardPile.size() > 0){
                addToBot(new EmptyDeckShuffleAction());
            }

        }
        ///FROST SKULL SWAP
        if(c.cardID.equals(FrostSkull.ID)){
            addToBot(new MakeTempCardInHandAction(new Hibernation()));
        }
        ///BERSERKER SWAP
        if(c.cardID.equals(BerserkerSkull.ID)){
           ///We put this here in case we need it
        }
        ///Pre-Default Space
        ///Pre-Default Space
        ///LITTLE BONE SWAP
        if(c.cardID.equals(LittleBoneSkull.ID)){
            addToBot(new GainEnergyAction(2));
        }
        ///FALLBACK SWAP
        if(c.cardID.equals(null)){
            addToBot(new GainEnergyAction(2));
        }
        isDone = TRUE;
    }








}
