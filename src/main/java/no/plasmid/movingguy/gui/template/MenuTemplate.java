package no.plasmid.movingguy.gui.template;

import no.plasmid.movingguy.gui.Menu;

public class MenuTemplate extends Menu implements Template<Menu> {

	@Override
	public Class<Menu> getComponentClass() {
		return Menu.class;
	}

}
