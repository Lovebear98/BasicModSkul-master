package skulmod.potions;

import basemod.abstracts.CustomPotion;
import basemod.devcommands.energy.Energy;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import skulmod.util.CustomActions.SkullActions.ChooseASkull;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;

public class SwapPotion extends CustomPotion {
    public static final String POTION_ID = makeID("SwapPotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public SwapPotion() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.HEART, PotionColor.STRENGTH);
        this.isThrown = false;
        this.targetRequired = false;
        this.labOutlineColor = Color.WHITE.cpy();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(potionStrings.DESCRIPTIONS[1], potionStrings.DESCRIPTIONS[2]));
    }


    public void initializeData() {
        this.potency = 6-getPotency();
            this.description = potionStrings.DESCRIPTIONS[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(potionStrings.DESCRIPTIONS[1], potionStrings.DESCRIPTIONS[2]));
    }

    @Override
    public boolean canUse() {
        if(EnergyPanel.totalCount < 1){
            return false;
        }
        return super.canUse();
    }

    public void use(AbstractCreature target) {
if(EnergyPanel.totalCount >= 1){
    addToBot(new LoseEnergyAction(1));
}
        AbstractDungeon.actionManager.addToBottom(new ChooseASkull(AbstractDungeon.player, TRUE));
    }




    public CustomPotion makeCopy() {
        return new SwapPotion();
    }

    @Override
    public int getPotency() {
        return super.getPotency();
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }
}
