package no.plasmid.movingguy.gui.loader;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import no.plasmid.movingguy.gui.Component;
import no.plasmid.movingguy.gui.ComponentFactory;
import no.plasmid.movingguy.gui.Menu;
import no.plasmid.movingguy.gui.MenuItem;
import no.plasmid.movingguy.gui.Panel;
import no.plasmid.movingguy.gui.dataobject.Color;
import no.plasmid.movingguy.gui.dataobject.Font;
import no.plasmid.movingguy.gui.dataobject.Rectangle;
import no.plasmid.movingguy.gui.dataobject.Texture;
import no.plasmid.movingguy.gui.event.KeyboardEventListener;
import no.plasmid.movingguy.gui.layout.LayoutEngine;
import no.plasmid.movingguy.gui.layout.RelativePositionLayoutEngine.HorizontalAlignment;
import no.plasmid.movingguy.gui.layout.RelativePositionLayoutEngine.VerticalAlignment;
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
	
	private void handleElement(Component parent, Element configPartElement) {
		//Handle the element
		Component createdComponent = null;
		String type = configPartElement.getTagName();

		//Read values
		String name = configPartElement.getAttribute("name");
		String focusedComponentName = configPartElement.getAttribute("focusedComponentName");
		Color backgroundColor = parseColor(configPartElement.getAttribute("backgroundColor"));
		LayoutEngine layoutEngine = parseLayoutEngine(configPartElement.getAttribute("layoutEngine"));
		Rectangle<Integer> requestedBounds = parseRequestedBounds(configPartElement.getAttribute("requestedBounds"));
		HorizontalAlignment horizontalAlignment = parseHorizontalAlignment(configPartElement.getAttribute("horizontalAlignment"));
		VerticalAlignment verticalAlignment = parseVerticalAlignment(configPartElement.getAttribute("verticalAlignment"));
		Texture texture = dataContainer.getTexture(configPartElement.getAttribute("texture"));
		Rectangle<Double> textureCoordinates = parseTextureCoordinates(configPartElement.getAttribute("textureCoordinates"));
		String value = configPartElement.getAttribute("value");
		Font font = dataContainer.getFont(configPartElement.getAttribute("font"));
		Integer[] textSizes = parseTextSizes(configPartElement.getAttribute("textSize"));
		Boolean hidden = parseHidden(configPartElement.getAttribute("hidden"));
		KeyboardEventListener keyboardEventListener = parseKeyboardListener(configPartElement.getAttribute("keyboardEventListener"));
		
		switch (type) {
		case "Page":
			createdComponent = ComponentFactory.createPage(name, focusedComponentName, keyboardEventListener);
			break;
		case "Panel":
			createdComponent = ComponentFactory.createPanel(name, backgroundColor, requestedBounds, layoutEngine,
					horizontalAlignment, verticalAlignment, texture, textureCoordinates, keyboardEventListener);
			break;
		case "Menu":
			createdComponent = ComponentFactory.createMenu(name, backgroundColor, requestedBounds, layoutEngine,
					horizontalAlignment, verticalAlignment, texture, textureCoordinates, keyboardEventListener);
			break;
		case "MenuItem":
			createdComponent = ComponentFactory.createMenuItem(name, backgroundColor, requestedBounds, layoutEngine,
					horizontalAlignment, verticalAlignment, texture, textureCoordinates, keyboardEventListener);
			((Menu)parent).addMenuItem((MenuItem)createdComponent);
			break;
		case "SelectedItemMarker":
			createdComponent = ComponentFactory.createPanel(name, backgroundColor, requestedBounds, layoutEngine,
					horizontalAlignment, verticalAlignment, texture, textureCoordinates, keyboardEventListener);
			((Menu)parent).setSelectedItemMarker((Panel)createdComponent);
			break;
		case "TextLabel":
			createdComponent = ComponentFactory.createTextLabel(name, backgroundColor, requestedBounds, layoutEngine,
					horizontalAlignment, verticalAlignment, texture, textureCoordinates, value, font,
					null != textSizes ? textSizes[0] : null, null != textSizes ? textSizes[1] : null,
					keyboardEventListener);
			break;
		default:
			//TODO Log warning for unknown tag type
		}

		//Add keyboard event listener
		if (createdComponent != null) {
			if (null != parent) {
				parent.addChild(createdComponent);
			}
			createdComponent.setHidden(hidden);
		}
		
		//Handle children
		NodeList nodeList = configPartElement.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node instanceof Element) {
				handleElement(createdComponent, (Element)node);
			}
		}
	}
	
	private Boolean parseHidden(String hiddenString) {
		Boolean rc = false;
		if (!"".equals(hiddenString)) {
			rc = Boolean.parseBoolean(hiddenString);
		}
		return rc;
	}
	
}
