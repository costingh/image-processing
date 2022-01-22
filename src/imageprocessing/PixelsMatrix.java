package imageprocessing;

import java.awt.image.BufferedImage;

/**
 * Class used for storing pixels of an image as a 2d arry of integers
 * @author Constantin Gheorghe
 *
 */
public class PixelsMatrix {
	int[][] pixels;															// array 2d tip int care retine pixelii (pixelii sunt numere intregi pe 32 de biti)
	
	/**
	 * Constructor that creates a 2d array of integers with a given dimension
	 * @param bufferedImage is a BufferedImage instance that stores RGB colors of an image as an integer (pixel) used to fill in the 2d array with pixels
	 * @param height is the number of rows of the matrix (height in pixels of bufferedImage object)
	 * @param width is the number of columns of the matrix (width in pixels of bufferedImage object)
	 */
	public PixelsMatrix(BufferedImage bufferedImage, int height, int width) {		
		this.pixels = new int[height][width];								// alocare spatiu
		for(int row = 0; row < height; row++) {				
			for (int col = 0; col < width; col++) 	
				this.pixels[row][col] = bufferedImage.getRGB(col, row); 	// completare cu pixelul din bufferedImage
		}	
	}

	/**
	 * Method for getting the pixels of an image as 2d array
	 * @return a 2d array of integers (int[][])
	 */
	public int[][] getPixels() {
		return pixels;
	}
	
	/**
	 * Method used to set a specific pixel
	 * @param row is the row in 2d array where the old pixel is
	 * @param col is the column in 2d array where the old pixel is
	 * @param pixel is the new pixel that will override the old pixel
	 */
	public void setPixel(int row, int col, int pixel) {						// setare pixel[row][col] = pixel;
		this.pixels[row][col] = pixel;
	}
}
