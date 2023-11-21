package skulmod.relics;

import basemod.helpers.CardPowerTip;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.WrathStance;
import skulmod.cards.generated.Acceleration;
import skulmod.cards.power.SkullPowers.ChampionSkull;
import skulmod.cards.power.SkullPowers.WerewolfSkull;
import skulmod.character.LittleBone;

import java.util.Objects;

import static skulmod.SkulMod.makeID;

@NoCompendium
public class EternalHunt extends BaseRelic {
    private static final String NAME = "EternalHunt"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.UNCOMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.

    public EternalHunt() {

        super(ID, NAME, LittleBone.Enums.CARD_COLOR, RARITY, SOUND);
        this.tips.add(new CardPowerTip(new Acceleration()));
        this.counter = 0;
    }

    @Override
    public void atTurnStartPostDraw() {

    }

    @Override
    public void onObtainCard(AbstractCard c) {
        super.onObtainCard(c);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void onEquip() {
        super.onEquip();
    }

    @Override
    public void atBattleStart() {
        addToBot(new MakeTempCardInDrawPileAction(new Acceleration(),1, true, true));
        super.atBattleStart();

    }

    @Override
    public void onShuffle() {
        addToBot(new MakeTempCardInDrawPileAction(new Acceleration(),1, true, true));
        super.onShuffle();
    }

    @Override
    public boolean canSpawn() {
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals(WerewolfSkull.ID)) {
                return true;
            }
        }
        return false;
    }





    @Override
    public void onUnequip() {
        super.onUnequip();
    }



    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {


    }


// Take advantage of autocomplete!
    // If you type "public onUse" IntelliJ should already have the method in the suggestions.
    // Use the up/down arrows to select it and press enter to automatically create this whole chunk.
    // This autocomplete is also a good way to see all the hooks/look for the right hook by name, by just typing "publi"




    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }




}
