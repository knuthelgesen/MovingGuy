package no.plasmid.movingguy.gui;

/**
 * This implementation of the layout engine will position the component relative to it's parent. The alignment will be
 * read from the component itself. There are no limits on the values, so component bounds can be larger than it's
 * parent.
 * 
 * @author helgesk
 *
 */
public class RelativePositionLayoutEngine implements LayoutEngine {

	@Override
	public Rectangle<Integer> calculateBounds(Component comp) {
		Rectangle<Integer> rc = new Rectangle<Integer>(comp.getRequestedBounds());
		
		if (comp.getParent() != null) {
			Rectangle<Integer> parentBounds = comp.getParent().getActualBounds();
			
			//Calculate horizontal position
			switch (comp.getHorizontalAlignment()) {
			case LEFT:
				rc.setX1(rc.getX1() + parentBounds.getX1());
				rc.setX2(rc.getX2() + parentBounds.getX1());
				break;
			case CENTER:
				rc.setX1(rc.getX1() + parentBounds.getX1() + (parentBounds.getX2() - parentBounds.getX1()) / 2);
				rc.setX2(rc.getX2() + parentBounds.getX1() + (parentBounds.getX2() - parentBounds.getX1()) / 2);
				break;
			case RIGHT:
				rc.setX1(rc.getX1() + parentBounds.getX2());
				rc.setX2(rc.getX2() + parentBounds.getX2());
				break;
			default:
				//Same as left
				rc.setX1(rc.getX1() + parentBounds.getX1());
				rc.setX2(rc.getX2() + parentBounds.getX1());
				break;
			}

			//Calculate vertical position
			switch (comp.getVerticalAlignment()) {
			case TOP:
				rc.setY1(rc.getY1() + parentBounds.getY2());
				rc.setY2(rc.getY2() + parentBounds.getY2());
				break;
			case CENTER:
				rc.setY1(rc.getY1() + parentBounds.getY1() + (parentBounds.getY2() - parentBounds.getY1()) / 2);
				rc.setY2(rc.getY2() + parentBounds.getY1() + (parentBounds.getY2() - parentBounds.getY1()) / 2);
				break;
			case BOTTOM:
				rc.setY1(rc.getY1() + parentBounds.getY1());
				rc.setY2(rc.getY2() + parentBounds.getY1());
				break;
			default:
				//Like top
				rc.setY1(rc.getY1() + parentBounds.getY2());
				rc.setY2(rc.getY2() + parentBounds.getY2());
				break;
			
			}
			
		}
		
		return rc;
	}
	
	/**
	 * Enumeration used to indicate the alignment of a component relative to it's parent on the horizontal axis
	 * @author helgesk
	 *
	 */
	public enum HorizontalAlignment {
		LEFT, CENTER, RIGHT
	}
	
	/**
	 * Enumeration used to indicate the alignment of a component relative to it's parent on the vertical axis
	 * @author helgesk
	 *
	 */
	public enum VerticalAlignment {
		TOP, CENTER, BOTTOM
	}

}
