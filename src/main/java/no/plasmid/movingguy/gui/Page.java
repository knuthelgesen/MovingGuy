package no.plasmid.movingguy.gui;

import no.plasmid.movingguy.Configuration;

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
	public Page(String name) {
		super.setName(name);
		
		super.setLayoutEngine(pageLayoutEngine);
		setRequestedBounds(new Rectangle<Integer>(0, 0, Configuration.WINDOW_WIDTH, Configuration.WINDOW_HEIGTH));
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
	public void setDefaultFocusedComponent(String defaultFocusedComponentName) {
		this.defaultFocusedComponentName = defaultFocusedComponentName;
	}
	
	@Override
	public void setLayoutEngine(LayoutEngine layoutEngine) {
		//Do nothing. The page shall always use the PageLayoutEngine
	}
	
	@Override
	public void setName(String name) {
		//Do nothing. The name can not be changed.
	}
	
}
