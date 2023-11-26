package skulmod.cards.Cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SlimeMod extends AbstractCardModifier {
    private float SlimeMultBase;
    private boolean SlimeApplied = false;

    @Override
    public String identifier(AbstractCard card) {
        return "SlimeMod";
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if(SlimeApplied != true){
            SlimeMultBase = AbstractDungeon.cardRandomRng.random(25, 200);
            SlimeApplied = true;
        }
    }
    @Override
    public String modifyName(String cardName, AbstractCard card) {
        if(card.type != AbstractCard.CardType.CURSE && card.type != AbstractCard.CardType.STATUS){
            cardName = cardName + " " + (int) SlimeMultBase + "%";
        }
        return super.modifyName(cardName, card);
    }

    @Override
    public float modifyBaseDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        if(card.type != AbstractCard.CardType.CURSE && card.type != AbstractCard.CardType.STATUS) {
            damage = damage *= SlimeMultBase / 100;
            damage = Math.max(damage, 1);
        }
        return super.modifyBaseDamage(damage, type, card, target);
    }

    @Override
    public float modifyBaseBlock(float block, AbstractCard card) {
        if(card.type != AbstractCard.CardType.CURSE && card.type != AbstractCard.CardType.STATUS) {
            block = block *= SlimeMultBase / 100;
            block = Math.max(block, 1);
        }
        return super.modifyBaseBlock(block, card);
    }

    @Override
    public float modifyBaseMagic(float magic, AbstractCard card) {
        if(card.type != AbstractCard.CardType.CURSE && card.type != AbstractCard.CardType.STATUS) {
            magic = magic *= SlimeMultBase / 100;
            magic = Math.max(magic, 1);
        }
        return super.modifyBaseMagic(magic, card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new SlimeMod();
    }
}
