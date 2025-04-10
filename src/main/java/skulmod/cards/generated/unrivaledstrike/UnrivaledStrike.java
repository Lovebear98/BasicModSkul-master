package skulmod.cards.generated.unrivaledstrike;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.BaseCard;
import skulmod.cards.Cardmods.UnrivaledStrikeMod;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;

import java.util.List;

import static skulmod.SkulMod.makeID;

//@NoCompendium
public class UnrivaledStrike extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "UnrivaledStrike",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.SPECIAL,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 0;



    public UnrivaledStrike() {
        super(cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        tags.add(CardTags.STRIKE);
        this.isMultiDamage = true;
        CardModifierManager.addModifier(this, new UnrivaledStrikeMod(false, 0, 0, 0, 0));
    }

    @Override
    public void update() {
        super.update();

    }



    @Override
    public void upgrade() {
        super.upgrade();
    }

    public UnrivaledStrike(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {

    }
    @Override
    public void applyPowers() {
        super.applyPowers();
        List<AbstractCardModifier> tensionMod = CardModifierManager.getModifiers(this, "UnrivaledStrikeMod");
        if (!tensionMod.isEmpty()) {
            if(((UnrivaledStrikeMod) tensionMod.get(0)).PeerlessBlow >= 1){
                this.target = CardTarget.ENEMY;
                this.isMultiDamage = false;
            }else{
                this.target = CardTarget.ALL_ENEMY;
                this.isMultiDamage = true;
            }
        }else{
            this.target = CardTarget.ALL_ENEMY;
            this.isMultiDamage = true;
        }
    }
    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        isMultiDamage = false;
        super.calculateCardDamage(mo);
        isMultiDamage = true;
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new UnrivaledStrike();
    }
}