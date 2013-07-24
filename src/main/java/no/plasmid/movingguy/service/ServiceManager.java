package no.plasmid.movingguy.service;

/**
 * This class manages all the services available for the application. It is implemented as a singleton. The
 * initialization method must be called before the services can reliably be used, as they must be initialized, and in
 * the correct order.
 * 
 * @author helgesk
 *
 */
public class ServiceManager {

	/**
	 * Static instance of the service manager
	 */
	private static ServiceManager instance = new ServiceManager();
	
	/**
	 * Get the instance
	 * @return
	 */
	public static ServiceManager getInstance() {
		return instance;
	}
	
	/*
	 * Services
	 */
	private GameManager gameManager;
	private GUIManager guiManager;
	private GUIValueObjectContainer guiValueObjectContainer;
	private InputHandler inputHandler;
	private ProgramStateManager programStateManager;
	private Renderer renderer;
	
	/**
	 * Private constructor
	 */
	private ServiceManager() {
		gameManager = new GameManager();
		guiManager = new GUIManager();
		guiValueObjectContainer = new GUIValueObjectContainer();
		inputHandler = new InputHandler();
		programStateManager = new ProgramStateManager();
		renderer = new Renderer();
	}
	
	/**
	 * Initialize all services in the correct order, so they are ready for use. Before this method is called, the
	 * behavior of the services can not be guaranteed.
	 */
	public void initializeServices() {
		gameManager.initializeService();
		inputHandler.initializeService();
		programStateManager.initializeService();
	}

	/**
	 * Get the service that manages all games, and loading and saving of them.
	 * 
	 * @return the {@link GameManager} instance
	 */
	public GameManager getGameManager() {
		return gameManager;
	}
	
	/**
	 * Get the service that manages the GUI. It will keep track of which components are visible, call components to be
	 * rendered, and pass input events to the component in focus.
	 * 
	 * @return the {@link GUIManager} instance
	 */
	public GUIManager getGUIManager() {
		return guiManager;
	}
	
	/**
	 * Get the service that acts as container for all known GUI value objects. These are normally read from
	 * configuration files.
	 * 
	 * @return the {@link GUIValueObjectContainer} instance
	 */
	public GUIValueObjectContainer getGUIValueObjectContainer() {
		return guiValueObjectContainer;
	}
	
	/**
	 * Get the service that manages input from the user. It will go through all input events from the OS, and interpret
	 * these before passing them to the GUI manager for further processing by the GUI layer.
	 * 
	 * @return the {@link InputHandler} instance
	 */
	public InputHandler getInputHandler() {
		return inputHandler;
	}
	
	public ProgramStateManager getProgramStateManager() {
		return programStateManager;
	}
	
	public Renderer getRenderer() {
		return renderer;
	}

}
