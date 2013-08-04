package no.plasmid.movingguy.gui.layout;

import no.plasmid.movingguy.gui.Component;
import no.plasmid.movingguy.gui.dataobject.Rectangle;

/**
 * Interface for classes that are used to calculate the final bounds of components.
 * 
 * @author helgesk
 *
 */
public interface LayoutEngine {
	
	/**
	 * This method will calculate the final bounds of a component.
	 * 
	 * @param comp the component to calculate bounds for
	 * @return a Rectangle containing the bounds of the component
	 */
	public Rectangle<Integer> calculateBounds(Component comp);

}
