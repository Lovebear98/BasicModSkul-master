package skulmod.cards.attack.darkpaladin;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.powers.custompowers.DarkPower;
import skulmod.util.CardInfo;

import static skulmod.SkulMod.makeID;

public class DarkCross extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "DarkCross",
            3,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.RARE,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;



    public DarkCross() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        setCostUpgrade(2);

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

    public DarkCross(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        int BlockToGain = 0;
        if(AbstractDungeon.player.hasPower(VigorPower.POWER_ID)){
            BlockToGain += AbstractDungeon.player.getPower(VigorPower.POWER_ID).amount;
        }
        if(AbstractDungeon.player.hasPower(DarkPower.POWER_ID)){
            BlockToGain += AbstractDungeon.player.getPower(DarkPower.POWER_ID).amount;
        }
for(int Loops = magicNumber; Loops > 0; Loops -= 1){
    addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
}

        if(BlockToGain > 0){
            addToBot(new GainBlockAction(p, BlockToGain));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DarkCross();
    }
}