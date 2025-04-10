package skulmod.util.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.cards.status.DistantHope;
import skulmod.character.LittleBone;

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