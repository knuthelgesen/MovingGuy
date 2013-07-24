package no.plasmid.movingguy.gui;

/**
 * This component will be rendered as a simple panel with flat color.
 * 
 * @author helgesk
 *
 */
public class ColoredPanel extends Component {

	/**
	 * The color used when rendering this panel
	 */
	private Color color = null;
		
	/**
	 * @return the color used when rendering this panel
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Set which color to use when rendering the panel
	 * 
	 * @param color the color to use when rendering the panel
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public void draw(GUIRenderer renderer) {
		if (!isHidden()) {
			renderer.fillRectangle(getActualBounds(), getColor());
			for (Component child : getChildren()) {
				child.draw(renderer);
			}
		}
	}

}
