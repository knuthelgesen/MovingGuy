package no.plasmid.movingguy.gui.event;

import no.plasmid.movingguy.gui.Page;
import no.plasmid.movingguy.service.GUIManager;
import no.plasmid.movingguy.service.ServiceManager;

/**
 * Implementation of the keyboard event listener that is used for splash screen. When any key is received, it will
 * signal the GUI manager to show the main menu page.
 * 
 * @author helgesk
 *
 */
public class SplashScreenKeyboardEventListener extends NoActionKeyboardEventListener {

	/**
	 * The GUI manager
	 */
	private GUIManager guiManager;
	
	/**
	 * Constructor
	 */
	public SplashScreenKeyboardEventListener() {
		guiManager = ServiceManager.getInstance().getGUIManager();
	}
	
	@Override
	protected boolean isConsumed(KeyboardEvent e) {
		//Consume all keys
		return true;
	}
	
	@Override
	protected void handleKeyDownEvent(KeyboardEvent e) {
		guiManager.setActivePage((Page)guiManager.getNamedComponent("MainMenuPage"));
	}
	
}
