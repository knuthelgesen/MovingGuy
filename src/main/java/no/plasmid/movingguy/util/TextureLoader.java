package no.plasmid.movingguy.util;

import java.io.FileNotFoundException;

import no.plasmid.movingguy.gui.Texture;
import no.plasmid.movingguy.service.GUIValueObjectContainer;
import no.plasmid.movingguy.service.Renderer;
import no.plasmid.movingguy.service.ServiceManager;

public class TextureLoader {

	/**
	 * The renderer
	 */
	private Renderer renderer;

	/**
	 * The GUI value object container
	 */
	private GUIValueObjectContainer valueObjectContainer;
	
	/**
	 * Used to load PNG images for textures
	 */
	private PNGImageLoader pngImageLoader;
	
	/**
	 * Constructor
	 */
	public TextureLoader() {
		pngImageLoader = new PNGImageLoader();

		renderer = ServiceManager.getInstance().getRenderer();
		valueObjectContainer = ServiceManager.getInstance().getGUIValueObjectContainer();
	}
		
	/**
	 * Will load an image file, put the data into a texture, register it with OpenGL, and store it.
	 * 
	 * Images must be found on the class path
	 * OpenGL must be initialized before calling this method
	 * 
	 * @param name
	 * @param fileName
	 */
	public void loadTexture(String name, String fileName) {
		//Create the texture
		Texture newTexture = new Texture(name);
		
		try {
			//Load the texture file
			pngImageLoader.loadTexture(newTexture, fileName);
			
			//Register with OpenGL
			renderer.registerTextureWithOpenGL(newTexture);

			//Store the texture
			valueObjectContainer.addTexture(newTexture);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
