package skulmod.cards.power.SkullPowers;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import skulmod.CustomTags;
import skulmod.cards.BaseCard;
import skulmod.character.LittleBone;
import skulmod.powers.custompowers.skulls.BomberSkullPower;
import skulmod.powers.custompowers.skulls.LittleBonePower;
import skulmod.util.CardInfo;
import skulmod.util.CustomActions.SkullActions.GotSkullAction;
import skulmod.util.Patches.NotInDeck.StartExhaustedField;
import skulmod.util.animation.AtlasPaths;

import java.util.ArrayList;

import static skulmod.SkulMod.makeID;
import static skulmod.util.CustomActions.SkullActions.ChooseASkull.ActiveSkull;
@NoCompendium
public class BomberSkull extends BaseCard implements SpawnModificationCard {
    private final static CardInfo cardInfo = new CardInfo(
            "BomberSkull",
            -2,
            CardType.POWER,
            CardTarget.NONE,
            CardRarity.SPECIAL,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;



    public BomberSkull() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        tags.add(CustomTags.Skull);
        tags.add(CardTags.HEALING);
        StartExhaustedField.startexhausted.set(this, true);
        SoulboundField.soulbound.set(this, true);
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

    public BomberSkull(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void onObtainCard() {
        addToTop(new GotSkullAction());
}
    @Override
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
        //Player can't already have the card.
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals(this.cardID)) {
                return false;
            }
        }
        //To slightly reduce the spawn rate of Skulls so that they don't flood card rewards
        //--Roll a random chance of 0-99
        //int SkullChance = AbstractDungeon.cardRng.random(99);
        ///--If we land at or above 84
        //if(SkullChance >= 84){
            ///--Skulls won't spawn
            //return false;
        //}
        //Card will spawn.
        return true;
    }

    @Override
    public boolean canSpawnShop(ArrayList<AbstractCard> currentShopCards) {
        //Player can't already have the card.
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals(this.cardID)) {
                return false;
            }
        }

        //Card will spawn.
        return true;
    }
    @Override
    public void onChoseThisOption() {
        super.onChoseThisOption();
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new BomberSkullPower(AbstractDungeon.player, -1))));
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new EntanglePower(AbstractDungeon.player))));
        ActiveSkull = this;
        if(AbstractDungeon.player instanceof LittleBone){
            ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.BomberAnim(), "IDLE");

        }
    }

    @Override
    public void onRemoveFromMasterDeck() {
        super.onRemoveFromMasterDeck();
    }

    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BomberSkull();
    }
}