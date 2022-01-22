package imageprocessing;

/**
 * Class that is used to store dimensions as height and width
 * @author Constantin Gheorghe
 *
 */
public class Dimension {
	private int height;						
	private int width;						
	
	/**
	 * Constructor that initializes height and width
	 * @param height integer that specifies height dimension
	 * @param width integer that specifies width dimension
	 */
	public Dimension(int height, int width) {	// constructor cu parametri
		super();
		this.height = height;
		this.width = width;
	}

	/**
	 * Getter method for height
	 * @return the height as an integer
	 */
	public int getHeight() {					// getter pentru inalatime
		return height;
	}
	
	/**
	 * Set height dimension
	 * @param height is the new height as integer
	 */
	public void setHeight(int height) { 		// setter pentru inalatime
		this.height = height;
	}
	
	/**
	 * Getter method for width
	 * @return the width as an integer
	 */
	public int getWidth() {						// getter latime
		return width;
	}
	
	/**
	 * Set width dimension
	 * @param width is the new width as integer
	 */
	public void setWidth(int width) {			// setter pentru latime
		this.width = width;
	}
}
