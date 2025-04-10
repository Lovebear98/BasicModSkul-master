package skulmod.patches.crosscompatskulls.unchained;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.util.CustomActions.SkullActions.BonepileAction;
import skulmod.util.CustomActions.SkullActions.ChooseASkull;
import skulmod.util.othermodskulls.unchained.cards.RiderSkull;
import theUnchainedMod.actions.GainRelayAction;

import static skulmod.SkulMod.hasUnchained;


public class UnchainedBonepiles {

    @SpirePatch(
            clz= BonepileAction.class,
            method="ExternalBonepiles",
            requiredModId = "TheUnchainedMod",
            optional = true
    )
    public static class MoreBonepiles {

        @SpirePrefixPatch
 public static void NewBonepiles(){
            if(ChooseASkull.ActiveSkull != null) {
                AbstractCard as = ChooseASkull.ActiveSkull;
                AbstractPlayer p = AbstractDungeon.player;
                ///Check for modcompat first so we don't call a card that doesn't exist
                if (hasUnchained) {
                    ///RIDER BONEPILE
                    if (as.cardID.equals(RiderSkull.ID)) {
                        AbstractDungeon.actionManager.addToBottom(new GainRelayAction(p, 2));
                    }
                }
            }
        }
    }
}

