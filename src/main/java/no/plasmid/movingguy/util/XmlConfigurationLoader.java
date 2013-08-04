package no.plasmid.movingguy.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import no.plasmid.movingguy.TestDataCreator;
import no.plasmid.movingguy.gui.dataobject.Color;
import no.plasmid.movingguy.gui.dataobject.Rectangle;
import no.plasmid.movingguy.gui.event.ExitMenuItemKeyboardEventListener;
import no.plasmid.movingguy.gui.event.KeyboardEventListener;
import no.plasmid.movingguy.gui.event.MenuKeyboardEventListener;
import no.plasmid.movingguy.gui.event.OptionsMenuBackKeyboardEventListener;
import no.plasmid.movingguy.gui.event.OptionsMenuItemKeyboardEventListener;
import no.plasmid.movingguy.gui.event.SplashScreenKeyboardEventListener;
import no.plasmid.movingguy.gui.layout.CoverParentLayoutEngine;
import no.plasmid.movingguy.gui.layout.LayoutEngine;
import no.plasmid.movingguy.gui.layout.RelativePositionLayoutEngine;
import no.plasmid.movingguy.gui.layout.RelativePositionLayoutEngine.HorizontalAlignment;
import no.plasmid.movingguy.gui.layout.RelativePositionLayoutEngine.VerticalAlignment;
import no.plasmid.movingguy.service.GUIDataObjectContainer;
import no.plasmid.movingguy.service.ServiceManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class XmlConfigurationLoader {

	protected GUIDataObjectContainer dataContainer;
	
	public XmlConfigurationLoader() {
		dataContainer = ServiceManager.getInstance().getGUIDataObjectContainer();
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
				rc = dataContainer.getColor(colorString.toUpperCase());
			}
		}
		return rc;
	}

	/**
	 * Parse a string containing the name of a layout engine and return the correct one.
	 * 
	 * @param layoutEngineString a string containing the name of a layout engine
	 * @return the layout engine, or <code>null</code> if not found
	 */
	protected LayoutEngine parseLayoutEngine(String layoutEngineString) {
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

	/**
	 * Parse a string containing the value for horizontal alignment.
	 * @param horizontalAlignmentString a string containing the value for horizontal alignment
	 * @return the value for horizontal alignment parsed from the string
	 */
	protected HorizontalAlignment parseHorizontalAlignment(String horizontalAlignmentString) {
		HorizontalAlignment rc = null;
		if (!"".equals(horizontalAlignmentString)) {
			rc = HorizontalAlignment.valueOf(horizontalAlignmentString);
		}
		return rc;
	}

	/**
	 * Parse a string containing the name of a {@link KeyboardEventListener}, and return a new instance of it if found
	 * @param keyboardListenerString the string containing the name of the keyboard event listener
	 * @return and instance of the listener, or <code>null</code> if not found
	 */
	protected KeyboardEventListener parseKeyboardListener(String keyboardListenerString) {
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
	
	/**
	 * Parse a string containing the values for texture coordinates (four double values).
	 * @param textureCoordinatesString a string containing the values for texture coordinates
	 * @return a rectangle containing the values for texture coordinates parsed from the string
	 */
	protected Rectangle<Integer> parseRequestedBounds(String requestedBoundsString) {
		Rectangle<Integer> rc = null;
		if (!"".equals(requestedBoundsString)) {
			String[] parts = requestedBoundsString.split(",");
			rc = new Rectangle<Integer>(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
					Integer.parseInt(parts[3]));
		}
		return rc;
	}
	
	/**
	 * Parse a string containing the values for text sizes (two integer values).
	 * @param textSizeString a string containing the values for text sizes
	 * @return an array containing the two values parsed from the string
	 */
	protected Integer[] parseTextSizes(String textSizeString) {
		Integer[] rc = null;
		if (!"".equals(textSizeString)) {
			String[] textSizeStringParts = textSizeString.split(",");
			rc = new Integer[]{Integer.parseInt(textSizeStringParts[0]), Integer.parseInt(textSizeStringParts[1])};
		}
		return rc;
	}
	
	/**
	 * Parse a string containing the values for texture coordinates (four double values).
	 * @param textureCoordinatesString a string containing the values for texture coordinates
	 * @return a rectangle containing the values for texture coordinates parsed from the string
	 */
	protected Rectangle<Double> parseTextureCoordinates(String textureCoordinatesString) {
		Rectangle<Double> rc = null;
		if (!"".equals(textureCoordinatesString)) {
			String[] parts = textureCoordinatesString.split(",");
			rc = new Rectangle<Double>(Double.parseDouble(parts[0].trim()), Double.parseDouble(parts[1].trim()),
					Double.parseDouble(parts[2].trim()), Double.parseDouble(parts[3].trim()));
		}
		return rc;
	}
	
	/**
	 * Parse a string containing the value for vertical alignment.
	 * @param verticalAlignmentString a string containing the value for vertical alignment
	 * @return the value for vertical alignment parsed from the string
	 */
	protected VerticalAlignment parseVerticalAlignment(String verticalAlignmentString) {
		VerticalAlignment rc = null;
		if (!"".equals(verticalAlignmentString)) {
			rc = VerticalAlignment.valueOf(verticalAlignmentString);
		}
		return rc;
	}
	
}
