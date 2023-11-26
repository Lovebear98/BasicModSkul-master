package skulmod.chimeracardaugments;

import CardAugments.CardAugmentsMod;
import CardAugments.cardmods.AbstractAugment;
import CardAugments.cardmods.common.*;
import CardAugments.cardmods.curse.DisposableMod;
import CardAugments.cardmods.curse.ReturningMod;
import CardAugments.cardmods.curse.VoidingMod;
import CardAugments.cardmods.event.*;
import CardAugments.cardmods.rare.*;
import CardAugments.cardmods.uncommon.*;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import skulmod.CustomTags;
import skulmod.SkulMod;
import skulmod.cards.attack.werewolf.Momentum;
import skulmod.cards.attack.werewolf.Predation;
import skulmod.cards.generated.Cannonfire;
import skulmod.cards.skill.LetsKnitTogether;
import skulmod.cards.skill.davyjones.EyeOfTheStorm;
import skulmod.character.LittleBone;

import java.util.ArrayList;

import static skulmod.SkulMod.makeID;

public class AugmentHelper {
    public static void register() {
        ArrayList<String> BanList = MakeBanList();
        int Count = BanList.size()-1;
        for(int Loops = 0; !(Loops == BanList.size() - 1); Loops += 1){
            CardAugmentsMod.registerCustomBan(BanList.get(Loops), c -> c.hasTag(CustomTags.Skull));
        }
        CardAugmentsMod.registerCustomBan(BonyMod.ID, c -> c instanceof Cannonfire);
        CardAugmentsMod.registerCustomBan(BonyMod.ID, c -> c instanceof Momentum);
        CardAugmentsMod.registerCustomBan(BonyMod.ID, c -> c instanceof EyeOfTheStorm);
        CardAugmentsMod.registerCustomBan(BonyMod.ID, c -> c instanceof LetsKnitTogether);
        CardAugmentsMod.registerCustomBan(FrozenMod.ID, c -> c instanceof Predation);








        //Register mod
        CardAugmentsMod.registerMod(SkulMod.getModID(), CardCrawlGame.languagePack.getUIString(makeID("ModConfigs")).TEXT[0]);

        //Set M10 as an Orb user
        CardAugmentsMod.registerOrbCharacter(LittleBone.Enums.Little_Bone);

        //Can add modifiers manually
        //CardAugmentsMod.registerAugment(new BonyMod(), SkulMod.getModID());
        // etc...

        //Or use an autoadd
        new AutoAdd(SkulMod.getModID())
                .packageFilter("skulmod.chimeracardaugments")
                .any(AbstractAugment.class, (info, abstractAugment) -> CardAugmentsMod.registerAugment(abstractAugment, SkulMod.getModID()));
    }


    public static ArrayList<String> MakeBanList(){
        ArrayList<String> AugmentBanList = new ArrayList<>();
        AugmentBanList.add(AllOutMod.ID);
        AugmentBanList.add(BootMod.ID);
        AugmentBanList.add(BowlingMod.ID);
        AugmentBanList.add(BrutalMod.ID);
        AugmentBanList.add(ChargedMod.ID);
        AugmentBanList.add(DevotedMod.ID);
        AugmentBanList.add(DoubtfulMod.ID);
        AugmentBanList.add(ElectroMod.ID);
        AugmentBanList.add(FlyingMod.ID);
        AugmentBanList.add(FollowUpMod.ID);
        AugmentBanList.add(FrostyMod.ID);
        AugmentBanList.add(GhostlyMod.ID);
        AugmentBanList.add(GritMod.ID);
        AugmentBanList.add(HolyMod.ID);
        AugmentBanList.add(LuckyMod.ID);
        AugmentBanList.add(MassiveMod.ID);
        AugmentBanList.add(PoisonedMod.ID);
        AugmentBanList.add(QuickMod.ID);
        AugmentBanList.add(ReshuffleMod.ID);
        AugmentBanList.add(RollMod.ID);
        AugmentBanList.add(ShamefulMod.ID);
        AugmentBanList.add(ShieldedMod.ID);
        AugmentBanList.add(ShivMod.ID);
        AugmentBanList.add(SlayerMod.ID);
        AugmentBanList.add(StickyMod.ID);
        AugmentBanList.add(StreamlinedMod.ID);
        AugmentBanList.add(SupplyMod.ID);
        AugmentBanList.add(SurvivorMod.ID);
        AugmentBanList.add(TinyMod.ID);
        AugmentBanList.add(WaveMod.ID);
        AugmentBanList.add(DisposableMod.ID);
        AugmentBanList.add(ReturningMod.ID);
        AugmentBanList.add(VoidingMod.ID);
        AugmentBanList.add(AberrantMod.ID);
        AugmentBanList.add(AutoMod.ID);
        AugmentBanList.add(CultistMod.ID);
        AugmentBanList.add(EchoMod.ID);
        AugmentBanList.add(FanaticMod.ID);
        AugmentBanList.add(GraveMod.ID);
        AugmentBanList.add(ShiningMod.ID);
        AugmentBanList.add(AlphaMod.ID);
        AugmentBanList.add(BerserkMod.ID);
        AugmentBanList.add(BlasphemousMod.ID);
        AugmentBanList.add(BludgeonMod.ID);
        AugmentBanList.add(BundledMod.ID);
        AugmentBanList.add(ClashyMod.ID);
        AugmentBanList.add(DemurMod.ID);
        AugmentBanList.add(DivergentMod.ID);
        AugmentBanList.add(ExplosiveMod.ID);
        AugmentBanList.add(FlexMod.ID);
        AugmentBanList.add(FormMod.ID);
        AugmentBanList.add(ForOneMod.ID);
        AugmentBanList.add(FragileMod.ID);
        AugmentBanList.add(FusedMod.ID);
        AugmentBanList.add(GlassMod.ID);
        AugmentBanList.add(GreedMod.ID);
        AugmentBanList.add(HemoMod.ID);
        AugmentBanList.add(InfiniteMod.ID);
        AugmentBanList.add(InvertedMod.ID);
        AugmentBanList.add(JankMod.ID);
        AugmentBanList.add(MeteorMod.ID);
        AugmentBanList.add(MK2Mod.ID);
        AugmentBanList.add(MoxieMod.ID);
        AugmentBanList.add(NuclearMod.ID);
        AugmentBanList.add(PerniciousMod.ID);
        AugmentBanList.add(PhilosophersMod.ID);
        AugmentBanList.add(RunicMod.ID);
        AugmentBanList.add(SanctifiedMod.ID);
        AugmentBanList.add(SearingMod.ID);
        AugmentBanList.add(SignatureMod.ID);
        AugmentBanList.add(SlaverMod.ID);
        AugmentBanList.add(SluggerMod.ID);
        AugmentBanList.add(SurgeMod.ID);
        AugmentBanList.add(XMod.ID);
        AugmentBanList.add(AbsoluteMod.ID);
        AugmentBanList.add(AmplifiedMod.ID);
        AugmentBanList.add(AngryMod.ID);
        AugmentBanList.add(ArmedMod.ID);
        AugmentBanList.add(BashMod.ID);
        AugmentBanList.add(BloodyMod.ID);
        AugmentBanList.add(BlurryMod.ID);
        AugmentBanList.add(BracedMod.ID);
        AugmentBanList.add(ClawfulMod.ID);
        AugmentBanList.add(DoomedMod.ID);
        AugmentBanList.add(DramaticMod.ID);
        AugmentBanList.add(EndlessMod.ID);
        AugmentBanList.add(ErangMod.ID);
        AugmentBanList.add(EruptingMod.ID);
        AugmentBanList.add(FearfulMod.ID);
        AugmentBanList.add(GloomMod.ID);
        AugmentBanList.add(GoForTheMod.ID);
        AugmentBanList.add(HeavyMod.ID);
        AugmentBanList.add(LockingMod.ID);
        AugmentBanList.add(MasochisticMod.ID);
        AugmentBanList.add(MementoMod.ID);
        AugmentBanList.add(NoxiousMod.ID);
        AugmentBanList.add(PiercingMod.ID);
        AugmentBanList.add(PredatoryMod.ID);
        AugmentBanList.add(PureMod.ID);
        AugmentBanList.add(RampedMod.ID);
        AugmentBanList.add(SadisticMod.ID);
        AugmentBanList.add(SlipperyMod.ID);
        AugmentBanList.add(SneckoMod.ID);
        AugmentBanList.add(SuckerMod.ID);
        AugmentBanList.add(SwivelMod.ID);
        AugmentBanList.add(TearMod.ID);
        AugmentBanList.add(VanishingMod.ID);
        AugmentBanList.add(VoidMod.ID);
        AugmentBanList.add(WallopMod.ID);

        return AugmentBanList;
    }
}
