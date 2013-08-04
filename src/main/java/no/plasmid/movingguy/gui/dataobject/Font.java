package no.plasmid.movingguy.gui.dataobject;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a font. Contains the information required to render characters using the font(i.e. a texture and
 * a map of texture coordinates)
 * 
 * @author helgesk
 *
 */
public class Font {

	/**
	 * Maps which texture coordinates belongs to which characters
	 */
	public static Map<Character, Rectangle<Double>> textureCoordinates;
	static {
		textureCoordinates = new HashMap<Character, Rectangle<Double>>();
		//Numbers
		textureCoordinates.put('0', new Rectangle<Double>(0.0, 0.1, 0.1, 0.0));
		textureCoordinates.put('1', new Rectangle<Double>(0.1, 0.1, 0.2, 0.0));
		textureCoordinates.put('2', new Rectangle<Double>(0.2, 0.1, 0.3, 0.0));
		
		//Upper case letters
		textureCoordinates.put('A', new Rectangle<Double>(0.0, 0.2, 0.1, 0.1));
		textureCoordinates.put('B', new Rectangle<Double>(0.1, 0.2, 0.2, 0.1));

		textureCoordinates.put('E', new Rectangle<Double>(0.4, 0.2, 0.5, 0.1));
		textureCoordinates.put('F', new Rectangle<Double>(0.5, 0.2, 0.6, 0.1));
		textureCoordinates.put('G', new Rectangle<Double>(0.6, 0.2, 0.7, 0.1));

		textureCoordinates.put('I', new Rectangle<Double>(0.8, 0.2, 0.9, 0.1));

		textureCoordinates.put('M', new Rectangle<Double>(0.2, 0.3, 0.3, 0.2));
		textureCoordinates.put('N', new Rectangle<Double>(0.3, 0.3, 0.4, 0.2));
		textureCoordinates.put('O', new Rectangle<Double>(0.4, 0.3, 0.5, 0.2));
		textureCoordinates.put('P', new Rectangle<Double>(0.5, 0.3, 0.6, 0.2));

		textureCoordinates.put('R', new Rectangle<Double>(0.7, 0.3, 0.8, 0.2));
		textureCoordinates.put('S', new Rectangle<Double>(0.8, 0.3, 0.9, 0.2));
		textureCoordinates.put('T', new Rectangle<Double>(0.9, 0.3, 1.0, 0.2));
		textureCoordinates.put('U', new Rectangle<Double>(0.0, 0.4, 0.1, 0.3));

		//Lower case letters
		textureCoordinates.put('a', new Rectangle<Double>(0.0, 0.5, 0.1, 0.4));

		textureCoordinates.put('c', new Rectangle<Double>(0.2, 0.5, 0.3, 0.4));

		textureCoordinates.put('e', new Rectangle<Double>(0.4, 0.5, 0.5, 0.4));
		
		textureCoordinates.put('i', new Rectangle<Double>(0.8, 0.5, 0.9, 0.4));
		
		textureCoordinates.put('k', new Rectangle<Double>(0.0, 0.6, 0.1, 0.5));
		textureCoordinates.put('l', new Rectangle<Double>(0.1, 0.6, 0.2, 0.5));
		textureCoordinates.put('m', new Rectangle<Double>(0.2, 0.6, 0.3, 0.5));
		textureCoordinates.put('n', new Rectangle<Double>(0.3, 0.6, 0.4, 0.5));
		textureCoordinates.put('o', new Rectangle<Double>(0.4, 0.6, 0.5, 0.5));
		textureCoordinates.put('p', new Rectangle<Double>(0.5, 0.6, 0.6, 0.5));

		textureCoordinates.put('r', new Rectangle<Double>(0.7, 0.6, 0.8, 0.5));
		textureCoordinates.put('s', new Rectangle<Double>(0.8, 0.6, 0.9, 0.5));
		textureCoordinates.put('t', new Rectangle<Double>(0.9, 0.6, 1.0, 0.5));
		textureCoordinates.put('u', new Rectangle<Double>(0.0, 0.7, 0.1, 0.6));

		textureCoordinates.put('w', new Rectangle<Double>(0.2, 0.7, 0.3, 0.6));
		textureCoordinates.put('x', new Rectangle<Double>(0.3, 0.7, 0.4, 0.6));
	
		//Other characters
		textureCoordinates.put(' ', new Rectangle<Double>(0.9, 0.4, 1.0, 0.3));
	}

	/**
	 * The texture used when rendering this font
	 */
	private final Texture fontTexture;
	
	/**
	 * Set the texture used when rendering this font
	 * @param fontTexture the texture used when rendering this font
	 */
	public Font(Texture fontTexture) {
		this.fontTexture = fontTexture;
	}

	/**
	 * @return the texture used when rendering this font
	 */
	public Texture getFontTexture() {
		return fontTexture;
	}

}
