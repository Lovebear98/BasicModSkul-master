package skulmod.cards.generated;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.util.CardInfo;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;
@NoCompendium
public class Awakening extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Awakening",
            2,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.SPECIAL,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 0;
    private int TotalEnergy;


    public Awakening() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        purgeOnUse = TRUE;
        setCostUpgrade(1);
        setEthereal(true);


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

    public Awakening(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.hasPower(VigorPower.POWER_ID)){
            int NumTimes = AbstractDungeon.player.getPower(VigorPower.POWER_ID).amount;
            for(int Loops = NumTimes; Loops > 0; Loops -= 1){
                addToBot(new DamageAction(m, new DamageInfo(p, magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            }
                addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, VigorPower.POWER_ID));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Awakening();
    }
}