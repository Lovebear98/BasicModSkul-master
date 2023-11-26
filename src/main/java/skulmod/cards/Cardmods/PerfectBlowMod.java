package skulmod.cards.Cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

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
