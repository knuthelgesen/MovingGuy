package no.plasmid.movingguy.gui;

import no.plasmid.movingguy.gui.template.MenuItemTemplate;

/**
 * Class used for menu items. Only instances of this class can be added to the menu and used as items.
 * 
 * @author helgesk
 *
 */
public class MenuItem extends Panel {

	/**
	 * Constructor
	 */
	public MenuItem() {
		super();
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param menuItem the MenuItem to copy
	 */
	public MenuItem(MenuItemTemplate menuItem) {
		super(menuItem);
	}

}
