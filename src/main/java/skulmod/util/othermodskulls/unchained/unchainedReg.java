package skulmod.util.othermodskulls.unchained;

import basemod.BaseMod;
import skulmod.SkulMod;
import skulmod.util.othermodskulls.unchained.cards.RideOrDie;
import skulmod.util.othermodskulls.unchained.cards.RiderSkull;

public class unchainedReg {
    public static void registercards() {
        SkulMod.logger.info("Adding Rider skull for Unchained!");
        BaseMod.addCard(new RiderSkull());
        BaseMod.addCard(new RideOrDie());
    }
}
