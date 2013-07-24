package no.plasmid.movingguy;

import no.plasmid.movingguy.gui.GUI;
import no.plasmid.movingguy.service.InputHandler;
import no.plasmid.movingguy.service.ProgramStateManager;
import no.plasmid.movingguy.service.Renderer;
import no.plasmid.movingguy.service.ServiceManager;
import no.plasmid.movingguy.service.ProgramStateManager.ProgramState;
import no.plasmid.movingguy.util.ApplicationConfigurationLoader;

import org.lwjgl.opengl.Display;

/**
 *	Main class for the application
 */
public class App 
{
	
	private InputHandler inputHandler;
	private ProgramStateManager programStateManager;
	private Renderer renderer;
	private GUI gui = null;
	
	public static void main( String[] args )
    {
    	App app = new App();
    	app.initializeApplication();
    	app.runApplication();
    	app.cleanupApplication();
    }

	private void initializeApplication() {
		//Load application configuration
		ApplicationConfigurationLoader appConfigLoader = new ApplicationConfigurationLoader();
		appConfigLoader.loadConfigurationFile("/app/configuration.xml");
	
		//Create the GUI object
		gui = new GUI();
 
        //Initialize the back end
        //TODO
        
        //Initialize the front end
		gui.initializeGUI();
		
		ServiceManager serviceManager = ServiceManager.getInstance();
        serviceManager.initializeServices();

        //Set program state
        programStateManager = serviceManager.getProgramStateManager();
        programStateManager.setCurrentProgramState(ProgramState.STARTING);
        
        //Get renderer
        renderer = serviceManager.getRenderer();
        
        //Get input handler
        inputHandler = serviceManager.getInputHandler();
        
        //Generate test data
        TestDataCreator testDataCreator = new TestDataCreator();
        if (!testDataCreator.createTestData()) {
        	//TODO Test data creation failure
        }
	}

	private void runApplication() {
		//Set program state to running
		programStateManager.setCurrentProgramState(ProgramState.RUNNING);
		
    	while (!programStateManager.getCurrentProgramState().equals(ProgramState.EXITING)) {
        	//Handle input
    		inputHandler.handleInput();
    		
    		//Render
    		renderer.render();
    		
    		//Lock to 60 FPS
    		Display.sync(60);
    		
    		//Update display
    		Display.update();
    	}
	}

	private void cleanupApplication() {
		//Destroy the GUI
		gui.destroyGUI();
	}
}
