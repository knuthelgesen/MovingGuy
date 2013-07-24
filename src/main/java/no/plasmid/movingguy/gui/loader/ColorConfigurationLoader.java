package no.plasmid.movingguy.gui.loader;

import no.plasmid.movingguy.gui.Color;
import no.plasmid.movingguy.util.XmlConfigurationLoader;

import org.w3c.dom.Element;

public class ColorConfigurationLoader extends XmlConfigurationLoader {

	@Override
	protected void handleElement(Element configPartElement) {
		//Handle the element
		String type = configPartElement.getTagName();

		if ("Color".equals(type)) {
			//Read values
			String name = configPartElement.getAttribute("name");
			Color color = parseColor(configPartElement.getAttribute("values"));
			
			//Register the color
			valueContainer.addColor(name, color);
		}
	}

}
