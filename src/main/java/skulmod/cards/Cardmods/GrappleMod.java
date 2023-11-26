package skulmod.cards.Cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.powers.custompowers.HerculeanStrength;

public class GrappleMod extends AbstractCardModifier {


    @Override
    public String identifier(AbstractCard card) {
        return "GrappleMod";
    }

    @Override
    public void onInitialApplication(AbstractCard card) {


    }
    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        if(AbstractDungeon.player.hasPower(HerculeanStrength.POWER_ID)){
            int HercNum = AbstractDungeon.player.getPower(HerculeanStrength.POWER_ID).amount;
            for(int Loops = HercNum; Loops >0; Loops -= 1){
                damage *= 1.5f;
            }
        }
        return super.modifyDamage(damage, type, card, target);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new GrappleMod();
    }
}
