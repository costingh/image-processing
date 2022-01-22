package imageprocessing;

/**
 * Abstract class that holds details about an Image
 */
import java.awt.image.BufferedImage;

public abstract class ImageDetails extends Dimension {
	protected byte[] bytes;										// toti bytii imaginii
	protected String destinationPath;							// destinatia la care va fi scrisa imaginea
	protected BufferedImage bufferedImage;						// obiect care stocheaza imaginea sub forma de pixeli
	protected Dimension dimensions;								// dimensiunile imaginii (inaltime, latime)
	protected int newColorDepth;								// noul numar de biti pe care vor fi reprezentate culorile
	
	/**
	 * Constructor for Dimension Parent Class
	 * @param height denotes height of image in pixels
	 * @param width denotes width of image in pixels
	 */
	public ImageDetails(int height, int width) {
		super(height, width);
	}
	
	/**
	 * Abstract method that converts byte[] into BufferedImage
	 * @param imageBytes is a byte[]
	 */
	public abstract void convertImageFromBytesToPixels(byte[] imageBytes);
	
	/**
	 * Abstract method for writing the new image at a given path
	 */
	public abstract void writeImage();
	
	/**
	 * Abstract method for changing each pixel of an image
	 */
	public abstract void modifyImage();
	
	/**
	 * Setter for newColorDepth integer field
	 * @param newColorDepth is an integer value that represents the new bits of each color in a pixel
	 */
	public void setNewColorDepth(int newColorDepth) {					
		this.newColorDepth = newColorDepth;
	}
}
