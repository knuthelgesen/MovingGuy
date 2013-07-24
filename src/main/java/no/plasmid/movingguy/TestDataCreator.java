package no.plasmid.movingguy;

import no.plasmid.movingguy.gui.Page;
import no.plasmid.movingguy.service.GUIManager;
import no.plasmid.movingguy.service.ServiceManager;

public class TestDataCreator {

	private GUIManager guiManager;
	
	public TestDataCreator() {
		guiManager = ServiceManager.getInstance().getGUIManager();
	}
	
	/**
	 * This method will create test data
	 * @return
	 */
	public boolean createTestData() {
		boolean rc = true;

		//Create a new game
		ServiceManager.getInstance().getGameManager().createNewGame();

		//Set the splash screen as active page when the application started
		Page splashScreenPage = (Page)guiManager.getNamedComponent("SplashScreen");
		guiManager.setActivePage(splashScreenPage);

		return rc;
	}
	
}
