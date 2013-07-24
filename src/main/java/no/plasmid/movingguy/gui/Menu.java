package no.plasmid.movingguy.gui;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import no.plasmid.movingguy.service.GUIManager;
import no.plasmid.movingguy.service.ServiceManager;

/**
 * A menu is a component that maintains a set of {@link MenuItem} and allows the user to select one of them. On
 * selection, it will call a handler in the item.
 * 
 * It supports children normally, but menu items should be added using the method addMenuItem() to function properly.
 * 
 * @author helgesk
 *
 */
public class Menu extends TexturedPanel {
	
	/**
	 * Reference to the GUI manager, used to setting the focused component when the user selects different menu items
	 */
	private GUIManager guiManager;
	
	/**
	 * A set containing all the menu items. They are also added as children
	 */
	private Set<MenuItem> menuItems;
	
	/**
	 * Holds the currently selected menu item
	 */
	private MenuItem selectedItem = null;
	
	/**
	 * A textured panel used to show the currently selected item
	 */
	private TexturedPanel selectedItemMarker = null;
	
	/**
	 * Constructor
	 */
	public Menu() {
		guiManager = ServiceManager.getInstance().getGUIManager();
		
		menuItems = new LinkedHashSet<MenuItem>();
	}
	
	/**
	 * Add a new menu item. The item will be added as a child to the menu component, but this method must be used if
	 * the item shall be part of the menu.
	 * 
	 * @param menuItem the menu item to add
	 */
	public void addMenuItem(MenuItem menuItem) {
		if (menuItems.add(menuItem)) {
			addChild(menuItem);
		}
		
		//If no item is currently selected, use this one
		if (selectedItem == null) {
			setSelectedItem(menuItem);
		}
	}

	/**
	 * Remove a menu item. If the menu item is currently selected, the selection will be set to <code>null</code>
	 * 
	 * @param menuItem the menu item to remove
	 */
	public void removeMenuItem(MenuItem menuItem) {
		//Remove the item from item list
		menuItems.remove(menuItem);
		
		//Check if it's currently selected, and if so, set the currently selected item to null and update the marker
		if (menuItem.equals(selectedItem)) {
			selectedItem = null;
			if (selectedItemMarker != null) {
				selectedItemMarker.setRequestedBounds(new Rectangle<Integer>(0, 0, 0, 0));
			}
		}
		
		//Remove the item as a child
		removeChild(menuItem);
	}
	
	/**
	 * Set which item is selected. If the item is not in the set of menu items, it will be added.
	 * 
	 * @param selectedItem
	 */
	public void setSelectedItem(MenuItem selectedItem) {
		//Set selected item
		this.selectedItem = selectedItem;

		//Add to set if not already there
		addMenuItem(selectedItem);
		
		//Update selection marker
		if (selectedItemMarker != null) {
			Rectangle<Integer> itemBounds = selectedItem.getRequestedBounds();
			selectedItemMarker.setRequestedBounds(new Rectangle<Integer>(itemBounds.getX1() - 5, itemBounds.getY1() - 5, itemBounds.getX2() + 5, itemBounds.getY2() + 5));
		}
	
		//Set focus to the selected item
		guiManager.setFocusedComponent(selectedItem);
	}
	
	/**
	 * @return the currently selected item, or <code>null</code> if none is selected.
	 */
	public MenuItem getSelectedItem() {
		return selectedItem;
	}
	
	/**
	 * Will set the currently selected item to the next one in the set. If the set is empty, the selected item will be
	 * set to null. If the end of the set is reached, the selected item will be set to the first item.
	 * 
	 * @return the menu item which has been selected by this method
	 */
	public MenuItem selectNextItem() {
		MenuItem firstItem = null;
		for (Iterator<MenuItem> it = menuItems.iterator(); it.hasNext();) {
			MenuItem item = it.next();
			if (firstItem == null) {
				//Save the first item
				firstItem = item;
			}
			if (selectedItem == null) {
				//Currently selected item is null, so just set it to the first
				setSelectedItem(item);
				break;
			}
			if (selectedItem.equals(item)) {
				//Found the current item in the set
				if (it.hasNext()) {
					//Set the selected item to next one in set
					setSelectedItem(it.next());
					break;
				} else {
					//Set the selected item to first one in list
					setSelectedItem(firstItem);
					break;
				}
			}
		}
		
		return selectedItem;
	}
	
	/**
	 * Will set the currently selected item to the previous one in the set. If the set is empty, the selected item will
	 * be set to null. If the beginning of the set is reached, the selected item will be set to the last item.
	 * 
	 * @return the menu item which has been selected by this method
	 */
	public MenuItem selectPreviousItem() {
		MenuItem previousItem = null;
		for (Iterator<MenuItem> it = menuItems.iterator(); it.hasNext();) {
			MenuItem item = it.next();
			if (selectedItem == null) {
				//Currently selected item is null, so just set it to the first
				setSelectedItem(item);
				break;
			}
			if (selectedItem.equals(item)) {
				//Found the current item
				if (previousItem != null) {
					//Set to previous item
					setSelectedItem(previousItem);
					break;
				} else {
					//Set to last item in the set
					while (it.hasNext()) {
						item = it.next();
					}
					setSelectedItem(item);
					break;
				}
			}
			previousItem = item;
		}		
		return selectedItem;
	}
	
	/**
	 * Set the selection marker to use.
	 * 
	 * @param selectedItemMarker the textured panel to use as a selection marker
	 */
	public void setSelectedItemMarker(TexturedPanel selectedItemMarker) {
		this.selectedItemMarker = selectedItemMarker;
	}
	
	
	@Override
	public void draw(GUIRenderer renderer) {
		if (!isHidden()) {
			renderer.fillTexturedRectangle(getActualBounds(), getColor(), getTexture(), getTextureCoordinates());
			//Render children
			for (Component child : getChildren()) {
				child.draw(renderer);
			}
	
			//Render the marker for selected item
			if (selectedItemMarker != null && selectedItem != null) {
				selectedItemMarker.draw(renderer);
			}
		}		
	}

}
