package skulmod.relics;


import basemod.abstracts.CustomBottleRelic;
import basemod.abstracts.CustomSavable;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import skulmod.cards.Cardmods.PerfectBlowMod;
import skulmod.util.Patches.BottledPlaceholderField;

import java.util.Iterator;
import java.util.function.Predicate;

import static java.lang.Boolean.TRUE;
import static skulmod.SkulMod.makeID;
import static skulmod.util.vars.RelicSpawn;


public class PerfectBlow extends BaseRelic  implements CustomBottleRelic, CustomSavable<Integer> {
    private static final String NAME = "PerfectBlow"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SHOP; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.HEAVY; //The sound played when the relic is clicked.

private static AbstractCard card;
private Boolean cardSelected = TRUE;

    public PerfectBlow() {
        super(ID, NAME, RARITY, SOUND);
    }

    @Override
    public boolean canSpawn() {
        return RelicSpawn();
    }

    public Predicate<AbstractCard> isOnCard() {
        return BottledPlaceholderField.inBottledPlaceholderField::get;
    }

    @Override
    public Integer onSave() {
        if (card != null) {
            return AbstractDungeon.player.masterDeck.group.indexOf(card);
        } else {
            return -1;
        }
    }

    @Override
    public void onLoad(Integer cardIndex) {
        if (cardIndex == null) {
            return;
        }
        if (cardIndex >= 0 && cardIndex < AbstractDungeon.player.masterDeck.group.size()) {
            card = AbstractDungeon.player.masterDeck.group.get(cardIndex);
            if (card != null) {
                BottledPlaceholderField.inBottledPlaceholderField.set(card, true);
                setDescriptionAfterLoading();
            }
        }
    }
    @Override
    public void onEquip() { // 1. When we acquire the relic
        cardSelected = false; // 2. Tell the relic that we haven't bottled the card yet
        if (AbstractDungeon.isScreenUp) { // 3. If the map is open - hide it.
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        // 4. Set the room to INCOMPLETE - don't allow us to use the map, etc.
        CardGroup group = CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck); // 5. Get a card group of all currently unbottled cards
        AbstractDungeon.gridSelectScreen.open(group, 1, DESCRIPTIONS[3] + name + DESCRIPTIONS[2], false, false, false, false);
        // 6. Open the grid selection screen with the cards from the CardGroup we specified above. The description reads "Select a card to bottle for" + (relic name) + "."
    }
    @Override
    public void onUnequip() { // 1. On unequip
        if (card != null) { // If the bottled card exists (prevents the game from crashing if we removed the bottled card from our deck for example.)
            AbstractCard cardInDeck = AbstractDungeon.player.masterDeck.getSpecificCard(card); // 2. Get the card
            if (cardInDeck != null) {
                BottledPlaceholderField.inBottledPlaceholderField.set(cardInDeck, false); // In our SpireField - set the card to no longer be bottled. (Unbottle it)
            }
        }
    }

    @Override
    public void update() {
        super.update(); //Do all of the original update() method in AbstractRelic

        if (!cardSelected && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            // If the card hasn't been bottled yet and we have cards selected in the gridSelectScreen (from onEquip)
            cardSelected = true; //Set the cardSelected boolean to be true - we're about to bottle the card.
            card = AbstractDungeon.gridSelectScreen.selectedCards.get(0); // The custom Savable "card" is going to equal
            // The card from the selection screen (it's only 1, so it's at index 0)
            BottledPlaceholderField.inBottledPlaceholderField.set(card, true); // Use our custom spire field to set that card to be bottled.
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.INCOMPLETE) {
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            }
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE; // The room phase can now be set to complete (From INCOMPLETE in onEquip)
            AbstractDungeon.gridSelectScreen.selectedCards.clear(); // Always clear your grid screen after using it.
            setDescriptionAfterLoading(); // Set the description to reflect the bottled card (the method is at the bottom of this file)
        }
    }


    @Override
    public void atBattleStart() {
        boolean fullHandDialog = false; // Create a boolean (to prevent multiple "My hand is full!" dialogues if we have multiple cards bottled)

        for (Iterator<AbstractCard> it = AbstractDungeon.player.drawPile.group.iterator(); it.hasNext(); ) {
            // Create a new Iterator called "it" that checks for all AbstractCards in our draw pile. For each card:
            AbstractCard card = it.next(); // create a new AbstractCard named "card" which is equal to the current card in the for each loop
            if (BottledPlaceholderField.inBottledPlaceholderField.get(card)) { // Check if our SpireField matches said card
                // Essentially, we end up with: Check if the draw pile has a card that is bottled with this bottle

                // So, once we find a card that is bottled:

                this.flash(); // The relic flashes
                ////it.remove(); // Remove that card from the iterator (to prevent infinite loops)
                CardModifierManager.addModifier(card, new PerfectBlowMod());


            }
        }

            }



    // Change description after relic is already loaded to reflect the bottled card.
    public void setDescriptionAfterLoading() {
        this.description = DESCRIPTIONS[1] + FontHelper.colorString(card.name, "y") + DESCRIPTIONS[2];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }
    // Take advantage of autocomplete!
    // If you type "public onUse" IntelliJ should already have the method in the suggestions.
    // Use the up/down arrows to select it and press enter to automatically create this whole chunk.
    // This autocomplete is also a good way to see all the hooks/look for the right hook by name, by just typing "publi"


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}