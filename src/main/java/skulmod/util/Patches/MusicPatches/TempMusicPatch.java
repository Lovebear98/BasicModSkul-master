package skulmod.util.Patches.MusicPatches;

import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.TempMusic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.SkulMod;
import skulmod.character.LittleBone;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.SKUL_MUSIC;
import static skulmod.SkulMod.SkulMusic;

@SpirePatch(
        clz = TempMusic.class,
        method = "getSong")
public class TempMusicPatch {

    @SpirePrefixPatch
    public static SpireReturn<Music> Prefix(TempMusic __instance, String key) {
if(SkulMusic == TRUE){
    SkulMod.logger.info("Changing Music");
    switch (key) {
        case "SHOP":
            return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/BlackMarket.ogg")));

        case "SHRINE":
            return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/DimensionalFissure.ogg")));

        case "MINDBLOOM":
            return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/Chapter5_Boss_phase_1.ogg")));

        case "BOSS_BOTTOM":
                return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/Chapter1_Boss.ogg")));

        case "BOSS_CITY":
                return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/Chapter2_Boss.ogg")));

        case "BOSS_BEYOND":
                return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/Chapter3_Boss.ogg")));

        case "BOSS_ENDING":
                return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/Chapter5_Boss_phase_2.ogg")));

        case "ELITE":
                return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/Adventurer.ogg")));

        case "CREDITS":
                return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/Tutorial.ogg")));

        default:
            return SpireReturn.Continue();

    }
    }else{
    return SpireReturn.Continue();

}

}
    }
