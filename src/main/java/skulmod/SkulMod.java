package skulmod;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.helpers.CardBorderGlowManager;
import basemod.helpers.CardModifierManager;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.Patcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scannotation.AnnotationDB;
import skulmod.cards.BaseCard;
import skulmod.cards.generated.Hibernation;
import skulmod.cards.generated.unrivaledstrike.*;
import skulmod.cards.power.SkullPowers.*;
import skulmod.character.LittleBone;
import skulmod.chimeracardaugments.AugmentHelper;
import skulmod.events.CocoonEvent;
import skulmod.potions.Calcium;
import skulmod.potions.SwapPotion;
import skulmod.powers.custompowers.DoomedDance;
import skulmod.powers.custompowers.Grit;
import skulmod.powers.custompowers.skulls.ClownPower;
import skulmod.powers.custompowers.skulls.FrostSkullPower;
import skulmod.powers.custompowers.skulls.PettyThiefPower;
import skulmod.powers.custompowers.skulls.WarriorSkullPower;
import skulmod.relics.BaseRelic;
import skulmod.util.CustomActions.SkullActions.ChooseASkull;
import skulmod.util.CustomActions.SkullActions.GenerateCannonballAction;
import skulmod.util.CustomActions.SkullActions.GotSkullAction;
import skulmod.util.CustomDynamicVariables.BlockVigor;
import skulmod.util.CustomDynamicVariables.DoubleMagic;
import skulmod.util.CustomDynamicVariables.RouletteSelfDamage;
import skulmod.util.GeneralUtils;
import skulmod.util.KeywordInfo;
import skulmod.util.Patches.SwapButton.ReloadButton;
import skulmod.util.TextureLoader;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static basemod.BaseModInit.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static skulmod.CustomTags.Skull;
import static skulmod.character.LittleBone.Enums.CARD_COLOR;
import static skulmod.character.LittleBone.Enums.Little_Bone;
import static skulmod.util.CustomActions.SkullActions.GotSkullAction.countSkullsInDeck;
import static skulmod.util.Patches.SkullPatches.ThiefGoldDamage.RecklessAttackPatch.GoldInCombat;

@SpireInitializer

public class SkulMod implements
        EditCharactersSubscriber,
        EditCardsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditRelicsSubscriber,
        PostInitializeSubscriber,
        OnPlayerTurnStartSubscriber,
        PostDrawSubscriber,
        OnStartBattleSubscriber,
        PostEnergyRechargeSubscriber,
        PostPowerApplySubscriber,
        OnPowersModifiedSubscriber,
        StartGameSubscriber,
        PostUpdateSubscriber,
        BetterOnApplyPowerPower,
        OnCardUseSubscriber,
        AddAudioSubscriber,
        PostBattleSubscriber,
        OnPlayerLoseBlockSubscriber
{

    public static ModInfo info;
    public static String modID; //Edit your pom.xml to change this
    static { loadModInfo(); }
//Settings
//Settings
    ///The buttons that allows you to hit the Swap Button on keyboard. Thanks to Hoshino's Reload mechanic for the systems behind this.
public static int swapKey = Input.Keys.Q; //R

    ///Config Options
public static Properties SkulDefaults = new Properties();
    public static final String SKUL_MUSIC = "SkulMusic";
    public static boolean SkulMusic = false;

    public static final String BOSS_SPAWN = "SpawnBoss";
    public static boolean SpawnBoss = false;
    public static final String SKULLS_SPAWN = "SkullsSpawn";
    public static boolean SkullsSpawn = false;
    public static String CurrentSkull = "None";


    ///End Config Options

    //Settings
    public static final Logger logger = LogManager.getLogger(modID); //Used to output to the console.
    //This is where your resource images go.
    private static final String resourcesFolder = "skulmod";
    private static final String BG_ATTACK = characterPath("cardback/bg_attack.png");
    private static final String BG_ATTACK_P = characterPath("cardback/bg_attack_p.png");
    private static final String BG_SKILL = characterPath("cardback/bg_skill.png");
    private static final String BG_SKILL_P = characterPath("cardback/bg_skill_p.png");
    private static final String BG_POWER = characterPath("cardback/bg_power.png");
    private static final String BG_POWER_P = characterPath("cardback/bg_power_p.png");
    private static final String ENERGY_ORB = characterPath("cardback/energy_orb.png");
    private static final String ENERGY_ORB_P = characterPath("cardback/energy_orb_p.png");
    private static final String SMALL_ORB = characterPath("cardback/small_orb.png");
    private static final String CHAR_SELECT_BUTTON = characterPath("select/button.png");
    private static final String CHAR_SELECT_PORTRAIT = characterPath("select/portrait.png");
    private static final Color cardColor = new Color(153f/255f, 51f/255f, 255f/255f, 1f);


    //red, green, blue, alpha. alpha is transparency, which should just be 1.
    //This is used to prefix the IDs of various objects like cards and relics,
    //to avoid conflicts between different mods using the same name for things.
    public static String makeID(String id) {
        return modID + ":" + id;
    }
    ///ChimeraCard fields

    public static String getModID() {
        return modID;
    }
    //ChimeraCard fields



    private static HashMap<AbstractCard.CardTags, ArrayList<AbstractCard>> tagsWithLists = new HashMap<>();
    //other stuff in class
    public static AbstractCard returnTrulyRandomCardWithTagInCombat(AbstractCard.CardTags tag) {
        if (tagsWithLists.get(tag) == null) {
            ArrayList<AbstractCard> list = new ArrayList<>();
            for (Map.Entry<String, AbstractCard> potentialCard : CardLibrary.cards.entrySet()) {
                AbstractCard card = potentialCard.getValue();
                if (card.rarity != AbstractCard.CardRarity.BASIC && card.rarity != AbstractCard.CardRarity.SPECIAL
                        && card.hasTag(tag) && !card.hasTag(AbstractCard.CardTags.HEALING)) {
                    list.add(card.makeCopy());
                }
            }
            tagsWithLists.put(tag, list);
        }
        ArrayList<AbstractCard> list = tagsWithLists.get(tag);
        return list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
    }

    ///Check if the card has the Skull tag. Thanks to Noelle/The Mystic for the whole system backing this up.
    public static boolean isThisASkull(AbstractCard card) {
        boolean retVal = false;
            if (((AbstractCard)card).hasTag(Skull)) {
                retVal = true;
            }
        return retVal;
    }



    public static AbstractCard returnStarterSkull(AbstractCard.CardTags tag) {
ArrayList<AbstractCard> starterSkull = new ArrayList<>();
        starterSkull.add(new ChampionSkull());
        starterSkull.add(new ClownSkull());
        starterSkull.add(new DavyJonesSkull());
        starterSkull.add(new GamblerSkull());
        starterSkull.add(new HunterSkull());
        starterSkull.add(new PettyThiefSkull());
        starterSkull.add(new ShieldSkull());
        starterSkull.add(new WarriorSkull());
        starterSkull.add(new DarkPaladinSkull());
        return starterSkull.get(AbstractDungeon.cardRandomRng.random(starterSkull.size() - 1));
    }

    //This will be called by ModTheSpire because of the @SpireInitializer annotation at the top of the class.
    public static void initialize() {

        new SkulMod();
        BaseMod.addColor(CARD_COLOR, cardColor,
                BG_ATTACK, BG_SKILL, BG_POWER, ENERGY_ORB,
                BG_ATTACK_P, BG_SKILL_P, BG_POWER_P, ENERGY_ORB_P,
                SMALL_ORB);
    }

    public SkulMod() {
        BaseMod.subscribe(this); //This will make BaseMod trigger all the subscribers at their appropriate times.
        logger.info(modID + " subscribed to BaseMod.");


        SkulDefaults.setProperty(SKUL_MUSIC, "TRUE");
        SkulDefaults.setProperty(BOSS_SPAWN, "FALSE");
        SkulDefaults.setProperty(SKULLS_SPAWN, "FALSE");
        try {
            SpireConfig config = new SpireConfig("SkulMod", "SkulConfig", SkulDefaults);
            config.load();
            SkulMusic = config.getBool(SKUL_MUSIC);
            SpawnBoss = config.getBool(BOSS_SPAWN);
            SkullsSpawn = config.getBool(SKULLS_SPAWN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///Make an empty list for us to put our Tactics in so we only have to make it once.
    public static ArrayList<AbstractCard> TacticsList = new ArrayList<>();
    @Override
    public void receivePostInitialize() {
        ///On startup, add our Tactics to the list
        TacticsList.add(new CeremonialWeapon());
        TacticsList.add(new GreenDragonCrescent());
        TacticsList.add(new MatterOfState());
        TacticsList.add(new ParasolCharge());
        TacticsList.add(new PeerlessBlow());
        TacticsList.add(new SixGeneralsFivePasses());
        TacticsList.add(new StrengthOf10K());
        TacticsList.add(new TigerGeneral());
        ///W add Laughing Arrow twice to make it more likely to see - a 1 in 5.
        // It may need to become a 3-in-11?
        TacticsList.add(new LaughingArrow());
        TacticsList.add(new LaughingArrow());



        BaseMod.addEvent(CocoonEvent.ID, CocoonEvent.class);



        BaseMod.addPotion(Calcium.class, Color.WHITE, null, null, Calcium.POTION_ID, Little_Bone);
        BaseMod.addPotion(SwapPotion.class, Color.PURPLE, Color.PINK, null, SwapPotion.POTION_ID, Little_Bone);

        CardBorderGlowManager.addGlowInfo(new CardBorderGlowManager.GlowInfo() {
            @Override
            public boolean test(AbstractCard abstractCard) {
                if(CardModifierManager.hasModifier(abstractCard,"ClownMod")){
                    return true;
                }
                return false;
            }

            @Override
            public Color getColor(AbstractCard abstractCard) {
                return Color.CORAL.cpy();
            }

            @Override
            public String glowID() {
                return makeID("ClownGlow");
            }
        });
        CardBorderGlowManager.addGlowInfo(new CardBorderGlowManager.GlowInfo() {
            @Override
            public boolean test(AbstractCard abstractCard) {
                if(CardModifierManager.hasModifier(abstractCard,"ChargeModifier")){
                    return true;
                }
                return false;
            }

            @Override
            public Color getColor(AbstractCard abstractCard) {
                return Color.FIREBRICK.cpy();
            }

            @Override
            public String glowID() {
                return makeID("ChargeModifier");
            }
        });




        //This loads the image used as an icon in the in-game mods menu.
        Texture badgeTexture = TextureLoader.getTexture(resourcePath("badge.png"));
        //Set up the mod information displayed in the in-game mods menu.
        //The information used is taken from your pom.xml file.
        BaseMod.registerModBadge(badgeTexture, info.Name, GeneralUtils.arrToString(info.Authors), info.Description, null);




        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        ////Play Skul:The Hero Slayer music
        ModLabeledToggleButton MusicBoxButton = new ModLabeledToggleButton("Enabled music from Skul: The Hero Slayer.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                SkulMusic, settingsPanel, (label) -> {
        }, (button) -> {
            SkulMusic = button.enabled;
            try {
                SpireConfig config = new SpireConfig("SkulMod", "SkulConfig", SkulDefaults);
                config.setBool(SKUL_MUSIC, SkulMusic);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        settingsPanel.addUIElement(MusicBoxButton);
        /////Allow Black Market Set Effect Items to spawn


        ModLabeledToggleButton SpawnSkullsButton = new ModLabeledToggleButton("At the start of the next run, add Skulls to the card pool.",
                350.0f, 600.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                SkullsSpawn, settingsPanel, (label) -> {
        }, (button) -> {
            SkullsSpawn = button.enabled;
            try {
                SpireConfig config = new SpireConfig("SkulMod", "SkulConfig", SkulDefaults);
                config.setBool(SKULLS_SPAWN, SkullsSpawn);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        settingsPanel.addUIElement(SpawnSkullsButton);












        if (Loader.isModLoaded("CardAugments")) {
            AugmentHelper.register();
        }


    }





    /*----------Localization----------*/

    //This is used to load the appropriate localization files based on language.
    private static String getLangString()
    {
        return Settings.language.name().toLowerCase();
    }
    private static final String defaultLanguage = "eng";

    public static final Map<String, KeywordInfo> keywords = new HashMap<>();

    @Override
    public void receiveEditStrings() {
        /*
            First, load the default localization.
            Then, if the current language is different, attempt to load localization for that language.
            This results in the default localization being used for anything that might be missing.
            The same process is used to load keywords slightly below.
        */
        loadLocalization(defaultLanguage); //no exception catching for default localization; you better have at least one that works.
        if (!defaultLanguage.equals(getLangString())) {
            try {
                loadLocalization(getLangString());
            }
            catch (GdxRuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLocalization(String lang) {
        //While this does load every type of localization, most of these files are just outlines so that you can see how they're formatted.
        //Feel free to comment out/delete any that you don't end up using.
        BaseMod.loadCustomStringsFile(CardStrings.class,
                localizationPath(lang, "CardStrings.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                localizationPath(lang, "CharacterStrings.json"));
        BaseMod.loadCustomStringsFile(EventStrings.class,
                localizationPath(lang, "EventStrings.json"));
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                localizationPath(lang, "OrbStrings.json"));
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                localizationPath(lang, "PotionStrings.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                localizationPath(lang, "PowerStrings.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                localizationPath(lang, "RelicStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class,
                localizationPath(lang, "UIStrings.json"));
        BaseMod.loadCustomStringsFile(TutorialStrings.class,
                localizationPath(lang, "TutorialStrings.json"));
        BaseMod.loadCustomStringsFile(MonsterStrings.class,
                localizationPath(lang, "MonsterStrings.json"));
    }

    @Override
    public void receiveEditKeywords()
    {
        Gson gson = new Gson();
        String json = Gdx.files.internal(localizationPath(defaultLanguage, "Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        KeywordInfo[] keywords = gson.fromJson(json, KeywordInfo[].class);
        for (KeywordInfo keyword : keywords) {
            registerKeyword(keyword);
        }

        if (!defaultLanguage.equals(getLangString())) {
            try
            {
                json = Gdx.files.internal(localizationPath(getLangString(), "Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
                keywords = gson.fromJson(json, KeywordInfo[].class);
                for (KeywordInfo keyword : keywords) {
                    registerKeyword(keyword);
                }
            }
            catch (Exception e)
            {
                logger.warn(modID + " does not support " + getLangString() + " keywords.");
            }
        }
    }

    private void registerKeyword(KeywordInfo info) {
        BaseMod.addKeyword(modID.toLowerCase(), info.PROPER_NAME, info.NAMES, info.DESCRIPTION);
        if (!info.ID.isEmpty())
        {
            keywords.put(info.ID, info);
        }
    }

    //These methods are used to generate the correct filepaths to various parts of the resources folder.
    public static String localizationPath(String lang, String file) {
        return resourcesFolder + "/localization/" + lang + "/" + file;
    }

    public static String resourcePath(String file) {
        return resourcesFolder + "/" + file;
    }
    public static String characterPath(String file) {
        return resourcesFolder + "/character/" + file;
    }
    public static String powerPath(String file) {
        return resourcesFolder + "/powers/" + file;
    }
    public static String relicPath(String file) {
        return resourcesFolder + "/relics/" + file;
    }


    //This determines the mod's ID based on information stored by ModTheSpire.
    private static void loadModInfo() {
        Optional<ModInfo> infos = Arrays.stream(Loader.MODINFOS).filter((modInfo)->{
            AnnotationDB annotationDB = Patcher.annotationDBMap.get(modInfo.jarURL);
            if (annotationDB == null)
                return false;
            Set<String> initializers = annotationDB.getAnnotationIndex().getOrDefault(SpireInitializer.class.getName(), Collections.emptySet());
            return initializers.contains(SkulMod.class.getName());
        }).findFirst();
        if (infos.isPresent()) {
            info = infos.get();
            modID = info.ID;
        }
        else {
            throw new RuntimeException("Failed to determine mod info/ID based on initializer.");
        }
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new LittleBone(),
                CHAR_SELECT_BUTTON, CHAR_SELECT_PORTRAIT, LittleBone.Enums.Little_Bone);
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new RouletteSelfDamage());
        BaseMod.addDynamicVariable(new BlockVigor());
        BaseMod.addDynamicVariable(new DoubleMagic());


        new AutoAdd(modID) //Loads files from this mod
                .packageFilter(BaseCard.class) //In the same package as this class
                .setDefaultSeen(true) //And marks them as seen in the compendium
                .cards(); //Adds the cards
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID) //Loads files from this mod
                .packageFilter(BaseRelic.class) //In the same package as this class
                .any(BaseRelic.class, (info, relic) -> { //Run this code for any classes that extend this class
                    if (relic.pool != null)
                        BaseMod.addRelicToCustomPool(relic, relic.pool); //Register a custom character specific relic
                    else
                        BaseMod.addRelic(relic, relic.relicType); //Register a shared or base game character specific relic

                    //If the class is annotated with @AutoAdd.Seen, it will be marked as seen, making it visible in the relic library.
                    //If you want all your relics to be visible by default, just remove this if statement.
                    if (info.seen)
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                });
    }

    @Override
    public void receiveOnPlayerTurnStart() {
        PettyThiefPower.RippedPocket = FALSE;
        ClownPower.NoCardsThisTurn = TRUE;
        ReloadButton.SwapAvailable = TRUE;
        WarriorSkullPower.AttackedThisTurn = FALSE;
    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard) {

    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        DoomedDance.powerupgraded = FALSE;
        GoldInCombat = 0;
        GenerateCannonballAction.CannonballsMade = 0;
        int SkullsInDeck = countSkullsInDeck(AbstractDungeon.player.masterDeck);
        if(SkullsInDeck > 2){
            for(int Loops = SkullsInDeck; Loops > 2; Loops -= 1) {
                AbstractDungeon.actionManager.addToTop(new GotSkullAction());
            }
            AbstractDungeon.actionManager.addToBottom(new ChooseASkull(AbstractDungeon.player, FALSE));

        }else{
            if(SkullsInDeck > 0) {
                AbstractDungeon.actionManager.addToTop(new ChooseASkull(AbstractDungeon.player, FALSE));
            }
        }

    }

    @Override
    public void receivePostEnergyRecharge() {

    }


    @Override
    public void receivePostPowerApplySubscriber(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {

    }

    @Override
    public void receivePowersModified() {
        
    }


    @Override
    public void receiveStartGame() {
        if (!CardCrawlGame.loadingSave){

            }
        }




    @Override
    public void receivePostUpdate() {

    }

    @Override
    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {

        return true;
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
if(abstractCard.costForTurn > 0){
    ClownPower.NoCardsThisTurn = FALSE;
}
if(abstractCard.type == AbstractCard.CardType.ATTACK){
    WarriorSkullPower.AttackedThisTurn = TRUE;
}
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio("SKUL_DARK_MIRROR_IN", "skulmod/audio/Hardmode_MirrorTransition_PortalOpen.wav" );
        BaseMod.addAudio("SKUL_BONE_FRAGMENTS","skulmod/audio/DarkTech_BoneSacrifies.wav");
        BaseMod.addAudio("SKUL_GOLD", "skulmod/audio/Object_GainGold.wav");
        BaseMod.addAudio("SKUL_CANNONBALL", "skulmod/audio/DavyJones_Reloading_Push.wav");
        BaseMod.addAudio("SKUL_CANNONBALL2", "skulmod/audio/DavyJones_Reloading_Push2.wav");
        BaseMod.addAudio("SKUL_ARROW","skulmod/audio/Hunter_Attack 2.wav");
        BaseMod.addAudio("SKUL_ARROW_READY","skulmod/audio/Hunter_BasicAttack_Ready 2.wav");
        BaseMod.addAudio("SKUL_SHIELD", "skulmod/audio/Heirloom_Buff.wav");
        BaseMod.addAudio("SKUL_CHAMPIONATTACK","skulmod/audio/Fighter_Hit_Heavy1.wav");
        BaseMod.addAudio("SKUL_CLOWNATTACK", "skulmod/audio/Clown_Throw_Knife 1.wav");
        BaseMod.addAudio("SKUL_GAMBLERATTACK","skulmod/audio/Gambler_AtkB.wav");
        BaseMod.addAudio("SKUL_CHARGECAP","skulmod/audio/Warrior_Charge_End.wav");
        BaseMod.addAudio("SKUL_CHARGEGAIN","skulmod/audio/Warrior_Charge_Power2.wav");
        BaseMod.addAudio("SKUL_GAMBLE","skulmod/audio/Gambler_SlotMachine_Stop.wav");
        BaseMod.addAudio("SKUL_CLOWN_PROC","skulmod/audio/Clown_Passive_Explosion.wav");
        BaseMod.addAudio("HERC_PROC","skulmod/audio/Hercules_Slam_Enhanced.wav");

    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {

    }


    @Override
    public int receiveOnPlayerLoseBlock(int i) {
if(i > 0){
    if(AbstractDungeon.player.hasPower(FrostSkullPower.POWER_ID)){
    Hibernation Shard = new Hibernation();
    Shard.baseMagicNumber = Shard.magicNumber = i;
    Shard.update();
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(Shard));
    }

    if(AbstractDungeon.player.hasPower(Grit.POWER_ID)){
        int BlockLost;
        if(AbstractDungeon.player.getPower(Grit.POWER_ID).amount >= i){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Grit(AbstractDungeon.player, -i)));
            BlockLost = 0;
        }else{
            BlockLost = i - AbstractDungeon.player.getPower(Grit.POWER_ID).amount;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Grit(AbstractDungeon.player, -AbstractDungeon.player.getPower(Grit.POWER_ID).amount)));
        }
        return Math.max(0, BlockLost);
    }
}
        return i;
    }
}

