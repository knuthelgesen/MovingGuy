package no.plasmid.movingguy.gui.dataobject;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;

/**
 * Class that contains information about a texture that can be used when rendering with OpenGL
 * @author helgesk
 *
 */
public class Texture {

	/**
	 * OpenGL texture ID which signals that the texture is not registered with OpenGL
	 */
	public static final int GL_TEXTURE_ID_NOT_REGISTERED = 0;

	/**
	 * Used as placeholder
	 */
	public static final Texture EMPTY = new Texture("empty");	//Should be used as placeholder
	
	/**
	 * Name of the texture
	 */
	private String name;
	
	/**
	 * Texture ID from Open GL. This is generated when the texture is registered with OpenGL
	 */
	private int glTextureId; // Texture ID from OpenGL
	
	/**
	 * Width in pixels
	 */
	private int width;

	/**
	 * Height in pixels
	 */
	private int height;

	/**
	 * Pixel format, as used by OpenGL. Values should be set to those found in {@link GL11}
	 */
	private int pixelFormat;
	
	/**
	 * The image data
	 */
	private ByteBuffer imageData;

	/**
	 * Create an empty texture
	 * 
	 * @param name the name of the texture
	 */
	public Texture(String name) {
		this.name = name;
		
		//Begin as unregistered
		glTextureId = GL_TEXTURE_ID_NOT_REGISTERED;
	}
	
	/**
	 * @return the name of the texture
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the texture ID from OpenGL
	 */
	public int getGlTextureId() {
		return glTextureId;
	}

	/**
	 * Set the texture id generated by OpenGL
	 * @param glTextureId the texture ID generated by OpenGL
	 */
	public void setGlTextureId(int glTextureId) {
		this.glTextureId = glTextureId;
	}

	/**
	 * @return the width of the texture image
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Set the width of the texture image
	 * @param width the width of the texture image
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height of the texture image
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the height of the texture image
	 * @param height the height of the texture image
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the OpenGL texture format
	 */
	public int getPixelFormat() {
		return pixelFormat;
	}

	/**
	 * Set the OpenGL texture format, as they are defined in {@link GL11}
	 * @param pixelFormat the OpenGL texture format
	 */
	public void setPixelFormat(int pixelFormat) {
		this.pixelFormat = pixelFormat;
	}

	/**
	 * @return a byte buffer containing the image data
	 */
	public ByteBuffer getImageData() {
		return imageData;
	}

	/**
	 * Set the texture image data
	 * @param imageData a {@link ByteBuffer} containing the image data
	 */
	public void setImageData(ByteBuffer imageData) {
		this.imageData = imageData;
	}
}