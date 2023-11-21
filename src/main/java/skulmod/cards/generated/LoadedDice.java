package skulmod.cards.generated;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.powers.custompowers.Lucky7;
import skulmod.util.CardInfo;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;
@NoCompendium
public class LoadedDice extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "LoadedDice",
            0,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.SPECIAL,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 7;
    private static final int UPG_MAGIC = 7;
    private AbstractCard thiscard = this;



    public LoadedDice() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        PurgeField.purge.set(this, true);

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

    public LoadedDice(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        super.triggerOnEndOfPlayerTurn();
        addToTop(new ShowCardAndPoofAction(this));
        addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractDungeon.player.hand.removeCard(thiscard);
                isDone = TRUE;
            }
        });
    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        int CardDraw;
        if(this.upgraded == TRUE){
            CardDraw = 2;
        }else{
            CardDraw = 1;
        }
    addToBot(new DrawCardAction(p, CardDraw));
    addToTop(new ApplyPowerAction(p, p, (new Lucky7(p, this.magicNumber))));
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();


    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new LoadedDice();
    }
}