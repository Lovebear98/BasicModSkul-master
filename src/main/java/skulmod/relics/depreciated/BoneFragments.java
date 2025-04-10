package skulmod.relics.depreciated;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import skulmod.character.LittleBone;
import skulmod.relics.BaseRelic;

import static skulmod.SkulMod.makeID;

public class BoneFragments extends BaseRelic implements ClickableRelic {
    private static final String NAME = "BoneFragments"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SPECIAL; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    public static int FragmentCount;
    public static int FragmentGain;

    public BoneFragments() {
        super(ID, NAME, LittleBone.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atTurnStartPostDraw() {

    }


    @Override
    public void update() {
        super.update();
        if(FragmentGain != 0) {
            this.flash();
            this.counter += FragmentGain;
            FragmentGain = 0;
        }
        if(FragmentCount != this.counter) {
                FragmentCount = this.counter;
            }
        if(this.counter < 0){
            this.counter = 0;
        }
        }

    @Override
    public void onEquip() {
        super.onEquip();
        this.counter = 0;
        FragmentCount = 0;
        FragmentGain = 0;




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
        return DESCRIPTIONS[0];
    }


    @Override
    public void onRightClick() {
        this.counter += 500;
    }
}
