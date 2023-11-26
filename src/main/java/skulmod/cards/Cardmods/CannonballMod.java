package skulmod.cards.Cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CannonballMod extends AbstractCardModifier {
    private static boolean MadeChargeGlow;
    private int OriginalCost = 0;
    private float Multiplier;

    @Override
    public String identifier(AbstractCard card) {
        return "CannonballMod";
    }

    @Override
    public void onInitialApplication(AbstractCard card) {


    }


    @Override
    public float modifyBaseDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        damage = damage += card.baseDamage;
        return super.modifyBaseDamage(damage, type, card, target);
    }




    @Override
    public AbstractCardModifier makeCopy() {
        return new CannonballMod();
    }
}
