package no.plasmid.movingguy.gui.event;

import no.plasmid.movingguy.service.ServiceManager;
import no.plasmid.movingguy.service.ProgramStateManager.ProgramState;

import org.lwjgl.input.Keyboard;

/**
 * Keyboard event listener for the exit menu item
 * 
 * Signals the application to exit
 * 
 * @author helgesk
 *
 */
public class ExitMenuItemKeyboardEventListener extends NoActionKeyboardEventListener {

	@Override
	protected boolean isConsumed(KeyboardEvent e) {
		//Consume up and down
		boolean rc = false;
		if (e.getKey() == Keyboard.KEY_RETURN || e.getKey() == Keyboard.KEY_SPACE) {
			rc = true;
		}
		return rc;
	}
	
	@Override
	protected void handleKeyDownEvent(KeyboardEvent e) {
		if (e.getKey() == Keyboard.KEY_RETURN || e.getKey() == Keyboard.KEY_SPACE) {
			ServiceManager.getInstance().getProgramStateManager().setCurrentProgramState(ProgramState.EXITING);
		}
	}

}
