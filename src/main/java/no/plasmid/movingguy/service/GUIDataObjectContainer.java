package no.plasmid.movingguy.service;

import java.util.HashMap;
import java.util.Map;

import no.plasmid.movingguy.gui.Component;
import no.plasmid.movingguy.gui.dataobject.Color;
import no.plasmid.movingguy.gui.dataobject.Font;
import no.plasmid.movingguy.gui.dataobject.Texture;
import no.plasmid.movingguy.gui.template.Template;

public class GUIDataObjectContainer implements Service {

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
	
	/**
	 * Map of all known templates mapped to the name of the components
	 */
	private Map<Class<? extends Component>, Template<? extends Component>> templates;
	
	@Override
	public void initializeService() {
		colors = new HashMap<String, Color>();
		textures = new HashMap<String, Texture>();
		fonts = new HashMap<String, Font>();
		templates = new HashMap<Class<? extends Component>, Template<? extends Component>>();
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
	
	/**
	 * Will return a name for the component type specified, or <code>null</code> if none is found.
	 * 
	 * @param componentType the name of the component type
	 * @return the template for the type, or <code>null</code> if not found
	 */
	@SuppressWarnings("unchecked")
	public <T> T getTemplate(Class<? extends Component> componentType) {
		return (T) templates.get(componentType);
	}
	
	/**
	 * Add a component template to the list of known ones.
	 * 
	 * @param template the component template to add
	 */
	public void addTemplate(Template<? extends Component> template) {
		templates.put(template.getComponentClass(), template);
	}
	
}
