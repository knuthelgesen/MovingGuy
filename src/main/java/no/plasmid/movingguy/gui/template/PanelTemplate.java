package no.plasmid.movingguy.gui.template;

import no.plasmid.movingguy.gui.Panel;

public class PanelTemplate extends Panel implements Template<Panel> {

	@Override
	public Class<Panel> getComponentClass() {
		return Panel.class;
	}

}
