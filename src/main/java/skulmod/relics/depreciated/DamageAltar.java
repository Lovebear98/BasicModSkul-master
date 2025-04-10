package skulmod.relics.depreciated;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.rooms.RestRoom;
import skulmod.character.LittleBone;
import skulmod.relics.BaseRelic;

import static skulmod.SkulMod.makeID;
import static skulmod.relics.depreciated.BoneFragments.FragmentCount;
import static skulmod.relics.depreciated.BoneFragments.FragmentGain;
public class DamageAltar extends BaseRelic implements ClickableRelic {
    private static final String NAME = "DamageAltar"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SPECIAL; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    private static int BoneFragmentCost = 10;
    private static int AlarMaxUses = 8;
    private static int DamageAltarGain = 1;

    private static int AltarCap = AlarMaxUses * DamageAltarGain;

    public DamageAltar() {
        super(ID, NAME, LittleBone.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atTurnStartPostDraw() {

    }


    @Override
    public void update() {
        super.update();



    }

    @Override
    public void onEquip() {
        super.onEquip();
        this.counter = 0;

    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
if(this.counter > 0){
    addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, this.counter)));
}
    }

    @Override
    public void onUnequip() {
        super.onUnequip();

        this.counter = 0;
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
    return DESCRIPTIONS[0]+this.BoneFragmentCost + DESCRIPTIONS[1] + DamageAltarGain + DESCRIPTIONS[2] + AltarCap + DESCRIPTIONS [3];

    }


    @Override
    public void onRightClick() {
if(AbstractDungeon.getCurrRoom() instanceof RestRoom){
    if(FragmentCount >= this.BoneFragmentCost){
        if(this.counter != this.AlarMaxUses){
            FragmentGain += -this.BoneFragmentCost;
            this.counter += 1;
            getUpdatedDescription();
        }

    }
}
    }
}
