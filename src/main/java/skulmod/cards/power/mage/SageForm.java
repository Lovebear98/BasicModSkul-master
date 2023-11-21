package skulmod.cards.power.mage;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.BaseCard;
import skulmod.cards.power.SkullPowers.GreatWarlockSkull;
import skulmod.cards.power.SkullPowers.MageSkull;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;

import java.util.ArrayList;

import static skulmod.SkulMod.makeID;

public class SageForm extends BaseCard implements SpawnModificationCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SageForm",
            2,
            CardType.POWER,
            CardTarget.NONE,
            CardRarity.RARE,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 0;



    public SageForm() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        setEthereal(true, false);



    }
    @Override
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
        //Player MUST have the Skull associated with the form.
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals(MageSkull.ID)) {
                return true;
            }
        }

        //Card will spawn.
        return false;
    }

    @Override
    public boolean canSpawnShop(ArrayList<AbstractCard> currentShopCards) {
        //Player MUST have the Skull associated with the form.
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals(MageSkull.ID)) {
                return true;
            }
        }

        //Card will spawn.
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

    public SageForm(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new skulmod.powers.custompowers.forms.SageForm(p, magicNumber)));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SageForm();
    }
}