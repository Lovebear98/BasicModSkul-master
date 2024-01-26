package skulmod.relics;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import skulmod.cards.power.SkullPowers.*;
import skulmod.character.LittleBone;

import static skulmod.SkulMod.makeID;

@NoCompendium
public class HuntersGift extends BaseRelic {

    private CardGroup SkullList = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private boolean cardsSelected = true;
    private static final String NAME = "HuntersGift"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.SOLID; //The sound played when the relic is clicked.

    public HuntersGift() {

        super(ID, NAME, LittleBone.Enums.CARD_COLOR, RARITY, SOUND);
    }


    public void onEquip() {
        this.cardsSelected = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }

        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        if(SkullList.isEmpty() || SkullList == null){
            SetUpSkullList();
        }
        if(!SkullList.isEmpty()){
            AbstractDungeon.gridSelectScreen.open(SkullList, 1, this.DESCRIPTIONS[1], false, false, false, true);
        }

    }

    public void update() {
        super.update();
        if (!this.cardsSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
            this.ObtainSkull(AbstractDungeon.gridSelectScreen.selectedCards.get(0));
        }

    }

    public void ObtainSkull(AbstractCard card) {
        this.cardsSelected = true;
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(card, (float) Settings.WIDTH /2, (float) Settings.HEIGHT /2));
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
        AbstractDungeon.gridSelectScreen.selectedCards.clear();
    }

    private void SetUpSkullList(){
        SkullList.addToBottom(new AbsoluteSkull());
        SkullList.addToBottom(new BerserkerSkull());
        SkullList.addToBottom(new ChampionSkull());
        SkullList.addToBottom(new ClownSkull());
        SkullList.addToBottom(new DarkPaladinSkull());
        SkullList.addToBottom(new DavyJonesSkull());
        SkullList.addToBottom((new FrostSkull()));
        SkullList.addToBottom(new GamblerSkull());
        SkullList.addToBottom((new GodOfWarSkull()));
        SkullList.addToBottom(new GreatWarlockSkull());
        SkullList.addToBottom(new HunterSkull());
        SkullList.addToBottom(new LittleBoneSkull());
        SkullList.addToBottom((new MageSkull()));
        SkullList.addToBottom(new PettyThiefSkull());
        SkullList.addToBottom(new ShieldSkull());
        SkullList.addToBottom(new WarriorSkull());
        SkullList.addToBottom(new WerewolfSkull());



        SkullList.sortAlphabetically(true);
    };



    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }




}
