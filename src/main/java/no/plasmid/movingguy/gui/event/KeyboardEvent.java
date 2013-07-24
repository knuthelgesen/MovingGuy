package no.plasmid.movingguy.gui.event;

public class KeyboardEvent {

	/**
	 * The type of keyboard event
	 */
	private Type type;
	
	/**
	 * The key in question
	 */
	private int key;
	
	/**
	 * Construct a new KeyboardEvent
	 * 
	 * @param type the {@link Type} of event
	 * @param key The integer code of the key in question
	 */
	public KeyboardEvent(Type type, int key) {
		this.type = type;
		this.key = key;
	}
	
	/**
	 * @return the {@link Type} of event
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @return the integer code of the key in question
	 */
	public int getKey() {
		return key;
	}
	
	/**
	 * Enumeration of types of keyboard events corresponding to which action was done by the user.
	 * 
	 * @author helgesk
	 *
	 */
	public enum Type {
		DOWN, UP, TYPED;
	}
	
}
