package skulmod.cards.power.SkullPowers;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import skulmod.CustomTags;
import skulmod.cards.BaseCard;
import skulmod.cards.generated.unrivaledstrike.StrategyBook;
import skulmod.cards.generated.unrivaledstrike.UnrivaledStrike;
import skulmod.character.LittleBone;
import skulmod.powers.custompowers.skulls.GodOfWarPower;
import skulmod.util.CardInfo;
import skulmod.util.CustomActions.SkullActions.GotSkullAction;
import skulmod.util.Patches.NotInDeck.StartExhaustedField;
import skulmod.util.animation.AtlasPaths;

import java.util.ArrayList;

import static skulmod.SkulMod.makeID;
import static skulmod.util.CustomActions.SkullActions.ChooseASkull.ActiveSkull;

public class GodOfWarSkull extends BaseCard implements SpawnModificationCard {
    private final static CardInfo cardInfo = new CardInfo(
            "GodOfWarSkull",
            -2,
            CardType.POWER,
            CardTarget.NONE,
            CardRarity.UNCOMMON,
            LittleBone.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);
    public static final String[] EXTENDED_DESCRIPTION = CardStrings.getMockCardString().EXTENDED_DESCRIPTION;
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;



    public GodOfWarSkull() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
        tags.add(CustomTags.Skull);
        tags.add(CardTags.HEALING);
        StartExhaustedField.startexhausted.set(this, true);
        SoulboundField.soulbound.set(this, true);
        MultiCardPreview.add(this, new UnrivaledStrike(), new StrategyBook());
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

    public GodOfWarSkull(CardInfo cardInfo) {
        super(cardInfo);
    }
    @Override
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
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
    public void onObtainCard() {
        addToTop(new GotSkullAction());
}

    @Override
    public void onChoseThisOption() {
        super.onChoseThisOption();
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (new GodOfWarPower(AbstractDungeon.player, -1))));
        if(AbstractDungeon.player instanceof LittleBone){
            ((LittleBone) (AbstractDungeon.player)).AnimateSkull(AtlasPaths.GodOfWarAnim(), "IDLE");

        }
        ActiveSkull = this;
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
        return new GodOfWarSkull();
    }
}