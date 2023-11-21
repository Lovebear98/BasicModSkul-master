package skulmod.util.Patches;

import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.SkulMod;
import skulmod.cards.status.DistantHope;
import skulmod.character.LittleBone;

import java.util.Iterator;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.SkulMusic;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "dungeonTransitionSetup")
public class BanePatch {

    @SpirePostfixPatch
    public static void Postfix() {
if(AbstractDungeon.player instanceof LittleBone){
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals(AscendersBane.ID)) {
                AbstractDungeon.player.masterDeck.removeCard(c);
                AbstractDungeon.player.masterDeck.addToTop(new DistantHope());
                break;
            }
        }


}

    }
}