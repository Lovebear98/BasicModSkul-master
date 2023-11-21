package skulmod.cards.Cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.attack.warrior.PowerStrike;

public class ChargeModifier extends AbstractCardModifier {
    private static boolean MadeChargeGlow;
    private int OriginalCost = 0;
    private float Multiplier;

    @Override
    public String identifier(AbstractCard card) {
        return "ChargeModifier";
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        OriginalCost = card.cost;
        if(OriginalCost == 0){
            Multiplier = 3;
        }
        if(OriginalCost == 1){
            Multiplier = 3;
        }
        if(OriginalCost == 2){
            Multiplier = 1.5F;
        }
        if(OriginalCost >= 3){
            Multiplier = 1;
        }
        card.cost = 3;
        card.costForTurn = 3;
        if(card instanceof PowerStrike){
            if(card.magicNumber > 0) {
                card.cost -= card.magicNumber;
                card.costForTurn -= card.magicNumber;
                if(card.cost < 0){
                    card.cost = 0;
                }
                if(card.costForTurn < 0){
                    card.costForTurn = 0;
                }
            }
        }
        card.isCostModified = true;
        card.tags.add(AbstractCard.CardTags.STRIKE);

    }

    @Override
    public String modifyName(String cardName, AbstractCard card) {
if(card.upgraded){
    cardName = "Charged Strike+";
}else{
    cardName = "Charged Strike";
}
        return super.modifyName(cardName, card);
    }


    @Override
    public float modifyBaseDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        damage = damage * Multiplier;
        return super.modifyBaseDamage(damage, type, card, target);
    }
    @Override
    public float modifyBaseBlock(float block, AbstractCard card) {
        block = block * Multiplier;
        return super.modifyBaseBlock(block, card);
    }
    @Override
    public float modifyBaseMagic(float magic, AbstractCard card) {
        magic = magic * Multiplier;
        return super.modifyBaseMagic(magic, card);
    }


    @Override
    public AbstractCardModifier makeCopy() {
        return new ChargeModifier();
    }
}
