package no.plasmid.movingguy.gui.loader;

import no.plasmid.movingguy.gui.Font;
import no.plasmid.movingguy.gui.Texture;
import no.plasmid.movingguy.util.XmlConfigurationLoader;

import org.w3c.dom.Element;

public class FontConfigurationLoader extends XmlConfigurationLoader {

	@Override
	protected void handleElement(Element configPartElement) {
		//Handle the element
		String type = configPartElement.getTagName();

		if ("Font".equals(type)) {
			//Read values
			String name = configPartElement.getAttribute("name");
			Texture texture = valueContainer.getTexture(configPartElement.getAttribute("texture"));
			
			//Register the color
			valueContainer.addFont(name, new Font(texture));
		}
	}

}
