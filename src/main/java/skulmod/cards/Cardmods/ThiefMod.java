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

public class ThiefMod extends AbstractCardModifier {
    public static final String ID = makeID("NonCardKeywords");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private int GoldCount = 0;
    private int EndCount = 24;


    @Override
    public String identifier(AbstractCard card) {
        return "ThiefMod";
    }

    @Override
    public void onInitialApplication(AbstractCard card) {

    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCreature m = target;
        super.onUse(card, target, action);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, card.damage, card.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new GainGoldAction(card.magicNumber));
        GoldCount += card.magicNumber;

        if(GoldCount >= EndCount){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, card.block));
        }
        card.initializeDescription();
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        int GoldLeft = EndCount - GoldCount;
if(GoldCount >= EndCount){
    rawDescription += cardStrings.EXTENDED_DESCRIPTION[11];
}else{
    rawDescription += cardStrings.EXTENDED_DESCRIPTION[9]+GoldLeft+cardStrings.EXTENDED_DESCRIPTION[10];
}
        return super.modifyDescription(rawDescription, card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ThiefMod();
    }
}
