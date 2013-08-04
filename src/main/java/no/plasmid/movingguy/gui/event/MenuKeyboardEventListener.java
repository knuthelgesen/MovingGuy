package no.plasmid.movingguy.gui.event;

import no.plasmid.movingguy.gui.Menu;

import org.lwjgl.input.Keyboard;

/**
 * Keyboard event listener for menus
 * 
 * Move the selection index up or down
 * 
 * @author helgesk
 *
 */
public class MenuKeyboardEventListener extends NoActionKeyboardEventListener {
	
	@Override
	protected boolean isConsumed(KeyboardEvent e) {
		//Consume up and down
		boolean rc = false;
		if (e.getKey() == Keyboard.KEY_UP || e.getKey() == Keyboard.KEY_DOWN) {
			rc = true;
		}
		return rc;
	}
	
	@Override
	protected void handleKeyDownEvent(KeyboardEvent e) {
		if (e.getKey() == Keyboard.KEY_UP) {
			((Menu)getOwner()).selectPreviousItem();
		}
		if (e.getKey() == Keyboard.KEY_DOWN) {
			((Menu)getOwner()).selectNextItem();
		}
	}

	@Override
	public KeyboardEventListener clone() {
		return new MenuKeyboardEventListener();
	}

}
