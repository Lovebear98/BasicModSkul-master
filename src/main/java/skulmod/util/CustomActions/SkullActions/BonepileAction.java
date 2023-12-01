package skulmod.util.CustomActions.SkullActions;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Blasphemy;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import skulmod.cards.Cardmods.ClownModifier;
import skulmod.cards.generated.LoadedDice;
import skulmod.cards.power.SkullPowers.*;
import skulmod.orbs.Arrow;
import skulmod.orbs.Barrier;
import skulmod.powers.custompowers.*;
import skulmod.util.CustomActions.Cardgen.MakeTempUpgradedCardinDrawAction;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static java.lang.Boolean.TRUE;

public class BonepileAction extends AbstractGameAction {
    private Boolean cb;
    private AbstractPlayer p;
    private AbstractCard c;
    private AbstractMonster m;



    AbstractCard as;



    public BonepileAction(AbstractPlayer p, AbstractCard c, AbstractMonster m, AbstractCard as) {
        this.p = p;
        this.c = c;
        this.m = m;
        this.as = as;
        this.setValues(this.p = AbstractDungeon.player, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FAST;

    }

    @Override
    public void update() {
        if(as == null){
            as = new LittleBoneSkull();
        }
        ///Absolute
        if(as.cardID.equals(AbsoluteSkull.ID)){
            addToBot(new ApplyPowerAction(p, p, new Labors(p, 3)));
        }
        ///GodOfWar
        if(as.cardID.equals(GodOfWarSkull.ID)){
            addToBot(new ApplyPowerAction(p, p, new Tactics(p, 1)));
        }
        ///CHAMPION BONEPILE
        if(as.cardID.equals(ChampionSkull.ID)){
            addToBot(new ApplyPowerAction(p, p, (new TemporaryMaxHP(p, 3))));
        }
        ///DAVY JONES BONEPILE
        if(as.cardID.equals(DavyJonesSkull.ID)){
            addToBot(new GenerateCannonballAction());
        }
        ///SHIELD SKULL BONEPILE
        if(as.cardID.equals(ShieldSkull.ID)){
            addToBot(new ChannelAction(new Barrier()));
        }
        ///GAMBLER BONEPILE
        if(as.cardID.equals(GamblerSkull.ID)){
            int effect = AbstractDungeon.cardRandomRng.random(99);
            if(effect <=  94){
                addToBot(new MakeTempCardInDrawPileAction(new LoadedDice(), 1, true, true));
            }else{
                addToBot(new MakeTempUpgradedCardinDrawAction(new LoadedDice(), 1));
        }}
        ///WARRIOR BONEPILE
        if(as.cardID.equals(WarriorSkull.ID)){
            addToBot(new ApplyPowerAction(p, p, (new Charge(p, 1))));
        }
        ///MAGE BONEPILE
        if(as.cardID.equals(MageSkull.ID)){
                AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                if(mo != null){
                    addToBot(new ApplyPowerAction(mo, p, (new Burn(mo, 1))));
            }
        }
        ///PETTY THIEF BONEPILE
        if(as.cardID.equals(PettyThiefSkull.ID)){
            addToBot(new GainGoldAction(4));
        }
        ///CLOWN BONEPILE
        if(as.cardID.equals(ClownSkull.ID)){
            ClownMakeImitation();
        }
        ///HUNTER BONEPILE
        if(as.cardID.equals(HunterSkull.ID)){
            addToBot(new ChannelAction(new Arrow()));
        }

        ///DARK PALADIN BONEPILE
        if(as.cardID.equals(DarkPaladinSkull.ID)){
            addToBot(new ApplyPowerAction(p, p, new Grit(p, 2)));
        }
        ///GREAT WARLOCK BONEPILE
        if(as.cardID.equals(GreatWarlockSkull.ID)){
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

                AbstractMonster mo;
                while(var3.hasNext()) {
                    mo = (AbstractMonster)var3.next();
                    if(!mo.isDeadOrEscaped() && !mo.isDying && !mo.isDead && !mo.halfDead){
                        if(mo.hasPower(Concentration.POWER_ID)){
                            if(mo.getPower(Concentration.POWER_ID).amount > 0){
                                addToBot(new ApplyPowerAction(mo, p, new Concentration(mo, -1)));
                            }
                        }
                    }
                    }
                }
        }
        ///BOMBER SKULL BONEPILE
        if(as.cardID.equals(BomberSkull.ID)){
            addToBot(new ApplyPowerAction(p, p, new Gunpowder(p, 1)));
        }
        ///WEREWOLF BONEPILE
        if(as.cardID.equals(WerewolfSkull.ID)){
                addToBot(new DrawCardAction(1));
                addToBot(new WerewolfDiscardAction());
        }
        ///FROST SKULL BONEPILE
        if(as.cardID.equals(FrostSkull.ID)){
                addToBot(new GainBlockAction(p, 2));
                for(AbstractMonster mo: AbstractDungeon.getCurrRoom().monsters.monsters){
                    addToBot(new GainBlockAction(mo, 2));
                }
        }
        ///BERSERKER BONEPILE
        if(as.cardID.equals(FrostSkull.ID)){
            ///We keep this here in case we need it later.
        }
        ///Pre-Default Space
        ///Pre-Default Space
        ///LITTLE BONE BONEPILE
        if(as.cardID.equals(LittleBoneSkull.ID)){
            addToBot(new GainBlockAction(p, 2));
        }
        ///FALLBACK BONEPILE
        if(as.cardID.equals(null)){
            addToBot(new GainBlockAction(p, 2));
        }

        isDone = TRUE;
    }

    public static void ClownMakeImitation() {
        final Set<String> CLOWNBLACKLIST = new HashSet<>(
                Arrays.asList(Blasphemy.ID,
                        LittleBoneSkull.ID,
                        ChampionSkull.ID,
                        ClownSkull.ID,
                        DavyJonesSkull.ID,
                        GamblerSkull.ID,
                        MageSkull.ID,
                        PettyThiefSkull.ID,
                        ShieldSkull.ID,
                        WarriorSkull.ID,
                        HunterSkull.ID,
                        GreatWarlockSkull.ID,
                        BomberSkull.ID,
                        DarkPaladinSkull.ID,
                        WerewolfSkull.ID,
                        FrostSkull.ID
                ));

        CardCrawlGame.sound.play("SKUL_CLOWN_PROC", 0.2f);
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCard.CardRarity rarity;
        int roll = AbstractDungeon.cardRandomRng.random(99);
        if (roll < 65) {
            rarity = AbstractCard.CardRarity.COMMON;
        } else if (roll < 90) {
            rarity = AbstractCard.CardRarity.UNCOMMON;
        } else {
            rarity = AbstractCard.CardRarity.RARE;
        }
        AbstractCard card = null;
        while (card == null || CLOWNBLACKLIST.contains(card.cardID) || card.hasTag(AbstractCard.CardTags.HEALING)) {
            card = CardLibrary.getAnyColorCard(rarity);
        }

        p.limbo.addToTop(card);
        CardModifierManager.addModifier(card, new ClownModifier());
        card.current_x = Settings.WIDTH / 2.0F;
        card.current_y = Settings.HEIGHT / 2.0F;
        card.target_x = Settings.WIDTH / 2.0F;
        card.target_y = Settings.HEIGHT / 2.0F;
        card.drawScale = 0.12F;
        card.targetDrawScale = 0.75F;
        card.transparency = 0;
        card.targetTransparency = 1;
        card.applyPowers();
        AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card));
        p.limbo.clear();
    }


}
