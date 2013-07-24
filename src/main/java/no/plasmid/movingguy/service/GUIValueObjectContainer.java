package no.plasmid.movingguy.service;

import java.util.HashMap;
import java.util.Map;

import no.plasmid.movingguy.gui.Color;
import no.plasmid.movingguy.gui.Font;
import no.plasmid.movingguy.gui.Texture;

public class GUIValueObjectContainer implements Service {

	/**
	 * Map of all known colors
	 */
	private Map<String, Color> colors;
	
	/**
	 * Map of all known textures
	 */
	private Map<String, Texture> textures;
	
	/**
	 * Map of all known fonts
	 */
	private Map<String, Font> fonts;
	
	@Override
	public void initializeService() {
		colors = new HashMap<String, Color>();
		textures = new HashMap<String, Texture>();
		fonts = new HashMap<String, Font>();
	}

	/**
	 * Will return the color with the specified name, or <code>null</code> if not found
	 * @param name the name of the color to return
	 * @return the color with the specified name, or <code>null</code> if not found
	 */
	public Color getColor(String name) {
		return colors.get(name);
	}
	
	/**
	 * Add a color to the list of known ones.
	 * @param name the name of the color
	 * @param color the color to add
	 */
	public void addColor(String name, Color color) {
		colors.put(name, color);
	}
	
	/**
	 * Will return the texture with the specified name, or <code>null</code> if not found
	 * @param name the name of the texture to return
	 * @return the texture with the specified name, or <code>null</code> if not found
	 */
	public Texture getTexture(String name) {
		return textures.get(name);
	}

	/**
	 * Add a texture to the list of known ones.
	 * @param texture the texture to add
	 */
	public void addTexture(Texture texture) {
		textures.put(texture.getName(), texture);
	}
	
	/**
	 * Will return the font with the specified name, or <code>null</code> if not found
	 * @param name the name of the font to return
	 * @return the font with the specified name, or <code>null</code> if not found
	 */
	public Font getFont(String name) {
		return fonts.get(name);
	}
		
	/**
	 * Add a font to the list of known ones.
	 * @param font the font to add
	 */
	public void addFont(String name, Font font) {
		fonts.put(name, font);
	}
	
}
