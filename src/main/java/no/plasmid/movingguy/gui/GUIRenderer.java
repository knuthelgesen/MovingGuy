package no.plasmid.movingguy.gui;

import no.plasmid.movingguy.service.Service;

/**
 * Interface for the renderer used for GUI.
 * 
 * @author helgesk
 *
 */
public interface GUIRenderer extends Service {

	/**
	 * Fill a rectangle with color
	 * 
	 * @param bounds the size and position to fill
	 * @param color the color to fill with
	 */
	public void fillRectangle(Rectangle<Integer> bounds, Color color);
	
	/**
	 * Fill a rectangle with a texture and color
	 * 
	 * @param bounds the size and position to fill
	 * @param color the color to fill with
	 * @param texture the texture to fill with 
	 * @param textureCoordinates the texture coordinates to use
	 */
	public void fillTexturedRectangle(Rectangle<Integer> bounds, Color color, Texture texture, Rectangle<Double> textureCoordinates);
	
}
