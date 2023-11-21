package skulmod.cards.attack.shield;

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
import skulmod.util.CustomActions.SkullActions.BonepileAction;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;
import static skulmod.util.CustomActions.SkullActions.ChooseASkull.ActiveSkull;

public class ShieldSmash extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ShieldSmash",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 2;



    public ShieldSmash() {
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

        if(AbstractDungeon.player.currentBlock != 0) {
            this.baseDamage = AbstractDungeon.player.currentBlock;
            this.isDamageModified = TRUE;
        }else{
            this.baseDamage = 1;
        }
            super.applyPowers();

    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

    public ShieldSmash(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.currentBlock != 0){
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SHIELD));

    }
        for(int Loops = magicNumber; Loops > 0; Loops -= 1){
            addToBot(new BonepileAction(p, this, m, ActiveSkull));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ShieldSmash();
    }
}