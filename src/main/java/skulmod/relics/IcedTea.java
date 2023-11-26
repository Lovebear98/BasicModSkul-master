package skulmod.relics;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.PowerTip;
import skulmod.character.LittleBone;

import static java.lang.Boolean.FALSE;
import static skulmod.SkulMod.makeID;

@NoCompendium
public class IcedTea extends BaseRelic {
    private static final String NAME = "IcedTea"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.SOLID; //The sound played when the relic is clicked.
    private Boolean FirstSwap = FALSE;
    private boolean Processing = FALSE;

    public IcedTea() {

        super(ID, NAME, LittleBone.Enums.CARD_COLOR, RARITY, SOUND);
        this.counter = 44;
        refreshTips();
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
        refreshTips();
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();

    }

    @Override
    public void onUnequip() {
        super.onUnequip();
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {


    }

    @Override
    public int onLoseHpLast(int damageAmount) {
        if(this.counter != 0){
            if(damageAmount > this.counter){
                damageAmount -= this.counter;
                this.counter = 0;
            }else{
                this.counter -= damageAmount;
                damageAmount = 0;
            }
        }
        if(this.counter <= 0){
            this.grayscale = true;
            this.usedUp = true;
            refreshTips();
        }

        refreshTips();
        return super.onLoseHpLast(damageAmount);

    }

    public void setCounter(int setCounter) {
        this.counter = setCounter;
        if (setCounter <= 0) {
            this.grayscale = true;
            this.usedUp = true;
            refreshTips();
        }

    }
// Take advantage of autocomplete!
    // If you type "public onUse" IntelliJ should already have the method in the suggestions.
    // Use the up/down arrows to select it and press enter to automatically create this whole chunk.
    // This autocomplete is also a good way to see all the hooks/look for the right hook by name, by just typing "publi"

public void refreshTips(){
    if(this.usedUp){
        this.description = this.DESCRIPTIONS[2];
    }else{
        this.description = this.DESCRIPTIONS[0] + counter + this.DESCRIPTIONS[1];
    }
    this.tips.clear();
    this.tips.add(new PowerTip(this.name, this.description));
    this.initializeTips();
}


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]+counter+DESCRIPTIONS[1];
    }




}
