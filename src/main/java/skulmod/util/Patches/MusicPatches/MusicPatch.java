package skulmod.util.Patches.MusicPatches;

import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import skulmod.SkulMod;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.SkulMusic;
///Thank you The Drifter, for showing me where to patch!
@SpirePatch(
        clz = MainMusic.class,
        method = "getSong")
public class MusicPatch {

    @SpirePrefixPatch
    public static SpireReturn<Music> Prefix(MainMusic __instance, String key) {
if(SkulMusic == TRUE){
    SkulMod.logger.info("Changing Music");
    switch (key) {
        case "Exordium":
                return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/Chapter1.ogg")));

        case "TheCity":
                return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/Chapter2.ogg")));


        case "TheBeyond":
                return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/Chapter3.ogg")));

        case "TheEnding":
                return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/Chapter4.ogg")));

        case "MENU":
            return SpireReturn.Return((MainMusic.newMusic("skulmod/audio/Music/DemonCastle.ogg")));

        default:
            SkulMod.logger.info("BGM Dun Goofed. Defaulting: " + key);
            return SpireReturn.Continue();
    }

}else{
    return SpireReturn.Continue();
}

    }
}