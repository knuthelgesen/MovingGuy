package no.plasmid.movingguy.gui;

import no.plasmid.movingguy.gui.RelativePositionLayoutEngine.HorizontalAlignment;
import no.plasmid.movingguy.gui.RelativePositionLayoutEngine.VerticalAlignment;
import no.plasmid.movingguy.service.ServiceManager;

/**
 * Factory class that is used to create components, this only contains static methods. The methods also provide some
 * "light" error checking, that is, they will give warnings when some information is missing, but not block the
 * creation of the component objects.
 * 
 * @author helgesk
 *
 */
public class ComponentFactory {

	/**
	 * Create a page. The page only requires a name, since they will always cover the entire screen. The page will be
	 * registered with the GUIManager service.
	 * @param name The name of the page
	 * @return the newly created page
	 */
	public static Page createPage(String name, String defaultActiveComponentName) {
		//Check input
		if (null == name || "".equals(name)) {
			throw new IllegalArgumentException("A Page must have a name");
		}
		if (null == defaultActiveComponentName || "".equals(defaultActiveComponentName)) {
			throw new IllegalArgumentException("A Page must have a default selected component");
		}
		
		//Create the page
		Page rc = new Page(name);
		ServiceManager.getInstance().getGUIManager().registerNamedComponent(rc);
		
		//Set name of default active component
		rc.setDefaultFocusedComponent(defaultActiveComponentName);
		
		return rc;
	}

	/**
	 * Create a colored panel. If a name is provided, the panel will be registered with the GUIManager service. If an
	 * attribute that is vital for correct rendering is missing, a warning will be logged.
	 * @param name The optional name of the panel
	 * @param color The color used when rendering the panel
	 * @param requestedBouds The requested size and position of the panel
	 * @param layoutEngine The layout engine to use when calculating final position and size
	 * @param horizontalAlignment The alignment on the horizontal axis
	 * @param verticalAlignment The alignment on the vertical axis
	 * @return the newly created panel
	 */
	public static ColoredPanel createColoredPanel(String name, Color color, Rectangle<Integer> requestedBouds,
			LayoutEngine layoutEngine, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
		//Check input
		if (null == color || null == requestedBouds || null == layoutEngine || null == horizontalAlignment
				|| null == verticalAlignment) {
			//TODO log a warning that the component might not be shown correctly
		}
		
		//Create the panel and set values
		ColoredPanel rc = new ColoredPanel();
		rc.setColor(color);
		rc.setRequestedBounds(requestedBouds);
		rc.setLayoutEngine(layoutEngine);
		rc.setHorizontalAlignment(horizontalAlignment);
		rc.setVerticalAlignment(verticalAlignment);
		
		//If there is a name, register the component as a named component
		addNameToComponent(rc, name);
		
		return rc;
	}
	
	/**
	 * Create a textured panel. If a name is provided, the panel will be registered with the GUIManager service. If an
	 * attribute that is vital for correct rendering is missing, a warning will be logged.
	 * @param name The optional name of the panel
	 * @param color The color used when rendering the panel
	 * @param requestedBouds The requested size and position of the panel
	 * @param layoutEngine The layout engine to use when calculating final position and size
	 * @param horizontalAlignment The alignment on the horizontal axis
	 * @param verticalAlignment The alignment on the vertical axis
	 * @param texture The texture to use when rendering the panel
	 * @param textureCoordinates The texture coordinates to use when rendering
	 * @return the newly created panel
	 */
	public static TexturedPanel createTexturedPanel(String name, Color color, Rectangle<Integer> requestedBouds,
			LayoutEngine layoutEngine, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
			Texture texture, Rectangle<Double> textureCoordinates) {
		//Check input
		if (null == color || null == requestedBouds || null == layoutEngine || null == horizontalAlignment
				|| null == verticalAlignment || null == texture || null == textureCoordinates) {
			//TODO log a warning that the component might not be shown correctly
		}

		//Create the panel and set values
		TexturedPanel rc = new TexturedPanel();
		rc.setColor(color);
		rc.setRequestedBounds(requestedBouds);
		rc.setLayoutEngine(layoutEngine);
		rc.setHorizontalAlignment(horizontalAlignment);
		rc.setVerticalAlignment(verticalAlignment);
		rc.setTexture(texture);
		rc.setTextureCoordinates(textureCoordinates);
		
		//If there is a name, register the component as a named component
		addNameToComponent(rc, name);
		
		return rc;
	}
	
	/**
	 * Create a menu. If a name is provided, the panel will be registered with the GUIManager service. If an attribute
	 * that is vital for correct rendering is missing, a warning will be logged.
	 * @param name The optional name of the menu
	 * @param color The color used when rendering the menu
	 * @param requestedBouds The requested size and position of the menu
	 * @param layoutEngine The layout engine to use when calculating final position and size
	 * @param horizontalAlignment The alignment on the horizontal axis
	 * @param verticalAlignment The alignment on the vertical axis
	 * @param texture The texture to use when rendering the menu
	 * @param textureCoordinates The texture coordinates to use when rendering
	 * @return the newly created menu
	 */
	public static Menu createMenu(String name, Color color, Rectangle<Integer> requestedBouds,
			LayoutEngine layoutEngine, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
			Texture texture, Rectangle<Double> textureCoordinates) {
		//Check input
		if (null == color || null == requestedBouds || null == layoutEngine || null == horizontalAlignment
				|| null == verticalAlignment || null == texture || null == textureCoordinates) {
			//TODO log a warning that the component might not be shown correctly
		}
		
		//Create the panel and set values
		Menu rc = new Menu();
		rc.setColor(color);
		rc.setRequestedBounds(requestedBouds);
		rc.setLayoutEngine(layoutEngine);
		rc.setHorizontalAlignment(horizontalAlignment);
		rc.setVerticalAlignment(verticalAlignment);
		rc.setTexture(texture);
		rc.setTextureCoordinates(textureCoordinates);
		
		//If there is a name, register the component as a named component
		addNameToComponent(rc, name);
		
		return rc;
	}
	
	/**
	 * Create a menu item. If a name is provided, the panel will be registered with the GUIManager service. If an
	 * attribute that is vital for correct rendering is missing, a warning will be logged.
	 * @param name The optional name of the menu item
	 * @param color The color used when rendering the menu item
	 * @param requestedBouds The requested size and position of the menu item
	 * @param layoutEngine The layout engine to use when calculating final position and size
	 * @param horizontalAlignment The alignment on the horizontal axis
	 * @param verticalAlignment The alignment on the vertical axis
	 * @param texture The texture to use when rendering the menu item
	 * @param textureCoordinates The texture coordinates to use when rendering
	 * @return the newly created menu item
	 */
	public static MenuItem createMenuItem(String name, Color color, Rectangle<Integer> requestedBouds,
			LayoutEngine layoutEngine, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
			Texture texture, Rectangle<Double> textureCoordinates) {
		//Check input
		if (null == color || null == requestedBouds || null == layoutEngine || null == horizontalAlignment
				|| null == verticalAlignment || null == texture || null == textureCoordinates) {
			//TODO log a warning that the component might not be shown correctly
		}
		
		//Create the panel and set values
		MenuItem rc = new MenuItem();
		rc.setColor(color);
		rc.setRequestedBounds(requestedBouds);
		rc.setLayoutEngine(layoutEngine);
		rc.setHorizontalAlignment(horizontalAlignment);
		rc.setVerticalAlignment(verticalAlignment);
		rc.setTexture(texture);
		rc.setTextureCoordinates(textureCoordinates);
		
		//If there is a name, register the component as a named component
		addNameToComponent(rc, name);

		return rc;
	}
	
	/**
	 * Create a text label. If a name is provided, the panel will be registered with the GUIManager service. If an
	 * attribute that is vital for correct rendering is missing, a warning will be logged.
	 * @param name The optional name of the label
	 * @param color The color used when rendering the label
	 * @param requestedBouds The requested size and position of the label
	 * @param layoutEngine The layout engine to use when calculating final position and size
	 * @param horizontalAlignment The alignment on the horizontal axis
	 * @param verticalAlignment The alignment on the vertical axis
	 * @param value The textual value of the label
	 * @param font The font to use when rendering the label
	 * @param textWidth The width of each rendered character
	 * @param textHeight The height of each rendered character
	 * @return the newly created text label
	 */
	public static TextLabel createTextLabel(String name, Color color, Rectangle<Integer> requestedBouds,
			LayoutEngine layoutEngine, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
			String value, Font font, Integer textWidth, Integer textHeight) {
		//Check input
		if (null == color || null == requestedBouds || null == layoutEngine || null == horizontalAlignment
				|| null == verticalAlignment || null == value || null == font || null == textWidth
				|| null == textHeight) {
			//TODO log a warning that the component might not be shown correctly
		}
		
		//Create the panel and set values
		TextLabel rc = new TextLabel();
		rc.setColor(color);
		rc.setRequestedBounds(requestedBouds);
		rc.setLayoutEngine(layoutEngine);
		rc.setHorizontalAlignment(horizontalAlignment);
		rc.setVerticalAlignment(verticalAlignment);
		rc.setValue(value);
		rc.setFont(font);
		rc.setTextWidth(textWidth);
		rc.setTextHeight(textHeight);
		
		//If there is a name, register the component as a named component
		addNameToComponent(rc, name);
		
		return rc;
	}

	private static void addNameToComponent(Component component, String name) {
		if (null != name && !"".equals(name)) {
			component.setName(name);
			ServiceManager.getInstance().getGUIManager().registerNamedComponent(component);
		}
	}
	
}
