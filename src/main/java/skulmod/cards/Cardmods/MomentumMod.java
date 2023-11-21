package skulmod.cards.Cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MomentumMod extends AbstractCardModifier {


    private final int magicNumber;

    public MomentumMod(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public String identifier(AbstractCard card) {
        return "MomentumMod";
    }


    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }



    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        damage += magicNumber;
        return super.modifyDamage(damage, type, card, target);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new MomentumMod(magicNumber);
    }
}
