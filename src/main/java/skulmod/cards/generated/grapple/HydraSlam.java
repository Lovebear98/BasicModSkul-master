package skulmod.cards.generated.grapple;

import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import skulmod.CustomTags;
import skulmod.cards.BaseCard;
import skulmod.cards.Cardmods.GrappleMod;
import skulmod.character.LittleBone;
import skulmod.powers.custompowers.HerculeanStrength;
import skulmod.util.CardInfo;

import static skulmod.SkulMod.makeID;
@NoCompendium
public class HydraSlam extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "HydraSlam",
            0,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.SPECIAL,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 1;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 0;

    String BASEIMG = "skulmod/cards/attack/HydraSlam.png";
    String SUPERIMG = "skulmod/cards/attack/HydraSlamE.png";
    private boolean Enhanced = false;



    public HydraSlam() {
        super(cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        tags.add(CustomTags.Grapple);
        setExhaust(true);
        setSelfRetain(true);
        CardModifierManager.addModifier(this, new GrappleMod());
        this.isMultiDamage = true;
    }

    @Override
    public void update() {
        super.update();

    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        boolean CheckAgainst = Enhanced;
        if(AbstractDungeon.player.hasPower(HerculeanStrength.POWER_ID)){
            if(AbstractDungeon.player.getPower(HerculeanStrength.POWER_ID).amount >= 3){
                this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
                loadCardImage(SUPERIMG);
                Enhanced = true;
            }else{
                this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
                loadCardImage(BASEIMG);
                Enhanced = false;
            }
        }else{
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            loadCardImage(BASEIMG);
            Enhanced = false;
        }

        if(CheckAgainst != Enhanced){
            initializeDescription();
        }
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

    public HydraSlam(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {

    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.hasPower(HerculeanStrength.POWER_ID)){
            if(AbstractDungeon.player.getPower(HerculeanStrength.POWER_ID).amount >= 3){
                addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
            }else{
                addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }else{
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
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
        return new HydraSlam();
    }
}