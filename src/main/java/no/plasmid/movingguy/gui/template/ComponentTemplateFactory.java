package no.plasmid.movingguy.gui.template;

import no.plasmid.movingguy.gui.ComponentFactory;
import no.plasmid.movingguy.gui.Page;
import no.plasmid.movingguy.gui.Panel;
import no.plasmid.movingguy.gui.dataobject.Color;
import no.plasmid.movingguy.gui.dataobject.Font;
import no.plasmid.movingguy.gui.dataobject.Rectangle;
import no.plasmid.movingguy.gui.dataobject.Texture;
import no.plasmid.movingguy.gui.event.KeyboardEventListener;
import no.plasmid.movingguy.gui.layout.LayoutEngine;
import no.plasmid.movingguy.gui.layout.RelativePositionLayoutEngine.HorizontalAlignment;
import no.plasmid.movingguy.gui.layout.RelativePositionLayoutEngine.VerticalAlignment;

public class ComponentTemplateFactory extends ComponentFactory {

	/**
	 * Create a template for the {@link Page} component
	 * @param name
	 * @param defaultActiveComponentName
	 * @return
	 */
	public static PageTemplate createPageTemplate(String name, String defaultActiveComponentName,
			KeyboardEventListener keyboardEventListener) {
		//Create the template
		PageTemplate rc = new PageTemplate();
		
		//Set Component specific data
		setComponentValues(rc, name, null, null, null, null, keyboardEventListener);
		
		//Set Panel specific values
		setPageValues(rc, defaultActiveComponentName);
		
		return rc;
	}
	
	/**
	 * Create a template for the {@link Panel} component
	 * @param name
	 * @param backgroundColor
	 * @param requestedBounds
	 * @param layoutEngine
	 * @param horizontalAlignment
	 * @param verticalAlignment
	 * @param texture
	 * @param textureCoordinates
	 * @return
	 */
	public static PanelTemplate createPanelTemplate(String name, Color backgroundColor,
			Rectangle<Integer> requestedBounds, LayoutEngine layoutEngine, HorizontalAlignment horizontalAlignment,
			VerticalAlignment verticalAlignment, Texture texture, Rectangle<Double> textureCoordinates,
			KeyboardEventListener keyboardEventListener) {
		//Create the template
		PanelTemplate rc = new PanelTemplate();

		//Set Component specific data
		setComponentValues(rc, name, layoutEngine, horizontalAlignment, verticalAlignment, requestedBounds,
				keyboardEventListener);
		
		//Set Panel specific values
		setPanelValues(rc, backgroundColor, texture, textureCoordinates);
		
		return rc;
	}
	
	public static MenuTemplate createMenuTemplate(String name, Color backgroundColor,
			Rectangle<Integer> requestedBounds, LayoutEngine layoutEngine, HorizontalAlignment horizontalAlignment,
			VerticalAlignment verticalAlignment, Texture texture, Rectangle<Double> textureCoordinates,
			KeyboardEventListener keyboardEventListener) {
		//Create the menu template
		MenuTemplate rc = new MenuTemplate();
		
		//Set Component specific data
		setComponentValues(rc, name, layoutEngine, horizontalAlignment, verticalAlignment, requestedBounds,
				keyboardEventListener);
		
		//Set Panel specific values
		setPanelValues(rc, backgroundColor, texture, textureCoordinates);
		
		return rc;
	}
	
	public static MenuItemTemplate createMenuItemTemplate(String name, Color backgroundColor,
			Rectangle<Integer> requestedBounds, LayoutEngine layoutEngine, HorizontalAlignment horizontalAlignment,
			VerticalAlignment verticalAlignment, Texture texture, Rectangle<Double> textureCoordinates,
			KeyboardEventListener keyboardEventListener) {
		//Create the menu item template
		MenuItemTemplate rc = new MenuItemTemplate();
		
		//Set Component specific data
		setComponentValues(rc, name, layoutEngine, horizontalAlignment, verticalAlignment, requestedBounds,
				keyboardEventListener);
		
		//Set Panel specific values
		setPanelValues(rc, backgroundColor, texture, textureCoordinates);
		
		return rc;
	}

	public static TextLabelTemplate createTextLabelTemplate(String name, Color backgroundColor,
			Rectangle<Integer> requestedBounds, LayoutEngine layoutEngine, HorizontalAlignment horizontalAlignment,
			VerticalAlignment verticalAlignment, Texture texture, Rectangle<Double> textureCoordinates,
			String textValue, Font font, Integer textWidth, Integer textHeight,
			KeyboardEventListener keyboardEventListener) {
		//Create the text label template
		TextLabelTemplate rc = new TextLabelTemplate();
	
		//Set Component specific data
		setComponentValues(rc, name, layoutEngine, horizontalAlignment, verticalAlignment, requestedBounds,
				keyboardEventListener);
		
		//Set Panel specific values
		setPanelValues(rc, backgroundColor, texture, textureCoordinates);
		
		//Set TextLabel specific values
		setTextLabelValues(rc, textValue, font, textWidth, textHeight);

		return rc;
	}
}
