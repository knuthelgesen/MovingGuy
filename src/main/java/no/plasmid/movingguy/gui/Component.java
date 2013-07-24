package no.plasmid.movingguy.gui;

import java.util.ArrayList;
import java.util.List;

import no.plasmid.movingguy.gui.RelativePositionLayoutEngine.HorizontalAlignment;
import no.plasmid.movingguy.gui.RelativePositionLayoutEngine.VerticalAlignment;
import no.plasmid.movingguy.gui.event.KeyboardEvent;
import no.plasmid.movingguy.gui.event.KeyboardEventListener;

/**
 * Base class for all GUI components
 * 
 * @author helgesk
 *
 */
public abstract class Component {
	
	/**
	 * The optional name of this component
	 */
	private String name = null;

	/**
	 * List of children components in the GUI component tree
	 */
	private List<Component> children;
	
	/**
	 * Parent component in the GUI component tree
	 */
	private Component parent = null;

	/**
	 * The layout engine used to calculate final position and size of the component
	 */
	private LayoutEngine layoutEngine = null;
	
	/**
	 * Alignment of the component on the horizontal plane
	 */
	private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
	
	/**
	 * Alignment of the component on the vertical plane
	 */
	private VerticalAlignment verticalAlignment = VerticalAlignment.TOP;
	
	/**
	 * The requested position and size
	 */
	private Rectangle<Integer> requestedBounds = Rectangle.zeroSizeIntegerRectangle;
	
	/**
	 * The actual position and size. Based on requested bounds, but calculated by the layout engine.
	 */
	private Rectangle<Integer> actualBounds = Rectangle.zeroSizeIntegerRectangle;
	
	/**
	 * Whether the component is hidden or not
	 */
	private Boolean hidden = false;
	
	/**
	 * Handler for keyboard events
	 */
	private KeyboardEventListener keyboardEventListener = null;
	
	/**
	 * Create a component
	 */
	public Component() {
		children = new ArrayList<Component>();
	}
	
	/**
	 * @return the name of the component
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the component
	 * @param name the name of the component
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the list of children
	 */
	public List<Component> getChildren() {
		return children;
	}
	
	/**
	 * Add a child to this component
	 * @param child the child that should be added as a child. Must not be the component itself, nor any parent.
	 */
	public void addChild(Component child) {
		if (child.equals(this) || child.isPresentInComponentTree(this)) {
			throw new IllegalArgumentException("Component can not be added as child to itself");
		}
		child.setParent(this);
		children.add(child);
	}
	
	/**
	 * Remove a child component
	 * @param child the child that should be removed
	 */
	public void removeChild(Component child) {
		child.setParent(null);
		children.remove(child);
	}
	
	/**
	 * Checks whether the component provided is found in the component tree at or below this component.
	 * 
	 * Example: If the parameter is found among the children of the component this method is called for, it will return
	 * true.<br>
	 * Example: If the parameter <code>equals</code> the component this method is called for, it will return true.<br>
	 * Example: If the parameter is not found among the children of the component this method is called for, it will
	 * return false.<br>
	 * Example: If the parameter is a parent of the component this method is called for, it will return false;<br>
	 * 
	 * @param comp the component to look for
	 * @return a value indicating whether the component was found
	 */
	public boolean isPresentInComponentTree(Component comp) {
		boolean rc = false;
		//Check if this is it
		if (this.equals(comp)) {
			//Yes!
			rc = true;
		} else {
			//No. Check children recursively
			for (Component child : children) {
				if (child.isPresentInComponentTree(comp)) {
					rc = true;
					break;
				}
			}
		}
		return rc;
	}
	
	/**
	 * @return the component's parent
	 */
	public Component getParent() {
		return parent;
	}
	
	/**
	 * Set the parent component. Only be called from addChild()
	 * @param parent the component's new parent
	 */
	private void setParent(Component parent) {
		this.parent = parent;
		calculateActualBounds();
	}
	
	/**
	 * @return the layout engine used by this component
	 */
	public LayoutEngine getLayoutEngine() {
		return layoutEngine;
	}

	/**
	 * Set which layout engine shall be used by this component. The layout engine calculates the final position and
	 * size of the component.
	 * @param layoutEngine
	 */
	public void setLayoutEngine(LayoutEngine layoutEngine) {
		this.layoutEngine = layoutEngine;
	}

	/**
	 * @return the horizontal alignment of this component
	 */
	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	/**
	 * Set the horizontal alignment of this component. This is used when calculating the actual bounds of the component
	 * relative to it's parent.
	 * @param horizontalAlignment the horizontal alignment of the component
	 */
	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	/**
	 * @return the vertical alignment of this component
	 */
	public VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	/**
	 * Set the vertical alignment of this component. This is used when calculating the actual bounds of the component
	 * relative to it's parent.
	 * @param verticalAlignment the vertical alignment of the component
	 */
	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	/**
	 * @return the requested size and position of the component
	 */
	public Rectangle<Integer> getRequestedBounds() {
		return requestedBounds;
	}
	
	/**
	 * Set the requested position and size of the component. Final position and size will be based on this, but
	 * modified by the layout engine.
	 * 
	 * @param requestedBounds a {@link Rectangle} with the new requested position and bounds
	 */
	public void setRequestedBounds(Rectangle<Integer> requestedBounds) {
		this.requestedBounds = requestedBounds;
		calculateActualBounds();
	}
	
	/**
	 * @return the actual bounds of this component, used when drawing.
	 */
	public Rectangle<Integer> getActualBounds() {
		return actualBounds;
	}

	/**
	 * @return a value indicating whether this component shall be drawn or not
	 */
	public Boolean isHidden() {
		return hidden;
	}

	/**
	 * Set whether this component shall be drawn or not
	 * @param hidden a value indicating whether this component shall be drawn or not
	 */
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * @return the keyboard event listener
	 */
	public KeyboardEventListener getKeyboardEventListener() {
		return keyboardEventListener;
	}
	
	/**
	 * Set the listener for keyboard events
	 * @param keyboardEventListener the handler, which must implement {@link KeyboardEventListener}
	 */
	public void setKeyboardEventListener(KeyboardEventListener keyboardEventListener) {
		this.keyboardEventListener = keyboardEventListener;
		keyboardEventListener.setOwner(this);
	}

	/**
	 * This method will be called by the rendering system to draw this component and any children.
	 * 
	 * The component class does not draw itself, therefore subclasses of Component should override this method if they want to be drawn.
	 * 
	 * @param renderer an implementation of {@link GUIRenderer} which is used for drawing this component
	 */
	public void draw(GUIRenderer renderer) {
		if (!isHidden()) {
			//The base class should not be drawn, but it's children shall
			for (Component child : children) {
				child.draw(renderer);
			}
		}
	}

	/**
	 * Use the layout engine to calculate the actual bounds for this component, and then make the child components do
	 * the same.
	 */
	private void calculateActualBounds() {
		if (layoutEngine != null) {
			actualBounds = layoutEngine.calculateBounds(this);
		}
		for (Component child : children) {
			child.calculateActualBounds();
		}
	}
	
	/**
	 * Handle a keyboard event by passing it to the KeyboardEventListener registered with this instance, or if the
	 * KeyboardEventListener is null or the event is not consumed, pass the event to the parent in the GUI component
	 * tree.
	 *  
	 * @param event the {@link KeyboardEvent}
	 */
	public final void handleKeyboardEvent(KeyboardEvent event) {
		if (keyboardEventListener == null) {
			//Let the parent handle the event
			if (parent != null) {
				parent.handleKeyboardEvent(event);
			}
		} else {
			if (keyboardEventListener.handleKeyboardEvent(event)) {
				//Event was consumed, so do nothing
			} else {
				//Event was not consumed, so let the parent handle it
				if (parent != null) {
					parent.handleKeyboardEvent(event);
				}
			}
		}
	}
	
}
