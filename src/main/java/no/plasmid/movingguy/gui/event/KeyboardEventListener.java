package no.plasmid.movingguy.gui.event;

import no.plasmid.movingguy.gui.Component;

/**
 * Abstract superclass for all keyboard event listeners
 * 
 * @author helgesk
 *
 */
public abstract class KeyboardEventListener {

	/**
	 * The component that "owns" this listener, i.e. the component that uses it
	 */
	private Component owner;
	
	/**
	 * return the component that "owns" this listener, i.e. the component that uses it
	 */
	public Component getOwner() {
		return owner;
	}

	/**
	 * Set which component owns this listener
	 * @param owner the owning component
	 */
	public void setOwner(Component owner) {
		this.owner = owner;
	}

	/**
	 * Handle a keyboard event by calling the correct method based on event type. The return value indicates whether
	 * the listener consumes the event.
	 * 
	 * @param event the event to handle
	 * @return a value indicating whether the event is consumed by this handler
	 */
	public final boolean handleKeyboardEvent(KeyboardEvent event) {
		switch (event.getType()) {
		case DOWN:
			handleKeyDownEvent(event);
			break;
		case UP:
			handleKeyUpEvent(event);
			break;
		case TYPED:
			handleKeyTypedEvent(event);
			break;
		default:
			//This REALLY should not happen
			throw new IllegalStateException("Keyboard event does not have a legal type");
		}
		
		return isConsumed(event);
	}
	
	/**
	 * Check if a listener wants to consume a keyboard event
	 * 
	 * @param event the event to check
	 * @return <code>true</code> if the handler consumes the event, <code>false</code> otherwise
	 */
	protected abstract boolean isConsumed(KeyboardEvent event);
	
	/**
	 * Handle a key down event
	 * @param event the event to handle
	 */
	protected abstract void handleKeyDownEvent(KeyboardEvent event);
	
	/**
	 * Handle a key up event
	 * @param event the event to handle
	 */
	protected abstract void handleKeyUpEvent(KeyboardEvent event);
	
	/**
	 * Handle a key typed event
	 * @param event the event to handle
	 */
	protected abstract void handleKeyTypedEvent(KeyboardEvent event);
	
	/**
	 * Clone this event listener to a new instance
	 * @return the new instance
	 */
	public abstract KeyboardEventListener clone();
	
}
