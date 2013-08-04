package no.plasmid.movingguy.gui;

import no.plasmid.movingguy.gui.dataobject.Color;
import no.plasmid.movingguy.gui.dataobject.Rectangle;
import no.plasmid.movingguy.gui.dataobject.Texture;

/**
 * This component will be rendered as a panel with a texture. The texture will be modified by the color of the panel.
 * 
 * @author helgesk
 *
 */
public class Panel extends Component {

	/**
	 * The color used when rendering this panel
	 */
	private Color backgroundColor = null;
		
	/**
	 * The texture to use when rendering this panel
	 */
	private Texture texture;
	
	/**
	 * The texture coordinates
	 */
	private Rectangle<Double> textureCoordinates;
	
	/**
	 * Construct a panel with size = 0 and empty texture
	 */
	public Panel() {
		textureCoordinates = new Rectangle<Double>(0.0, 1.0, 1.0, 0.0);
		texture = Texture.EMPTY;
	}
	
	/**
	 * Copy constructor.
	 * 
	 * @param panel the Panel to copy
	 */
	public Panel(Panel panel) {
		super(panel);
		
		setBackgroundColor(panel.getBackgroundColor());
		setTexture(panel.getTexture());
		setTextureCoordinates(panel.getTextureCoordinates());
	}
	
	/**
	 * @return the color used when rendering this panel
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * Set which color to use when rendering the panel
	 * 
	 * @param color the color to use when rendering the panel
	 */
	public void setBackgroundColor(Color color) {
		this.backgroundColor = color;
	}

	/**
	 * @return the texture used when rendering this panel
	 */
	public Texture getTexture() {
		return texture;
	}
	
	/**
	 * Set which texture to use when rendering this panel
	 * @param texture the texture to use for this panel
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	/**
	 * @return the texture coordinates used when rendering this panel
	 */
	public Rectangle<Double> getTextureCoordinates() {
		return textureCoordinates;
	}

	/**
	 * Set the texture coordinates to use when rendering this panel
	 * @param texture a {@link Rectangle} containing the texture coordinates
	 */
	public void setTextureCoordinates(Rectangle<Double> textureCoordinates) {
		this.textureCoordinates = textureCoordinates;
	}

	@Override
	public void draw(GUIRenderer renderer) {
		if (!isHidden()) {
			renderer.fillTexturedRectangle(getActualBounds(), getBackgroundColor(), texture, textureCoordinates);
			for (Component child : getChildren()) {
				child.draw(renderer);
			}
		}
	}
	
}
