package no.plasmid.movingguy.gui;

import no.plasmid.movingguy.Configuration;
import no.plasmid.movingguy.gui.dataobject.Rectangle;
import no.plasmid.movingguy.gui.layout.PageLayoutEngine;

/**
 * The Page class is used to group components together for easy management. The GUI system will always have an active
 * page which is displayed.
 * 
 * @author helgesk
 *
 */
public class Page extends Component {

	/**
	 * The layout engine that shall always be used for pages
	 */
	private static PageLayoutEngine pageLayoutEngine = new PageLayoutEngine();
	
	/**
	 * The name of the component which has focus when the GUI manager switches to this page
	 */
	private String defaultFocusedComponentName;
	
	/**
	 * Constructor. The page will always cover the entire screen, but not actually be drawn itself.
	 */
	public Page() {
		super.setLayoutEngine(pageLayoutEngine);
		setRequestedBounds(new Rectangle<Integer>(0, 0, Configuration.WINDOW_WIDTH, Configuration.WINDOW_HEIGTH));
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param page the Page to copy
	 */
	public Page(Page page) {
		super(page);
		
		setDefaultFocusedComponentName(page.defaultFocusedComponentName);
	}
	
	/**
	 * @return the name of the component that should be focused when the GUI manager switches to this page
	 */
	public String getDefaultFocusedComponentName() {
		return defaultFocusedComponentName;
	}

	/**
	 * Set the name of the component that should be focused when the GUI manager switches to this page. 
	 * 
	 * @param defaultFocusedComponentName the name of the default focused component
	 */
	public void setDefaultFocusedComponentName(String defaultFocusedComponentName) {
		this.defaultFocusedComponentName = defaultFocusedComponentName;
	}

}
