package skulmod.character;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import skulmod.cards.attack.CuteLittleSkulls;
import skulmod.cards.attack.Strike;
import skulmod.cards.power.SkullPowers.*;
import skulmod.cards.skill.Defend;
import skulmod.cards.skill.LetsKnitTogether;
import skulmod.cards.skill.hunter.TheFifthMeteor;
import skulmod.relics.*;
import skulmod.util.CustomActions.StarterSkullPool;
import skulmod.util.animation.AtlasPaths;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static skulmod.SkulMod.*;

public class LittleBone extends CustomPlayer {






    //Stats
    public static final int ENERGY_PER_TURN = 3;
    public static final int MAX_HP = 59;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 4;

    //Strings

    //Energy Orb Images
    public static final String[] orbTextures = {
            characterPath("orb/layer1.png"),
            characterPath("orb/layer2.png"),
            characterPath("orb/layer3.png"),
            characterPath("orb/layer4.png"),
            characterPath("orb/layer5.png"),
            characterPath("orb/layer6.png"),
            characterPath("orb/layer1d.png"),
            characterPath("orb/layer2d.png"),
            characterPath("orb/layer3d.png"),
            characterPath("orb/layer4d.png"),
            characterPath("orb/layer5d.png")
    };
    public static final float[] layerSpeeds = {
            0.0F, 40.0F, -40.0F, 20.0F, 0.0F,
            16.0F, 8.0F, -8.0F, 5.0F, 0.0F,
    };

    private static final String ID = makeID("SkulMod"); //This should match whatever you have in the CharacterStrings.json file
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    //Image file paths
    private static final String SHOULDER_1 = characterPath("shoulder.png"); //Shoulder 1 and 2 are used at rest sites.
    private static final String SHOULDER_2 = characterPath("shoulder2.png");
    private static final String CORPSE = characterPath("corpse.png"); //Corpse is when you die.
    private Color hbTextColor = Color.WHITE;

    public static class Enums {
        //These are used to identify your character, as well as your character's card color.
        //Library color is basically the same as card color, but you need both because that's how the game was made.
        @SpireEnum
        public static AbstractPlayer.PlayerClass Little_Bone;
        @SpireEnum(name = "Quartz_Purple") // These two MUST match. Change it to something unique for your character.
        public static AbstractCard.CardColor CARD_COLOR;
        @SpireEnum(name = "Quartz_Purple")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    public LittleBone() {

        super(NAMES[0], Enums.Little_Bone,
                new CustomEnergyOrb(orbTextures, characterPath("orb/vfx.png"), layerSpeeds), //Energy Orb
                new SpineAnimation(characterPath("animation/LittleBone.atlas"), characterPath("animation/LittleBone.json"), 1f)); //Animation
        AnimationState.TrackEntry e = state.setAnimation(0, "IDLE", true);


        initializeClass(null,
                SHOULDER_2,
                SHOULDER_1,
                CORPSE,
                getLoadout(),
                20.0F, -20.0F, 200.0F, 250.0F, //Character hitbox. x y position, then width and height.
                new EnergyManager(ENERGY_PER_TURN));

        //Location for text bubbles. You can adjust it as necessary later. For most characters, these values are fine.
        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 220.0F * Settings.scale);
    }

    public void damage(DamageInfo info) {

        super.damage(info);
    }
//Character mechanics below


    @Override
    public void preBattlePrep() {
        super.preBattlePrep();

    }

    @Override
    public void applyPreCombatLogic() {
        ///Skull will run on-screen at the start of combat
        AnimateSkull(AtlasPaths.LittleBoneAnim(), "WALKIN");
        super.applyPreCombatLogic();
    }

    @Override
    public void applyStartOfCombatLogic() {
        super.applyStartOfCombatLogic();
    }

    @Override
    public void useCard(AbstractCard c, AbstractMonster monster, int energyOnUse) {
        super.useCard(c, monster, energyOnUse);

        }

//Character mechanics above


    @Override
    public ArrayList<String> getStartingDeck() {







        ArrayList<String> retVal = new ArrayList<>();

        //List of IDs of cards for your starting deck.
        //If you want multiple of the same card, you have to add it multiple times.
        retVal.add(LittleBoneSkull.ID);
        ///Add a random card from
        retVal.add(StarterSkullPool.RandomCardInList(StarterSkullPool.StarterSkullList()));
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(LetsKnitTogether.ID);
        retVal.add(LetsKnitTogether.ID);
        retVal.add(CuteLittleSkulls.ID);


        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        //IDs of starting relics. You can have multiple, but one is recommended.
        retVal.add(Reassemble.ID);
        retVal.add(BestFriend.ID);


        return retVal;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        //This card is used for the Gremlin card matching game.
        //It should be a non-strike non-defend starter card, but it doesn't have to be.
        return new TheFifthMeteor();
    }

    /*- Below this is methods that you should *probably* adjust, but don't have to. -*/

    @Override
    public int getAscensionMaxHPLoss() {
        return 7; //Max hp reduction at ascension 14+
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        //These attack effects will be used when you attack the heart.
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY
        };
    }

    private final Color cardRenderColor = Color.PURPLE.cpy(); //Used for some vfx on moving cards (sometimes) (maybe)
    private final Color cardTrailColor = Color.PURPLE.cpy(); //Used for card trail vfx during gameplay.
    private final Color slashAttackColor = Color.PURPLE.cpy(); //Used for a screen tint effect when you attack the heart.

    @Override
    public Color getCardRenderColor() {
        return cardRenderColor;
    }

    @Override
    public Color getCardTrailColor() {
        return cardTrailColor;
    }

    @Override
    public Color getSlashAttackColor() {
        return slashAttackColor;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        //Font used to display your current energy.
        //energyNumFontRed, Blue, Green, and Purple are used by the basegame characters.
        //It is possible to make your own, but not convenient.
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        //This occurs when you click the character's button in the character select screen.
        //See SoundMaster for a full list of existing sound effects, or look at BaseMod's wiki for adding custom audio.
        CardCrawlGame.sound.playA("SKUL_DARK_MIRROR_IN", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        //Similar to doCharSelectScreenSelectEffect, but used for the Custom mode screen. No shaking.
        return "SKUL_BONE_FRAGMENTS";
    }


    //To replace the heart kill cutscene
    @Override
    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList<>();
        panels.add(new CutscenePanel("skulmod/scenes/Scene1.png", "BLUNT_HEAVY"));
        panels.add(new CutscenePanel("skulmod/scenes/Scene2.png", "ATTACK_FAST"));
        panels.add(new CutscenePanel("skulmod/scenes/Scene3.png", "APPEAR"));
        panels.add(new CutscenePanel("skulmod/scenes/Scene4.png", "BATTLE_START_BOSS"));
        return panels;
    }

    @Override
    public Texture getCutsceneBg() {
        return ImageMaster.loadImage("skulmod/scenes/Background.png");
    }

    //Don't adjust these four directly, adjust the contents of the CharacterStrings.json file.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2]; //Generally, the only difference in this text is how the vampires refer to the player.
    }

    /*- You shouldn't need to edit any of the following methods. -*/

    //This is used to display the character's information on the character selection screen.
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                MAX_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this,
                getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.CARD_COLOR;
    }

    @Override
    public AbstractPlayer newInstance() {
        //Makes a new instance of your character class.
        return new LittleBone();
    }

    public void AnimateSkull(String Skull, String type){
        ///type will be ATTACK, IDLE, or DODGE in combat and WALKIN for little bone on combat start only.
        ///Skull will be a path from another class that matches the file's name

        ///If we're calling our current Skull, skip loading a new atlas and just play an animation
        if(!Objects.equals(CurrentSkull, Skull)){
            ///Otherwise, load an animation using the string we chose for the Skull's file
            this.loadAnimation(characterPath("animation/"+Skull+".atlas"), characterPath("animation/"+Skull+".json"), 1f);
        }


        ///Then play the given animation once if it wasn't the idle animation
        if(!Objects.equals(type, "IDLE")){
            AbstractDungeon.player.state.setAnimation(0, type, false);// 374
        }

    ///Then play the idle animation afterwards
    AbstractDungeon.player.state.addAnimation(0, "IDLE", true, 0.0F);// 375


        ///Set the 'Current Skull' to our current skull so we know not to load the atlas if we don't have to
        CurrentSkull = Skull;
    }

}