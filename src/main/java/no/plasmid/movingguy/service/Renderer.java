package no.plasmid.movingguy.service;

import java.nio.IntBuffer;

import no.plasmid.movingguy.Configuration;
import no.plasmid.movingguy.gui.Color;
import no.plasmid.movingguy.gui.GUIRenderer;
import no.plasmid.movingguy.gui.Page;
import no.plasmid.movingguy.gui.Rectangle;
import no.plasmid.movingguy.gui.Texture;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Renderer implements GUIRenderer, Service {

	private GUIManager guiManager;
	
	private IntBuffer glTextureIdBuffer; // Used when making the texture IDs

	public void render() {
		//Clear the display
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		//Prepare for 2D rendering
		prepare2D();
		
		GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
		
		Page activePage = guiManager.getActivePage();
		activePage.draw(this);
		
		//Check for errors
		checkGL();
	}
	
	public void registerTextureWithOpenGL(Texture texture) {
		//Enable texturing
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		// Generate OpenGL texture id for the new texture
		int glTextureId = generateGlTextureId();

		// Bind the new texture
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, glTextureId);

		// Generate the texture in OpenGL
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, texture.getWidth(), texture.getHeight(), 0, texture.getPixelFormat(), GL11.GL_UNSIGNED_BYTE, texture.getImageData());
		
		// Set the OpenGL texture ID
		texture.setGlTextureId(glTextureId);
		
		checkGL();
	}
	
	@Override
	public void initializeService() {
		//Get the GUI manager
		guiManager = ServiceManager.getInstance().getGUIManager();

		//Create buffers
		glTextureIdBuffer = BufferUtils.createIntBuffer(1);

		//Enable blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		//Check for errors
		checkGL();
	}

	@Override
	public void fillRectangle(Rectangle<Integer> bounds, Color color) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		GL11.glColor4d(color.getColorValues()[0], color.getColorValues()[1], color.getColorValues()[2], color.getColorValues()[3]);

		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2i(bounds.getX1(), bounds.getY1());
			GL11.glVertex2i(bounds.getX2(), bounds.getY1());
			GL11.glVertex2i(bounds.getX2(), bounds.getY2());
			GL11.glVertex2i(bounds.getX1(), bounds.getY2());
		}
		GL11.glEnd();
	}
	
	@Override
	public void fillTexturedRectangle(Rectangle<Integer> bounds, Color color,
			Texture texture, Rectangle<Double> textureCoordinates) {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		GL11.glColor4d(color.getColorValues()[0], color.getColorValues()[1], color.getColorValues()[2], color.getColorValues()[3]);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getGlTextureId());
		
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2d(textureCoordinates.getX1(), textureCoordinates.getY1());
			GL11.glVertex2i(bounds.getX1(), bounds.getY1());
			GL11.glTexCoord2d(textureCoordinates.getX2(), textureCoordinates.getY1());
			GL11.glVertex2i(bounds.getX2(), bounds.getY1());
			GL11.glTexCoord2d(textureCoordinates.getX2(), textureCoordinates.getY2());
			GL11.glVertex2i(bounds.getX2(), bounds.getY2());
			GL11.glTexCoord2d(textureCoordinates.getX1(), textureCoordinates.getY2());
			GL11.glVertex2i(bounds.getX1(), bounds.getY2());
		}
		GL11.glEnd();
	}
	
	/**
	 * Check for OpenGL error, and throw exception if any are found
	 */
	private void checkGL() {
		final int code = GL11.glGetError();
		if (code != 0) {
			final String errorString = GLU.gluErrorString(code);
			final String message = "OpenGL error (" + code + "): " + errorString;
			throw new IllegalStateException(message);
		}
	}

    /**
     * Prepare for 2D rendering
     */
	private void prepare2D() {
		// Prepare projection matrix to render in 2D
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity(); // clear the perspective matrix
		GL11.glOrtho( // turn on 2D mode
				0, Configuration.WINDOW_WIDTH, // left, right
				0, Configuration.WINDOW_HEIGTH, // bottom, top
				-1, 1); // Zfar, Znear

		// Prepare the modelview matrix
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity(); // Clear the Modelview Matrix

		//Check for errors
		checkGL();
	}

	/**
	 * Ask OpenGL to generate room for a new texture, and return it's ID
	 * 
	 * @return
	 */
	private int generateGlTextureId() {
		GL11.glGenTextures(glTextureIdBuffer);
		return glTextureIdBuffer.get(0);
	}

}
