package skulmod.relics;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import skulmod.character.LittleBone;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class BestFriend extends BaseRelic {
    private static final String NAME = "BestFriend"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SPECIAL; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.HEAVY; //The sound played when the relic is clicked.
    public BestFriend() {
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
    public void onUnequip() {
        Reassemble.DeathByGuilt = TRUE;
        addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 99999));
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
        if(AbstractDungeon.cardRandomRng.random(9)+1 > 7){
            this.flash();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            if (AbstractDungeon.player.currentHealth > 0) {
                AbstractDungeon.player.heal(AbstractDungeon.cardRandomRng.random(3)+1);
            }
        }
        super.onVictory();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


}
