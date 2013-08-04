package no.plasmid.movingguy.gui;

import no.plasmid.movingguy.gui.dataobject.Color;
import no.plasmid.movingguy.gui.dataobject.Font;
import no.plasmid.movingguy.gui.dataobject.Rectangle;
import no.plasmid.movingguy.gui.dataobject.Texture;
import no.plasmid.movingguy.gui.event.KeyboardEventListener;
import no.plasmid.movingguy.gui.layout.LayoutEngine;
import no.plasmid.movingguy.gui.layout.RelativePositionLayoutEngine.HorizontalAlignment;
import no.plasmid.movingguy.gui.layout.RelativePositionLayoutEngine.VerticalAlignment;
import no.plasmid.movingguy.gui.template.MenuItemTemplate;
import no.plasmid.movingguy.gui.template.MenuTemplate;
import no.plasmid.movingguy.gui.template.PageTemplate;
import no.plasmid.movingguy.gui.template.PanelTemplate;
import no.plasmid.movingguy.gui.template.TextLabelTemplate;
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
	 * @param defaultActiveComponentName the name of the component that first will have focus when this page is displayed
	 * @param keyboardEventListener the keyboard event listener to use
	 * @return the newly created page
	 */
	public static Page createPage(String name, String defaultActiveComponentName, KeyboardEventListener keyboardEventListener) {
		//Check input
		if (null == name || "".equals(name)) {
			throw new IllegalArgumentException("A Page must have a name");
		}
		if (null == defaultActiveComponentName || "".equals(defaultActiveComponentName)) {
			throw new IllegalArgumentException("A Page must have a default selected component");
		}
		
		//Get the page template if it exists
		PageTemplate pageTemplate = ServiceManager.getInstance().getGUIDataObjectContainer().getTemplate(Page.class);
		
		//Create the page
		Page rc = null;
		if (null != pageTemplate) {
			rc = new Page(pageTemplate);
		} else {
			rc = new Page();
		}
		
		//Set Component specific values
		setComponentValues(rc, name, null, null, null, null, keyboardEventListener);
		
		//Set Page specific values
		setPageValues(rc, defaultActiveComponentName);
		
		return rc;
	}

	/**
	 * Create a panel. If a name is provided, the panel will be registered with the GUIManager service. If an
	 * attribute that is vital for correct rendering is missing, a warning will be logged.
	 * @param name The optional name of the panel
	 * @param backgroundColor The color used when rendering the panel
	 * @param requestedBounds The requested size and position of the panel
	 * @param layoutEngine The layout engine to use when calculating final position and size
	 * @param horizontalAlignment The alignment on the horizontal axis
	 * @param verticalAlignment The alignment on the vertical axis
	 * @param texture The texture to use when rendering the panel
	 * @param textureCoordinates The texture coordinates to use when rendering
	 * @param keyboardEventListener the keyboard event listener to use
	 * @return the newly created panel
	 */
	public static Panel createPanel(String name, Color backgroundColor, Rectangle<Integer> requestedBounds,
			LayoutEngine layoutEngine, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
			Texture texture, Rectangle<Double> textureCoordinates, KeyboardEventListener keyboardEventListener) {
		//Check input
		if (null == backgroundColor || null == requestedBounds || null == layoutEngine || null == horizontalAlignment
				|| null == verticalAlignment) {
			//TODO log a warning that the component might not be shown correctly
		}

		//Get the panel template if it exists
		PanelTemplate panelTemplate = ServiceManager.getInstance().getGUIDataObjectContainer().getTemplate(Panel.class);
		
		//Create panel
		Panel rc = null;
		if (null != panelTemplate) {
			rc = new Panel(panelTemplate);
		} else {
			rc = new Panel();
		}
		
		//Set Component specific data
		setComponentValues(rc, name, layoutEngine, horizontalAlignment, verticalAlignment, requestedBounds,
				keyboardEventListener);
		
		//Set Panel specific values
		setPanelValues(rc, backgroundColor, texture, textureCoordinates);
		
		return rc;
	}
	
	/**
	 * Create a menu. If a name is provided, the panel will be registered with the GUIManager service. If an attribute
	 * that is vital for correct rendering is missing, a warning will be logged.
	 * @param name The optional name of the menu
	 * @param backgroundColor The color used when rendering the menu
	 * @param requestedBounds The requested size and position of the menu
	 * @param layoutEngine The layout engine to use when calculating final position and size
	 * @param horizontalAlignment The alignment on the horizontal axis
	 * @param verticalAlignment The alignment on the vertical axis
	 * @param texture The texture to use when rendering the menu
	 * @param textureCoordinates The texture coordinates to use when rendering
	 * @param keyboardEventListener the keyboard event listener to use
	 * @return the newly created menu
	 */
	public static Menu createMenu(String name, Color backgroundColor, Rectangle<Integer> requestedBounds,
			LayoutEngine layoutEngine, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
			Texture texture, Rectangle<Double> textureCoordinates, KeyboardEventListener keyboardEventListener) {
		//Check input
		if (null == backgroundColor || null == requestedBounds || null == layoutEngine || null == horizontalAlignment
				|| null == verticalAlignment || null == texture || null == textureCoordinates) {
			//TODO log a warning that the component might not be shown correctly
		}

		//Get the menu template if it exists
		MenuTemplate menuTemplate = ServiceManager.getInstance().getGUIDataObjectContainer().getTemplate(Menu.class);

		//Create the menu
		Menu rc = null;
		if (null != menuTemplate) {
			rc = new Menu(menuTemplate);
		} else {
			rc = new Menu();
		}
		
		//Set Component specific data
		setComponentValues(rc, name, layoutEngine, horizontalAlignment, verticalAlignment, requestedBounds,
				keyboardEventListener);
		
		//Set Panel specific values
		setPanelValues(rc, backgroundColor, texture, textureCoordinates);
		
		return rc;
	}
	
	/**
	 * Create a menu item. If a name is provided, the panel will be registered with the GUIManager service. If an
	 * attribute that is vital for correct rendering is missing, a warning will be logged.
	 * @param name The optional name of the menu item
	 * @param backgroundColor The color used when rendering the menu item
	 * @param requestedBounds The requested size and position of the menu item
	 * @param layoutEngine The layout engine to use when calculating final position and size
	 * @param horizontalAlignment The alignment on the horizontal axis
	 * @param verticalAlignment The alignment on the vertical axis
	 * @param texture The texture to use when rendering the menu item
	 * @param textureCoordinates The texture coordinates to use when rendering
	 * @param keyboardEventListener the keyboard event listener to use
	 * @return the newly created menu item
	 */
	public static MenuItem createMenuItem(String name, Color backgroundColor, Rectangle<Integer> requestedBounds,
			LayoutEngine layoutEngine, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
			Texture texture, Rectangle<Double> textureCoordinates, KeyboardEventListener keyboardEventListener) {
		//Check input
		if (null == backgroundColor || null == requestedBounds || null == layoutEngine || null == horizontalAlignment
				|| null == verticalAlignment || null == texture || null == textureCoordinates) {
			//TODO log a warning that the component might not be shown correctly
		}
		
		//Get the menu item template if it exists
		MenuItemTemplate menuItemTemplate = ServiceManager.getInstance().getGUIDataObjectContainer().getTemplate(MenuItem.class);

		//Create the menu item
		MenuItem rc = null;
		if (null != menuItemTemplate) {
			rc = new MenuItem(menuItemTemplate);
		} else {
			rc = new MenuItem();
		}

		//Set Component specific data
		setComponentValues(rc, name, layoutEngine, horizontalAlignment, verticalAlignment, requestedBounds,
				keyboardEventListener);
		
		//Set Panel specific values
		setPanelValues(rc, backgroundColor, texture, textureCoordinates);

		return rc;
	}
	
	/**
	 * Create a text label. If a name is provided, the panel will be registered with the GUIManager service. If an
	 * attribute that is vital for correct rendering is missing, a warning will be logged.
	 * @param name The optional name of the label
	 * @param backgroundColor The color used when rendering the label
	 * @param requestedBounds The requested size and position of the label
	 * @param layoutEngine The layout engine to use when calculating final position and size
	 * @param horizontalAlignment The alignment on the horizontal axis
	 * @param verticalAlignment The alignment on the vertical axis
	 * @param texture The texture to use when rendering the text label
	 * @param textureCoordinates The texture coordinates to use when rendering
	 * @param textValue The textual value of the label
	 * @param font The font to use when rendering the label
	 * @param textWidth The width of each rendered character
	 * @param textHeight The height of each rendered character
	 * @param keyboardEventListener the keyboard event listener to use
	 * @return the newly created text label
	 */
	public static TextLabel createTextLabel(String name, Color backgroundColor, Rectangle<Integer> requestedBounds,
			LayoutEngine layoutEngine, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
			Texture texture, Rectangle<Double> textureCoordinates, String textValue, Font font, Integer textWidth,
			Integer textHeight, KeyboardEventListener keyboardEventListener) {
		//Check input
		if (null == backgroundColor || null == requestedBounds || null == layoutEngine || null == horizontalAlignment
				|| null == verticalAlignment || null == textValue || null == font || null == textWidth
				|| null == textHeight) {
			//TODO log a warning that the component might not be shown correctly
		}
		
		//Get the menu item template if it exists
		TextLabelTemplate textLabelTemplate = ServiceManager.getInstance().getGUIDataObjectContainer().getTemplate(TextLabel.class);

		//Create the menu item
		TextLabel rc = null;
		if (null != textLabelTemplate) {
			rc = new TextLabel(textLabelTemplate);
		} else {
			rc = new TextLabel();
		}

		//Set Component specific data
		setComponentValues(rc, name, layoutEngine, horizontalAlignment, verticalAlignment, requestedBounds,
				keyboardEventListener);
		
		//Set Panel specific values
		setPanelValues(rc, backgroundColor, texture, textureCoordinates);
		
		//Set TextLabel specific values
		setTextLabelValues(rc, textValue, font, textWidth, textHeight);
		
		return rc;
	}

	/**
	 * Set values that are common for the {@link Component} class.
	 * 
	 * @param component the component to add values too
	 * @param name the name to add to the component
	 * @param layoutEngine the layout engine to use when calculating final position and size
	 * @param requestedBounds the requested size and position of the component
	 * @param horizontalAlignment the alignment on the horizontal axis
	 * @param verticalAlignment the alignment on the vertical axis
	 * @param keyboardEventListener the keyboard event listener to use
	 */
	protected static void setComponentValues(Component component, String name, LayoutEngine layoutEngine,
			HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
			Rectangle<Integer> requestedBounds, KeyboardEventListener keyboardEventListener) {
		//Set name
		addNameToComponent(component, name);
		
		//Set other values
		if (null != layoutEngine) {
			component.setLayoutEngine(layoutEngine);
		}
		if (null != horizontalAlignment) {
			component.setHorizontalAlignment(horizontalAlignment);
		}
		if (null != verticalAlignment) {
			component.setVerticalAlignment(verticalAlignment);
		}
		if (null != requestedBounds) {
			component.setRequestedBounds(requestedBounds);
		}
		if (null != keyboardEventListener) {
			component.setKeyboardEventListener(keyboardEventListener);
		}
	}
	
	/**
	 * Set values that are specific for the {@link Page} class
	 * @param page the Page to add values to
	 * @param defaultActiveComponentName the name of the component that first will have focus when this page is displayed
	 */
	protected static void setPageValues(Page page, String defaultActiveComponentName) {
		if (null != defaultActiveComponentName) {
			page.setDefaultFocusedComponentName(defaultActiveComponentName);
		}
	}
	
	/**
	 * Set values that are specific for the {@link Panel} class
	 * @param panel
	 * @param backgroundColor
	 * @param texture
	 * @param textureCoordinates
	 */
	protected static void setPanelValues(Panel panel, Color backgroundColor, Texture texture,
			Rectangle<Double> textureCoordinates) {
		if (null != backgroundColor) {
			panel.setBackgroundColor(backgroundColor);
		}
		if (null != texture) {
			panel.setTexture(texture);
		}
		if (null != textureCoordinates) {
			panel.setTextureCoordinates(textureCoordinates);
		}
	}

	/**
	 * Set values that are specific for the {@link TextLabel} class
	 * @param textLabel
	 * @param textValue
	 * @param font
	 * @param textWidth
	 * @param textHeight
	 */
	protected static void setTextLabelValues(TextLabel textLabel, String textValue, Font font, Integer textWidth,
			Integer textHeight) {
		if (null != textValue) {
			textLabel.setValue(textValue);
		}
		if (null != font) {
			textLabel.setFont(font);
		}
		if (null != textWidth) {
			textLabel.setTextWidth(textWidth);
		}
		if (null != textHeight) {
			textLabel.setTextHeight(textHeight);
		}
	}
	
	private static void addNameToComponent(Component component, String name) {
		if (null != name && !"".equals(name)) {
			component.setName(name);
			ServiceManager.getInstance().getGUIManager().registerNamedComponent(component);
		}
	}
	
}
