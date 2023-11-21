package skulmod.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnPlayerDeathRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.MarkOfTheBloom;
import skulmod.character.LittleBone;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class Reassemble extends BaseRelic implements OnPlayerDeathRelic {
    private static final String NAME = "Reassemble"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.HEAVY; //The sound played when the relic is clicked.
    public Reassemble() {
        super(ID, NAME, LittleBone.Enums.CARD_COLOR, RARITY, SOUND);
    }
    public static Boolean DeathByGuilt = FALSE;

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
    public void onUnequip() {
        super.onUnequip();
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
    public void onVictory() {
        super.onVictory();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    public void setCounter(int setCounter) {
        if (setCounter == -2) {
            this.usedUp();
            this.counter = -2;
        }

    }

    @Override
    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {
        if(AbstractDungeon.player.hasRelic(MarkOfTheBloom.ID)){
            return true;
        }
        if(!this.usedUp){
            this.flash();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.player.heal(30, true);
            this.setCounter(-2);
                if(DeathByGuilt == TRUE){
                    DeathByGuilt = FALSE;
                }else{
                    this.usedUp();
                }
            return false;
        }else{
            return true;
        }

    }
}
