package no.plasmid.movingguy.gui.loader;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import no.plasmid.movingguy.gui.Color;
import no.plasmid.movingguy.gui.Component;
import no.plasmid.movingguy.gui.ComponentFactory;
import no.plasmid.movingguy.gui.CoverParentLayoutEngine;
import no.plasmid.movingguy.gui.Font;
import no.plasmid.movingguy.gui.LayoutEngine;
import no.plasmid.movingguy.gui.Menu;
import no.plasmid.movingguy.gui.MenuItem;
import no.plasmid.movingguy.gui.Rectangle;
import no.plasmid.movingguy.gui.RelativePositionLayoutEngine;
import no.plasmid.movingguy.gui.RelativePositionLayoutEngine.HorizontalAlignment;
import no.plasmid.movingguy.gui.RelativePositionLayoutEngine.VerticalAlignment;
import no.plasmid.movingguy.gui.Texture;
import no.plasmid.movingguy.gui.TexturedPanel;
import no.plasmid.movingguy.gui.event.ExitMenuItemKeyboardEventListener;
import no.plasmid.movingguy.gui.event.KeyboardEventListener;
import no.plasmid.movingguy.gui.event.MenuKeyboardEventListener;
import no.plasmid.movingguy.gui.event.OptionsMenuBackKeyboardEventListener;
import no.plasmid.movingguy.gui.event.OptionsMenuItemKeyboardEventListener;
import no.plasmid.movingguy.gui.event.SplashScreenKeyboardEventListener;
import no.plasmid.movingguy.service.GUIManager;
import no.plasmid.movingguy.service.ServiceManager;
import no.plasmid.movingguy.util.XmlConfigurationLoader;

public class GuiConfigurationLoader extends XmlConfigurationLoader {

	GUIManager guiManager;
	
	public GuiConfigurationLoader() {
		guiManager = ServiceManager.getInstance().getGUIManager();
	}
	
	@Override
	protected void handleElement(Element configPartElement) {
		this.handleElement(null, configPartElement);
	}
	
	private void handleElement(Component parent, Element guiPartElement) {
		//Handle the element
		Component createdComponent = null;
		String type = guiPartElement.getTagName();

		//Read values
		String name = guiPartElement.getAttribute("name");
		String focusedComponentName = guiPartElement.getAttribute("focusedComponentName");
		Color color = parseColor(guiPartElement.getAttribute("color"));
		LayoutEngine layoutEngine = parseLayoutEngine(guiPartElement.getAttribute("layoutEngine"));
		Rectangle<Integer> requestedBounds = parseRequestedBounds(guiPartElement.getAttribute("requestedBounds"));
		HorizontalAlignment horizontalAlignment = parseHorizontalAlignment(guiPartElement.getAttribute("horizontalAlignment"));
		VerticalAlignment verticalAlignment = parseVerticalAlignment(guiPartElement.getAttribute("verticalAlignment"));
		Texture texture = valueContainer.getTexture(guiPartElement.getAttribute("texture"));
		Rectangle<Double> textureCoordinates = parseTextureCoordinates(guiPartElement.getAttribute("textureCoordinates"));
		String value = guiPartElement.getAttribute("value");
		Font font = valueContainer.getFont(guiPartElement.getAttribute("font"));
		Integer[] textSizes = parseTextSizes(guiPartElement.getAttribute("textSize"));
		Boolean hidden = parseHidden(guiPartElement.getAttribute("hidden"));
		
		switch (type) {
		case "Page":
			createdComponent = ComponentFactory.createPage(name, focusedComponentName);
			break;
		case "ColoredPanel":
			createdComponent = ComponentFactory.createColoredPanel(name, color, requestedBounds, layoutEngine,
					horizontalAlignment, verticalAlignment);
			break;
		case "TexturedPanel":
			createdComponent = ComponentFactory.createTexturedPanel(name, color, requestedBounds, layoutEngine,
					horizontalAlignment, verticalAlignment, texture, textureCoordinates);
			break;
		case "Menu":
			createdComponent = ComponentFactory.createMenu(name, color, requestedBounds, layoutEngine,
					horizontalAlignment, verticalAlignment, texture, textureCoordinates);
			break;
		case "MenuItem":
			createdComponent = ComponentFactory.createMenuItem(name, color, requestedBounds, layoutEngine,
					horizontalAlignment, verticalAlignment, texture, textureCoordinates);
			((Menu)parent).addMenuItem((MenuItem)createdComponent);
			break;
		case "SelectedItemMarker":
			createdComponent = ComponentFactory.createTexturedPanel(name, color, requestedBounds, layoutEngine,
					horizontalAlignment, verticalAlignment, texture, textureCoordinates);
			((Menu)parent).setSelectedItemMarker((TexturedPanel)createdComponent);
			break;
		case "TextLabel":
			createdComponent = ComponentFactory.createTextLabel(name, color, requestedBounds, layoutEngine,
					horizontalAlignment, verticalAlignment, value, font, textSizes[0], textSizes[1]);
			break;
		default:
			//TODO Log warning for unknown tag type
		}

		//Add keyboard event listener
		if (createdComponent != null) {
			KeyboardEventListener listener = parseKeyboardListener(guiPartElement.getAttribute("keyboardEventListener"));
			if (listener != null) {
				createdComponent.setKeyboardEventListener(listener);
			}
			if (null != parent) {
				parent.addChild(createdComponent);
			}
			createdComponent.setHidden(hidden);
		}
		
		//Handle children
		NodeList nodeList = guiPartElement.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node instanceof Element) {
				handleElement(createdComponent, (Element)node);
			}
		}
	}
	
	private LayoutEngine parseLayoutEngine(String layoutEngineString) {
		LayoutEngine rc = null;
		if (!"".equals(layoutEngineString)) {
			switch (layoutEngineString) {
			case ("CoverParentLayoutEngine"):
				rc = new CoverParentLayoutEngine();
				break;
			case ("RelativePositionLayoutEngine"):
				rc = new RelativePositionLayoutEngine();
				break;
			default:
				//TODO warn
			}
		}
		return rc;
	}
	
	private Rectangle<Integer> parseRequestedBounds(String requestedBoundsString) {
		Rectangle<Integer> rc = null;
		if (!"".equals(requestedBoundsString)) {
			String[] parts = requestedBoundsString.split(",");
			rc = new Rectangle<Integer>(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
					Integer.parseInt(parts[3]));
		}
		return rc;
	}
	
	private HorizontalAlignment parseHorizontalAlignment(String horizontalAlignmentString) {
		HorizontalAlignment rc = null;
		if (!"".equals(horizontalAlignmentString)) {
			rc = HorizontalAlignment.valueOf(horizontalAlignmentString);
		}
		return rc;
	}
	
	private VerticalAlignment parseVerticalAlignment(String verticalAlignmentString) {
		VerticalAlignment rc = null;
		if (!"".equals(verticalAlignmentString)) {
			rc = VerticalAlignment.valueOf(verticalAlignmentString);
		}
		return rc;
	}
	
	private Rectangle<Double> parseTextureCoordinates(String textureCoordinatesString) {
		Rectangle<Double> rc = null;
		if (!"".equals(textureCoordinatesString)) {
			String[] parts = textureCoordinatesString.split(",");
			rc = new Rectangle<Double>(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]),
					Double.parseDouble(parts[3]));
		}
		return rc;
	}
	
	private KeyboardEventListener parseKeyboardListener(String keyboardListenerString) {
		KeyboardEventListener rc = null;
		if (!"".equals(keyboardListenerString)) {
			switch (keyboardListenerString) {
			case ("SplashScreenKeyboardEventListener"):
				rc = new SplashScreenKeyboardEventListener();
				break;
			case ("MenuKeyboardEventListener"):
				rc = new MenuKeyboardEventListener();
				break;
			case ("OptionsMenuItemKeyboardEventListener"):
				rc = new OptionsMenuItemKeyboardEventListener();
				break;
			case ("ExitMenuItemKeyboardEventListener"):
				rc = new ExitMenuItemKeyboardEventListener();
				break;
			case ("OptionsMenuBackKeyboardEventListener"):
				rc = new OptionsMenuBackKeyboardEventListener();
				break;
			default:
				//TODO warn
			}
		}
		return rc;
	}
	
	private Integer[] parseTextSizes(String textSizeString) {
		Integer[] rc = null;
		if (!"".equals(textSizeString)) {
			String[] textSizeStringParts = textSizeString.split(",");
			rc = new Integer[]{Integer.parseInt(textSizeStringParts[0]), Integer.parseInt(textSizeStringParts[1])};
		}
		return rc;
	}
	
	private Boolean parseHidden(String hiddenString) {
		Boolean rc = false;
		if (!"".equals(hiddenString)) {
			rc = Boolean.parseBoolean(hiddenString);
		}
		return rc;
	}
	
}
