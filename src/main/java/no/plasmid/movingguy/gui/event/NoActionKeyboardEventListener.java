package no.plasmid.movingguy.gui.event;

/**
 * Implementation of the keyboardEventHandler that does nothing. Meant to be overridden by other implementations.
 * 
 * @author helgesk
 *
 */
public abstract class NoActionKeyboardEventListener extends KeyboardEventListener {

	@Override
	protected boolean isConsumed(KeyboardEvent event) {
		return false;
	}

	@Override
	protected void handleKeyDownEvent(KeyboardEvent event) {
		//DO nothing
	}

	@Override
	protected void handleKeyUpEvent(KeyboardEvent event) {
		//DO nothing
	}

	@Override
	protected void handleKeyTypedEvent(KeyboardEvent event) {
		//DO nothing
	}

}
