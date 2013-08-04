package no.plasmid.movingguy.gui.template;

import no.plasmid.movingguy.gui.TextLabel;

public class TextLabelTemplate extends TextLabel implements Template<TextLabel> {

	@Override
	public Class<TextLabel> getComponentClass() {
		return TextLabel.class;
	}

}
