package skulmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.WrathStance;
import skulmod.cards.power.SkullPowers.ChampionSkull;
import skulmod.character.LittleBone;

import java.util.Objects;

import static skulmod.SkulMod.makeID;
import static skulmod.util.vars.RelicSpawn;

public class ChampionshipMatch extends BaseRelic {
    private static final String NAME = "ChampionshipMatch"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.UNCOMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.FLAT; //The sound played when the relic is clicked.

    public ChampionshipMatch() {

        super(ID, NAME, LittleBone.Enums.CARD_COLOR, RARITY, SOUND);
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
        super.atBattleStart();

    }

    @Override
    public void onPlayerEndTurn() {
        if(Objects.equals(AbstractDungeon.player.stance.ID, WrathStance.STANCE_ID)){
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1)));
            this.counter += 1;
        }
        super.onPlayerEndTurn();
    }

    @Override
    public boolean canSpawn() {
        if(RelicSpawn()){
            for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                if (c.cardID.equals(ChampionSkull.ID)) {
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        if((!Objects.equals(prevStance.ID, newStance.ID)) && !Objects.equals(newStance.ID, WrathStance.STANCE_ID)){
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -this.counter)));
            this.counter = 0;
        }
        super.onChangeStance(prevStance, newStance);
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
