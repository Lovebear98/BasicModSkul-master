package skulmod.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import skulmod.CustomTags;
import skulmod.cards.power.SkullPowers.*;

import java.util.ArrayList;

import static skulmod.SkulMod.makeID;
import static skulmod.util.vars.RelicSpawn;

public class DarkMirror extends BaseRelic {
    private static final String NAME = "DarkMirror"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SHOP; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.SOLID; //The sound played when the relic is clicked.
    private boolean cardSelected = true;

    public DarkMirror() {
        super(ID, NAME, RARITY, SOUND);
    }

    @Override
    public void atTurnStartPostDraw() {

    }
    @Override
    public boolean canSpawn() {
        return RelicSpawn();
    }

    @Override
    public void onObtainCard(AbstractCard c) {
        
        super.onObtainCard(c);
    }
    public void onEquip() {
        this.cardSelected = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }

        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, 1, this.DESCRIPTIONS[1], false, false, false, false);
    }
    public void update() {
        super.update();
        if (!this.cardSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
            this.cardSelected = true;
            AbstractCard c = (AbstractDungeon.gridSelectScreen.selectedCards.get(0)).makeStatEquivalentCopy();
            if(c.hasTag(CustomTags.Skull)){
                ArrayList<AbstractCard> DarkMirrorSkull = new ArrayList<>();
                DarkMirrorSkull.add(new BerserkerSkull());
                DarkMirrorSkull.add(new ChampionSkull());
                DarkMirrorSkull.add(new DavyJonesSkull());
                DarkMirrorSkull.add(new FrostSkull());
                DarkMirrorSkull.add(new AbsoluteSkull());
                c = DarkMirrorSkull.get(AbstractDungeon.cardRng.random(DarkMirrorSkull.size() - 1));
            }
            c.inBottleFlame = false;
            c.inBottleLightning = false;
            c.inBottleTornado = false;
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

    }





    @Override
    public void atBattleStart() {
        super.atBattleStart();

    }





    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {


    }

    @Override
    public void atPreBattle() {

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
