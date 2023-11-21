package skulmod.events;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.EchoForm;
import com.megacrit.cardcrawl.cards.green.WraithForm;
import com.megacrit.cardcrawl.cards.purple.DevaForm;
import com.megacrit.cardcrawl.cards.red.DemonForm;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import skulmod.cards.power.SkullPowers.*;
import skulmod.cards.power.champion.RageMode;
import skulmod.cards.power.clown.EntertainerForm;
import skulmod.cards.power.darkpaladin.AvengerForm;
import skulmod.cards.power.davyjones.BlackbeardForm;
import skulmod.cards.power.frostskull.FrozenSoulForm;
import skulmod.cards.power.gambler.AllInForm;
import skulmod.cards.power.greatwarlock.AbyssForm;
import skulmod.cards.power.hunter.SniperForm;
import skulmod.cards.power.mage.SageForm;
import skulmod.cards.power.pettythief.OutlawForm;
import skulmod.cards.power.shield.GuardianForm;
import skulmod.cards.power.warrior.WarlordForm;
import skulmod.cards.power.werewolf.EternalForm;

import java.util.Objects;

import static skulmod.SkulMod.makeID;
import static skulmod.util.CustomActions.SkullActions.GotSkullAction.getSkullsInDeck;


public class CocoonEvent extends AbstractImageEvent {

    public static final String ID = makeID("Cocoon");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String NAME = eventStrings.NAME;
    private final CardGroup CardList;


    private State state;

    private enum State {
        CHOOSING,
        LEAVING
    }

    public CocoonEvent() {
        super(NAME, DESCRIPTIONS[0], "skulmod/events/Cocoon.png");
        this.imageEventText.setDialogOption(OPTIONS[0]); //Form Card
        this.imageEventText.setDialogOption(OPTIONS[1]); //ANY Form Card
        this.imageEventText.setDialogOption(OPTIONS[2]); //Random Power
        this.imageEventText.setDialogOption(OPTIONS[3]); //Nope Out
        state = State.CHOOSING;
        AbstractDungeon.getCurrRoom().rewards.clear();
        this.CardList = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    }



    @Override
    public void update() {
        super.update();
        if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            final AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(c, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            CardList.group.clear();
        }
    }



    @Override
    protected void buttonEffect(int buttonPressed) {
        switch(state) {
            case CHOOSING:
                switch(buttonPressed) {
                    case 0:
                        ///Valid Form
                        SkulCocoonGetValidForms(false);
                        this.imageEventText.loadImage("skulmod/events/Cocoon2.png");
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        this.state = State.LEAVING;
                        AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
                        AbstractDungeon.gridSelectScreen.open(CardList, 1, OPTIONS[4], false, false, false, false);
                        break;
                    case 1:
                        ///ANY Form from Skull and basegame
                        SkulCocoonGetValidForms(true);
                        this.imageEventText.loadImage("skulmod/events/Cocoon2.png");
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        this.state = State.LEAVING;
                        AbstractCard c = CardList.getRandomCard(true);
                        AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
                        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(c, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                        break;
                    case 2:
                        ///Random Power
                        SkulCocoonGetRandomPower();
                        this.imageEventText.loadImage("skulmod/events/Cocoon2.png");
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        this.state = State.LEAVING;
                        AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
                        AbstractDungeon.gridSelectScreen.open(CardList, 1, OPTIONS[4], false, false, false, false);
                        break;
                    case 3:///Leave
                        imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        this.state = State.LEAVING;
                        AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
                        break;
                    default:
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        this.state = State.LEAVING;
                        AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
                        break;
                }

                break;
            case LEAVING:
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[5]);
                openMap();
                break;
        }
    }

    private void SkulCocoonGetRandomPower() {
            AbstractCard.CardRarity Rarity = null;
            int RandNum = AbstractDungeon.eventRng.random(1, 3);
            switch(RandNum){
                case 1: Rarity = AbstractCard.CardRarity.COMMON;
                    break;
                case 2: Rarity = AbstractCard.CardRarity.UNCOMMON;
                    break;
                case 3: Rarity = AbstractCard.CardRarity.RARE;
                    break;
            }
            AbstractCard card = CardLibrary.getAnyColorCard(AbstractCard.CardType.POWER, Rarity);
            AbstractCard card2 = CardLibrary.getAnyColorCard(AbstractCard.CardType.POWER, Rarity);
            AbstractCard card3 = CardLibrary.getAnyColorCard(AbstractCard.CardType.POWER, Rarity);
            CardList.group.add(card);
            CardList.group.add(card2);
            CardList.group.add(card3);
    }


    private void SkulCocoonGetValidForms(boolean All) {
        CardGroup SkullsOwned = getSkullsInDeck(AbstractDungeon.player.masterDeck);
        boolean FoundSkull;
        if(SkullsOwned.isEmpty()){
            SkullsOwned.addToBottom((new LittleBoneSkull()));
        }
        for(AbstractCard c : SkullsOwned.group){
            if(Objects.equals(c.cardID, ChampionSkull.ID) || All){
               CardList.group.add(new RageMode());
            }
            if(Objects.equals(c.cardID, ClownSkull.ID) || All){
                CardList.group.add(new EntertainerForm());
            }
            if(Objects.equals(c.cardID, DarkPaladinSkull.ID) || All){
                CardList.group.add(new AvengerForm());
            }
            if(Objects.equals(c.cardID, DavyJonesSkull.ID) || All){
                CardList.group.add(new BlackbeardForm());
            }
            if(Objects.equals(c.cardID, FrostSkull.ID) || All){
                CardList.group.add(new FrozenSoulForm());
            }
            if(Objects.equals(c.cardID, GamblerSkull.ID) || All){
                CardList.group.add(new AllInForm());
            }
            if(Objects.equals(c.cardID, GreatWarlockSkull.ID) || All){
                CardList.group.add(new AbyssForm());
            }
            if(Objects.equals(c.cardID, HunterSkull.ID) || All){
                CardList.group.add(new SniperForm());
            }
            if(Objects.equals(c.cardID, MageSkull.ID) || All){
                CardList.group.add(new SageForm());
            }
            if(Objects.equals(c.cardID, PettyThiefSkull.ID) || All){
                CardList.group.add(new OutlawForm());
            }
            if(Objects.equals(c.cardID, ShieldSkull.ID) || All){
                CardList.group.add(new GuardianForm());
            }
            if(Objects.equals(c.cardID, WarriorSkull.ID) || All){
                CardList.group.add(new WarlordForm());
            }
            if(Objects.equals(c.cardID, WerewolfSkull.ID) || All){
                CardList.group.add(new EternalForm());
            }
            if(All){
                CardList.group.add(new DemonForm());
                CardList.group.add(new WraithForm());
                CardList.group.add(new EchoForm());
                CardList.group.add(new DevaForm());
                CardList.group.add(new DemonForm());
                CardList.group.add(new WraithForm());
                CardList.group.add(new EchoForm());
                CardList.group.add(new DevaForm());
            }
            if(CardList.group.isEmpty()){
                CardList.group.add(new LittleBoneSkull());
            }
        }
    }
}