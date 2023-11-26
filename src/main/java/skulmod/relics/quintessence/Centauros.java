package skulmod.relics.quintessence;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import skulmod.character.LittleBone;
import skulmod.powers.custompowers.WedgedSpear;
import skulmod.relics.BaseRelic;

import static skulmod.SkulMod.makeID;


public class Centauros extends BaseRelic implements ClickableRelic {
    private static final String NAME = "Centauros"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.UNCOMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.
    private static final RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static boolean GotSpear = false;
    private int QuintNumber = 9;

    public Centauros() {

        super(ID, NAME, LittleBone.Enums.CARD_COLOR, RARITY, SOUND);

    }

    @Override
    public void atTurnStartPostDraw() {

    }

    @Override
    public void onObtainCard(AbstractCard c) {
        super.onObtainCard(c);
    }

    @Override
    public void update() {
        if(GotSpear == true){
            Refresh();
            GotSpear = false;
        }
        super.update();
    }

    @Override
    public void onEquip() {

        super.onEquip();
    }

    @Override
    public void atBattleStart() {
        Refresh();
        super.atBattleStart();

    }

    @Override
    public void onUnequip() {
        super.onUnequip();
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {


    }




// Take advantage of autocomplete!
    // If you type "public onUse" IntelliJ should already have the method in the suggestions.
    // Use the up/down arrows to select it and press enter to automatically create this whole chunk.
    // This autocomplete is also a good way to see all the hooks/look for the right hook by name, by just typing "publi"




    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    @Override
    public void onRightClick() {
        if(CardCrawlGame.isInARun() && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){
if(!this.usedUp){
    AbstractMonster mo = AbstractDungeon.getMonsters().getRandomMonster(true);
if(mo != null){
    addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new WedgedSpear(mo, 3)));
    AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(AbstractDungeon.player, QuintNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    this.usedUp();
}

}
    }
    }

    private void Refresh() {
        this.grayscale = false;
        this.usedUp = false;
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

}
