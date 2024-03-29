package skulmod.cards.skill.davyjones;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;
import skulmod.util.CustomActions.SkullActions.BonepileAction;
import skulmod.util.vars;

import static skulmod.SkulMod.makeID;
import static skulmod.util.CustomActions.SkullActions.ChooseASkull.ActiveSkull;

public class LowerTheAnchor extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "LowerTheAnchor",
            0,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.UNCOMMON,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;



    public LowerTheAnchor() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void update() {
        super.update();

    }

    @Override
    public void applyPowers() {
        super.applyPowers();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

    public LowerTheAnchor(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(1, vars.Discard(), true, true, card -> true, ToExhaust -> {
            for (AbstractCard c : ToExhaust) {
                addToTop(new DiscardSpecificCardAction(c, AbstractDungeon.player.hand));
                for(int Loops = magicNumber; Loops > 0; Loops -= 1){
                    addToBot(new BonepileAction(p, this, m, ActiveSkull));
                }
            }
        }));

    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new LowerTheAnchor();
    }
}