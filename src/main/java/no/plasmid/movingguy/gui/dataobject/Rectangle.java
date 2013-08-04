package no.plasmid.movingguy.gui.dataobject;

/**
 * Class which contains four numbers, representing a rectangle. Can be used as drawing coordinates, texture coordinates etc
 * 
 * @author helgesk
 *
 * @param <T> which subclass of {@link Number} to use for the values in the instance
 */
public class Rectangle<T extends Number> {
	
	/**
	 * Static predefined rectangles
	 */
	public static final Rectangle<Integer> zeroSizeIntegerRectangle	= new Rectangle<Integer>(0, 0, 0, 0);
	public static final Rectangle<Double> zeroSizeDoubleRectangle 	= new Rectangle<Double>(0.0, 0.0, 0.0, 0.0);

	/**
	 * point 1 x value
	 */
	private T x1;
	
	/**
	 * point 1 y value
	 */
	private T y1;
	
	/**
	 * point 2 x value
	 */
	private T x2;
	
	/**
	 * point 2 y value
	 */
	private T y2;
	
	/**
	 * Create a Rectangle instance with the specified values
	 * @param x1 point 1 x value
	 * @param y1 point 1 y value
	 * @param x2 point 2 x value
	 * @param y2 point 2 y value
	 */
	public Rectangle(T x1, T y1, T x2, T y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * Copy constructor
	 * @param original the Rectangle to copy
	 */
	public Rectangle(Rectangle<T> original) {
		this.x1 = original.x1;
		this.y1 = original.y1;
		this.x2 = original.x2;
		this.y2 = original.y2;
	}
	
	/**
	 * @return point 1 x value
	 */
	public T getX1() {
		return x1;
	}

	/**
	 * Set point 1 x value
	 * @param x1 point 1 x value
	 */
	public void setX1(T x1) {
		this.x1 = x1;
	}

	/**
	 * @return point 1 y value
	 */
	public T getY1() {
		return y1;
	}

	/**
	 * Set point 1 y value
	 * @param x1 point 1 x value
	 */
	public void setY1(T y1) {
		this.y1 = y1;
	}

	/**
	 * @return point 2 x value
	 */
	public T getX2() {
		return x2;
	}

	/**
	 * Set point 2 x value
	 * @param x1 point 1 x value
	 */
	public void setX2(T x2) {
		this.x2 = x2;
	}

	/**
	 * @return point 2 y value
	 */
	public T getY2() {
		return y2;
	}

	/**
	 * Set point 2 y value
	 * @param x1 point 1 x value
	 */
	public void setY2(T y2) {
		this.y2 = y2;
	}
	
}
