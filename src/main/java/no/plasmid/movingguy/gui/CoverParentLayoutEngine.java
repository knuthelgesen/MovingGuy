package no.plasmid.movingguy.gui;

/**
 * Implementation of the LayoutEngine interface that will simply make the component the same size and position as it's
 * parent.
 * 
 * @author helgesk
 *
 */
public class CoverParentLayoutEngine implements LayoutEngine {

	@Override
	public Rectangle<Integer> calculateBounds(Component comp) {
		Component parent = comp.getParent();
		Rectangle<Integer> rc = Rectangle.zeroSizeIntegerRectangle;
		if (parent != null) {
			rc = new Rectangle<Integer>(parent.getActualBounds());
		}
		return rc;
	}

}
