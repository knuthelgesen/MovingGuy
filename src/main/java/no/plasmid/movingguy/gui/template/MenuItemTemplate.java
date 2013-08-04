package no.plasmid.movingguy.gui.template;

import no.plasmid.movingguy.gui.MenuItem;

public class MenuItemTemplate extends MenuItem implements Template<MenuItem> {

	@Override
	public Class<MenuItem> getComponentClass() {
		return MenuItem.class;
	}

}
