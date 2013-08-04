package no.plasmid.movingguy.gui;

import no.plasmid.movingguy.Configuration;
import no.plasmid.movingguy.gui.loader.ColorConfigurationLoader;
import no.plasmid.movingguy.gui.loader.FontConfigurationLoader;
import no.plasmid.movingguy.gui.loader.GuiConfigurationLoader;
import no.plasmid.movingguy.gui.loader.TemplateConfigurationLoader;
import no.plasmid.movingguy.gui.loader.TextureConfigurationLoader;
import no.plasmid.movingguy.service.ServiceManager;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class GUI {
	
	/**
	 * Initialize the GUI services, load configuration files, and create the component tree.
	 */
	public void initializeGUI() {
		//First create the program window
		createProgramWindow();
	
		//Initialize services used by the GUI
		initializeGUIServices();
		
		//Load GUI configuration, and instantiate the GUI components
		loadGUIConfiguration();
	}
	
	/**
	 * Drop the components, and stop the GUI services.
	 */
	public void destroyGUI() {
		//Destroy the window
		Display.destroy();
	}
	
	/**
	 * First destroy, then initialize the GUI again
	 */
	public void restartGUI() {
		destroyGUI();
		
		initializeGUI();
	}
	
	/**
	 * Create the program window. Required before all other GUI initialization.
	 */
	private void createProgramWindow() {
		try {
			Configuration configuration = Configuration.getInstance();
	        String windowTitle = configuration.getValue("WINDOW_TITLE");
	        Integer windowWidth = configuration.getValue("WINDOW_WIDTH");
	        Integer windowHeight = configuration.getValue("WINDOW_HEIGHT");
	        Boolean fullscreen = configuration.getValue("FULLSCREEN");
	
	        //If no size is set, use the desktop mode as basis
	        DisplayMode desktopMode = Display.getDesktopDisplayMode();
	        
	        //If window size is not set, set to the same as desktop
	        if (null == windowWidth) {
	        	windowWidth = desktopMode.getWidth();
	        }
	        if (null == windowHeight) {
	        	windowHeight = desktopMode.getHeight();
	        }
	        
	        //Find out which size to use
	        if (fullscreen) {
	        	//We must choose one from the list of supported modes
	        	DisplayMode[] supportedModes = Display.getAvailableDisplayModes();
	        	
	        	int bestDiff = Integer.MAX_VALUE;
	        	DisplayMode bestMode = null;
	        	for (DisplayMode supportedMode : supportedModes) {
	        		int diff = Math.abs(supportedMode.getWidth() - windowWidth) + Math.abs(supportedMode.getHeight() - windowHeight);
	        		if (diff < bestDiff) {
	        			bestMode = supportedMode;
	        			bestDiff = diff;
	        		}
	        	}
	        	windowWidth = bestMode.getWidth();
	        	windowHeight = bestMode.getHeight();
	        } else {
	        	//In windowed mode, we can choose freely the size of the window, so no need to change the requested size
	        }
	
	        //Create the display mode
	        DisplayMode mode = new DisplayMode(windowWidth, windowHeight);
	        
	        //Create the pixel format
	        PixelFormat pixelFormat = new PixelFormat(32, 8, 16, 0, 16);
	        
	        //Create the display
        	Display.setDisplayMode(mode);
        	Display.setTitle(windowTitle);
	        Display.setFullscreen(fullscreen);
			Display.create(pixelFormat);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}

	/**
	 * Initialize services used by the GUI
	 */
	private void initializeGUIServices() {
		ServiceManager serviceManager = ServiceManager.getInstance();
		serviceManager.getRenderer().initializeService();
		serviceManager.getGUIDataObjectContainer().initializeService();
		serviceManager.getGUIManager().initializeService();
	}
	
	/**
	 * Load GUI configuration, and instantiate the components
	 */
	private void loadGUIConfiguration() {
		//Load color configuration
		ColorConfigurationLoader colorConfigLoader = new ColorConfigurationLoader();
		colorConfigLoader.loadConfigurationFile("/gui/colors.xml");

		//Load texture configuration
		TextureConfigurationLoader textureConfigLoader = new TextureConfigurationLoader();
		textureConfigLoader.loadConfigurationFile("/gui/textures.xml");
			
		//Load the font configuration
		FontConfigurationLoader fontConfigLoader = new FontConfigurationLoader();
		fontConfigLoader.loadConfigurationFile("/gui/fonts.xml");
		
		//Load templates from configuration file
		TemplateConfigurationLoader templateConfigLoader = new TemplateConfigurationLoader();
		templateConfigLoader.loadConfigurationFile("/gui/template.xml");

		//Load GUI from configuration file
		GuiConfigurationLoader guiConfigLoader = new GuiConfigurationLoader();
		guiConfigLoader.loadConfigurationFile("/gui/gui.xml");
	}
	
}
