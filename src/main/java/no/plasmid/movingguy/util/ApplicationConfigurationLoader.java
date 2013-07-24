package no.plasmid.movingguy.util;

import no.plasmid.movingguy.Configuration;

import org.w3c.dom.Element;

public class ApplicationConfigurationLoader extends XmlConfigurationLoader {

	@Override
	protected void handleElement(Element configPartElement) {
		try {
			//Handle the element
			String elementType = configPartElement.getTagName();

			if ("ConfigItem".equals(elementType)) {
				//Read values
				String name = configPartElement.getAttribute("name");
				String type = configPartElement.getAttribute("type");
				String valueString = configPartElement.getAttribute("value");

				Class<?> valueClass = Class.forName(type);
				Object value = null;
				
				switch (valueClass.getCanonicalName()) {
				case "java.lang.Integer":
					value = Integer.parseInt(valueString);
					break;
				case "java.lang.Boolean":
					value = Boolean.parseBoolean(valueString);
					break;
				default:
					value = valueClass.cast(valueString);
				}
				Configuration.getInstance().setValue(name, value);
			}
		} catch (ClassNotFoundException e) {
			//TODO log error
		}
	}

}
