package no.plasmid.movingguy.gui.loader;

import org.w3c.dom.Element;

import no.plasmid.movingguy.gui.Component;
import no.plasmid.movingguy.gui.dataobject.Color;
import no.plasmid.movingguy.gui.dataobject.Font;
import no.plasmid.movingguy.gui.dataobject.Rectangle;
import no.plasmid.movingguy.gui.dataobject.Texture;
import no.plasmid.movingguy.gui.event.KeyboardEventListener;
import no.plasmid.movingguy.gui.layout.LayoutEngine;
import no.plasmid.movingguy.gui.layout.RelativePositionLayoutEngine.HorizontalAlignment;
import no.plasmid.movingguy.gui.layout.RelativePositionLayoutEngine.VerticalAlignment;
import no.plasmid.movingguy.gui.template.ComponentTemplateFactory;
import no.plasmid.movingguy.gui.template.Template;
import no.plasmid.movingguy.util.XmlConfigurationLoader;

public class TemplateConfigurationLoader extends XmlConfigurationLoader {

	@Override
	protected void handleElement(Element configPartElement) {
		//Read data
		Color backgroundColor = parseColor(configPartElement.getAttribute("backgroundColor"));
		HorizontalAlignment horizontalAlignment = parseHorizontalAlignment(configPartElement.getAttribute("horizontalAlignment"));
		LayoutEngine layoutEngine = parseLayoutEngine(configPartElement.getAttribute("layoutEngine"));
		Rectangle<Integer> requestedBounds = parseRequestedBounds(configPartElement.getAttribute("requestedBounds"));
		Texture texture = dataContainer.getTexture(configPartElement.getAttribute("texture"));
		Rectangle<Double> textureCoordinates = parseTextureCoordinates(configPartElement.getAttribute("textureCoordinates"));
		VerticalAlignment verticalAlignment = parseVerticalAlignment(configPartElement.getAttribute("verticalAlignment"));
		KeyboardEventListener keyboardEventListener = parseKeyboardListener(configPartElement.getAttribute("keyboardEventListener"));
		String textValue = configPartElement.getAttribute("value");
		Font font = dataContainer.getFont(configPartElement.getAttribute("font"));
		Integer[] textSizes = parseTextSizes(configPartElement.getAttribute("textSize"));

		//Handle the element
		String type = configPartElement.getTagName();
		
		Template<? extends Component> createdTemplate = null;
		
		switch (type) {
		case "Page":
			createdTemplate = ComponentTemplateFactory.createPageTemplate(null, null, keyboardEventListener);
			break;
		case "Panel":
			createdTemplate = ComponentTemplateFactory.createPanelTemplate(null, backgroundColor, requestedBounds,
					layoutEngine, horizontalAlignment, verticalAlignment, texture, textureCoordinates,
					keyboardEventListener);
			break;
		case "Menu":
			createdTemplate = ComponentTemplateFactory.createMenuTemplate(null, backgroundColor, requestedBounds,
					layoutEngine, horizontalAlignment, verticalAlignment, texture, textureCoordinates,
					keyboardEventListener);
			break;
		case "MenuItem":
			createdTemplate = ComponentTemplateFactory.createMenuItemTemplate(null, backgroundColor, requestedBounds,
					layoutEngine, horizontalAlignment, verticalAlignment, texture, textureCoordinates,
					keyboardEventListener);
			break;
		case "TextLabel":
			createdTemplate = ComponentTemplateFactory.createTextLabelTemplate(null, backgroundColor, requestedBounds,
					layoutEngine, horizontalAlignment, verticalAlignment, texture, textureCoordinates, textValue, font,
					null != textSizes ? textSizes[0] : null, null != textSizes ? textSizes[1] : null,
					keyboardEventListener);
			break;
		default:
			//TODO log
		}
	
		if (createdTemplate != null) {
			//Add template to container
			dataContainer.addTemplate(createdTemplate);
		}
	}

}
