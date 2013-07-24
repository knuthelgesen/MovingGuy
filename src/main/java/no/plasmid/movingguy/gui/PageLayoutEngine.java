package no.plasmid.movingguy.gui;

import no.plasmid.movingguy.Configuration;

/**
 * This layout engine implementation will set the final bounds from 0 to the window size, so the component covers the
 * whole screen. This should be used by the page component.
 * 
 * @author helgesk
 *
 */
public class PageLayoutEngine implements LayoutEngine {

	@Override
	public Rectangle<Integer> calculateBounds(Component comp) {
		return new Rectangle<Integer>(0, 0, Configuration.WINDOW_WIDTH, Configuration.WINDOW_HEIGTH);
	}

}
