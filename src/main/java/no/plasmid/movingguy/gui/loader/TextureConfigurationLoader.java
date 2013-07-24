package no.plasmid.movingguy.gui.loader;

import org.w3c.dom.Element;

import no.plasmid.movingguy.util.TextureLoader;
import no.plasmid.movingguy.util.XmlConfigurationLoader;

public class TextureConfigurationLoader extends XmlConfigurationLoader {

	private TextureLoader textureLoader;
	
	public TextureConfigurationLoader() {
		textureLoader = new TextureLoader();
	}

	@Override
	protected void handleElement(Element configPartElement) {
		//Handle the element
		String type = configPartElement.getTagName();

		if ("Texture".equals(type)) {
			//Read values
			String name = configPartElement.getAttribute("name");
			String fileName = configPartElement.getAttribute("fileName");
			
			//Load and register the texture
			textureLoader.loadTexture(name, fileName);
		}
	}
	
}
