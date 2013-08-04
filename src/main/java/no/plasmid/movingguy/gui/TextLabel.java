package no.plasmid.movingguy.gui;

import no.plasmid.movingguy.gui.dataobject.Font;
import no.plasmid.movingguy.gui.dataobject.Rectangle;

/**
 * This component will be rendered as a text label, over one line. Each character is rendered by itself, so final size
 * of the component can be calculated. Text will always begin on the bottom left of the component's bounds.
 * 
 * @author helgesk
 *
 */
public class TextLabel extends Panel {

	/**
	 * The textual value of the component
	 */
	private char[] value;
	
	/**
	 * Which font to use when rendering
	 */
	private Font font;
	
	/**
	 * The width of each character in pixels
	 */
	private int textWidth;
	
	/**
	 * The height of each character, in pixels
	 */
	private int textHeight;

	/**
	 * Constructor
	 */
	public TextLabel() {
		super();
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param textLabel the TextLabel to copy
	 */
	public TextLabel(TextLabel textLabel) {
		super(textLabel);
		
		setValue(new String(textLabel.value));
		setFont(textLabel.font);
		setTextWidth(textLabel.textWidth);
		setTextHeight(textLabel.textHeight);
	}
	
	/**
	 * @return a String with the textual value of the label
	 */
	public String getValue() {
		return new String(value);
	}
	
	/**
	 * Set the textual value of the component
	 * @param value the textual value of the component
	 */
	public void setValue(String value) {
		this.value = value.toCharArray();
	}

	/**
	 * @return the font used for rendering the text label
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Set which font to use when rendering the text label
	 * @param font the font that shall be used for rendering the text
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * @return the width of each character in pixels
	 */
	public int getTextWidth() {
		return textWidth;
	}

	/**
	 * Set the width of each character, in pixels
	 * @param textWidth the width of each character, in pixels
	 */
	public void setTextWidth(int textWidth) {
		this.textWidth = textWidth;
	}

	/**
	 * @return the height of each character in pixels
	 */
	public int getTextHeight() {
		return textHeight;
	}

	/**
	 * Set the width of each character, in pixels
	 * @param textHeight the height of each character, in pixels
	 */
	public void setTextHeight(int textHeight) {
		this.textHeight = textHeight;
	}

	@Override
	public void draw(GUIRenderer renderer) {
		if (!isHidden()) {
			int xPos = getActualBounds().getX1();
			int yPos = getActualBounds().getY1();
			
			for (char character : value) {
				//Draw each character of the label
				renderer.fillTexturedRectangle(new Rectangle<Integer>(xPos, yPos, xPos + textWidth, yPos + textHeight), getBackgroundColor(), font.getFontTexture(), Font.textureCoordinates.get(character));
				xPos += textWidth;
			}
			
			//Draw children
			for (Component child : getChildren()) {
				child.draw(renderer);
			}
		}
	}

}
