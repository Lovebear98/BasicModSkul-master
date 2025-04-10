package skulmod.util.othermodskulls.unchained.cards;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;
import skulmod.util.othermodskulls.unchained.stances.RidingStance;

import static skulmod.SkulMod.makeID;

//@NoCompendium
public class RideOrDie extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "RideOrDie",
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

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 0;



    public RideOrDie() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        setEthereal(true, false);
        setExhaust(true);
    }

    @Override
    public boolean canUpgrade() {
        return false;
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

    public RideOrDie(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

}

    @Override
    public void onRemoveFromMasterDeck() {
        super.onRemoveFromMasterDeck();
    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.stance.ID.equals(RidingStance.STANCE_ID)){
            addToBot(new ChangeStanceAction(new NeutralStance()));
        }else{
            addToBot(new ChangeStanceAction(new RidingStance()));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new RideOrDie();
    }
}