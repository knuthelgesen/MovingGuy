package no.plasmid.movingguy.gui.template;

import no.plasmid.movingguy.gui.Component;
import no.plasmid.movingguy.gui.dataobject.Color;
import no.plasmid.movingguy.gui.dataobject.Rectangle;
import no.plasmid.movingguy.gui.dataobject.Texture;
import no.plasmid.movingguy.gui.layout.LayoutEngine;
import no.plasmid.movingguy.gui.layout.RelativePositionLayoutEngine.HorizontalAlignment;
import no.plasmid.movingguy.gui.layout.RelativePositionLayoutEngine.VerticalAlignment;

/**
/**
 * This is the interface for all component templates. The template classes will extend the component class it will act
 * as template for, and implement this interface.
 * 
 * @author helgesk
 *
 * @param <T extends Component> the component class the implementation will act as template for
 */
public interface Template<T extends Component> {

	/**
	 * Get which component class to act as a template for
	 * 
	 * @return the component class that this one will act as a template for
	 */
	public Class<T> getComponentClass();
	
	/**
	 * @return the background color for this component template
	 */
	public Color getBackgroundColor();

	/**
	 * Set the background color for this template
	 * 
	 * @param backgroundColor the background color for this component template
	 */
	public void setBackgroundColor(Color backgroundColor);
	
	/**
	 * @return the layout engine used for this component template
	 */
	public LayoutEngine getLayoutEngine();
	
	/**
	 * Set the layout engine used for this component template
	 * 
	 * @param layoutEngine the layout engine used for this component template
	 */
	public void setLayoutEngine(LayoutEngine layoutEngine);
	
	/**
	 * @return the horizontal alignment of this component template
	 */
	public HorizontalAlignment getHorizontalAlignment();

	/**
	 * Set the horizontal alignment of this component template.
	 * 
	 * @param horizontalAlignment the horizontal alignment of the component template
	 */
	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment);

	/**
	 * @return the texture used when rendering this component template
	 */
	public Texture getTexture();
	
	/**
	 * Set which texture to use when rendering this component template
	 * @param texture the texture to use for this panel
	 */
	public void setTexture(Texture texture);
	
	/**
	 * @return the texture coordinates used when rendering this component template
	 */
	public Rectangle<Double> getTextureCoordinates();

	/**
	 * Set the texture coordinates to use when rendering this component template
	 * @param texture a {@link Rectangle} containing the texture coordinates
	 */
	public void setTextureCoordinates(Rectangle<Double> textureCoordinates);

	/**
	 * @return the vertical alignment of this component template
	 */
	public VerticalAlignment getVerticalAlignment();

	/**
	 * Set the vertical alignment of this component template.
	 * 
	 * @param verticalAlignment the vertical alignment of the component template
	 */
	public void setVerticalAlignment(VerticalAlignment verticalAlignment);

}
