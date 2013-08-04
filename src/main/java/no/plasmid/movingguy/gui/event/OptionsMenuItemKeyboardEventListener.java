package no.plasmid.movingguy.gui.event;

import no.plasmid.movingguy.gui.Menu;
import no.plasmid.movingguy.service.ServiceManager;

import org.lwjgl.input.Keyboard;

/**
 * Keyboard event listener for the options menu item
 * 
 * Show the options menu
 * 
 * @author helgesk
 *
 */
public class OptionsMenuItemKeyboardEventListener extends NoActionKeyboardEventListener {

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
			Menu optionsMenu = (Menu)ServiceManager.getInstance().getGUIManager().getNamedComponent("optionsMenu");
			optionsMenu.setHidden(false);
			ServiceManager.getInstance().getGUIManager().pushComponentToFocusStack(getOwner());
			ServiceManager.getInstance().getGUIManager().setFocusedComponent(optionsMenu.getSelectedItem());
		}
	}

	@Override
	public KeyboardEventListener clone() {
		return new OptionsMenuItemKeyboardEventListener();
	}

}
