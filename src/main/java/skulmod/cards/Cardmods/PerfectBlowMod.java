package skulmod.cards.Cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;

import static skulmod.SkulMod.makeID;

public class PerfectBlowMod extends AbstractCardModifier {
    @Override
    public String identifier(AbstractCard card) {
        return "PerfectBlowModMod";
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.tags.add(AbstractCard.CardTags.STRIKE);
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }



    @Override
    public AbstractCardModifier makeCopy() {
        return new PerfectBlowMod();
    }
}
