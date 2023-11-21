package skulmod.relics;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import skulmod.cards.power.SkullPowers.GamblerSkull;
import skulmod.cards.power.SkullPowers.PettyThiefSkull;
import skulmod.character.LittleBone;

import static java.lang.Boolean.FALSE;
import static skulmod.SkulMod.makeID;

@NoCompendium
public class LuckAddiction extends BaseRelic {
    private static final String NAME = "LuckAddiction"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.UNCOMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.FLAT; //The sound played when the relic is clicked.

    public LuckAddiction() {

        super(ID, NAME, LittleBone.Enums.CARD_COLOR, RARITY, SOUND);

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
        super.atBattleStart();

    }

    @Override
    public boolean canSpawn() {
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals(GamblerSkull.ID)) {
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
