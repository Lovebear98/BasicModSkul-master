package skulmod.cards.attack.pettythief;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;
import static skulmod.util.Patches.SkullPatches.ThiefGoldDamage.RecklessAttackPatch.GoldInCombat;

public class ThrowGold extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ThrowGold",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 0;

    private int GoldCost;
    private int BaseGoldCost = 30;
    private int UpgradedGoldCost = 15;


    public ThrowGold() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
    }

    @Override
    public void update() {
        super.update();

    }

    @Override
    public void applyPowers() {
        if(this.upgraded == FALSE){
            GoldCost = BaseGoldCost;
        } else {
            this.GoldCost = UpgradedGoldCost;
        }
        this.baseDamage = GoldInCombat;
        super.applyPowers();
        if(this.baseDamage != 0){
            isDamageModified = TRUE;
        }
    }

    @Override
    public void upgrade() {
        this.GoldCost = UpgradedGoldCost;
        super.upgrade();

    }

    public ThrowGold(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {

            return super.canUse(p, m);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.loseGold(GoldCost);
addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));

    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ThrowGold();
    }
}