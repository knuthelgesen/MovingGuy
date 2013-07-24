package no.plasmid.movingguy.gui;

/**
 * A color that can be used when rendering with OpenGL. Will contain 4 double values.
 * 
 * @author helgesk
 *
 */
public class Color {

	/**
	 * OpenGL color values for this color. Values should be [0.0 - 1.0]
	 */
	private double[] colorValues;
	
	/**
	 * Create a color with all values at 0.0
	 */
	public Color() {
		colorValues = new double[4];
	}
	
	/**
	 * Create a color instance with the provided values
	 * 
	 * @param colorValues The values for each channel of this color. Values should be [0.0 - 1.0], and the array must
	 * be of length 4
	 */
	public Color(double[] colorValues) {
		if (colorValues.length != 4) {
			throw new IllegalArgumentException("Number of color values must be 4");
		}
		this.colorValues = colorValues.clone();
	}
	
	/**
	 * Create a color instance with the provided values. Values should be [0.0 - 1.0]
	 * 
	 * @param r value for <em>red</em> channel
	 * @param g value for <em>green</em> channel
	 * @param b value for <em>blue</em> channel
	 * @param a value for <em>alpha</em> channel
	 */
	public Color(double r, double g, double b, double a) {
		this(new double[]{r, g, b, a});
	}
	
	/**
	 * Get the color values
	 * 
	 * @return an array containing the color values for the four channels
	 */
	public double[] getColorValues() {
		return colorValues;
	}
	
}
