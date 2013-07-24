package no.plasmid.movingguy.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import no.plasmid.movingguy.TestDataCreator;
import no.plasmid.movingguy.gui.Color;
import no.plasmid.movingguy.service.GUIValueObjectContainer;
import no.plasmid.movingguy.service.ServiceManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class XmlConfigurationLoader {

	protected GUIValueObjectContainer valueContainer;
	
	public XmlConfigurationLoader() {
		valueContainer = ServiceManager.getInstance().getGUIValueObjectContainer();
	}
	
	public void loadConfigurationFile(String fileName) {
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = f.newDocumentBuilder();
			Document doc = builder.parse(TestDataCreator.class.getResourceAsStream(fileName));
			Element guiElement = doc.getDocumentElement();
			NodeList nodeList = guiElement.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node instanceof Element) {
					handleElement((Element)node);
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Handle the XML elements, and extract data. Implementation depends on the specific data to extract.
	 * 
	 * @param configPartElement the XML element to handle
	 */
	protected abstract void handleElement(Element configPartElement);
	
	/**
	 * Utility to parse a color string. The string can have two types of value:
	 * <li> A string giving the name of a color already defined. In this case the color will be looked up.
	 * <li> A string starting with '#' and then containing four hexadecimal values, corresponding to the red, green,
	 * blue and alpha value of the color. In this case the numbers will be parsed, and a new Color instance created.
	 *
	 * @param colorString the string describing the color
	 * @return the color either found, or created
	 */
	protected Color parseColor(String colorString) {
		Color rc = null;
		if (!"".equals(colorString)) {
			if (colorString.startsWith("#")) {
				//Parse the color as numbers
				double redChannel = (double)Integer.parseInt(colorString.substring(1, 3), 16) / 255.0;
				double greenChannel = (double)Integer.parseInt(colorString.substring(3, 5), 16) / 255.0;
				double blueChannel = (double)Integer.parseInt(colorString.substring(5, 7), 16) / 255.0;
				double alphaChannel = (double)Integer.parseInt(colorString.substring(7, 9), 16) / 255.0;
				rc = new Color(redChannel, greenChannel, blueChannel, alphaChannel);
			} else {
				//Find the color by name
				rc = valueContainer.getColor(colorString.toUpperCase());
			}
		}
		return rc;
	}
	
}
