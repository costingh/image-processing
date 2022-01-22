package imageprocessing;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Concrete class that extends Abstract Class ImageDetails and stores an image object as a byte array
 * @author Constantin Gheorghe
 *
 */
public class Image extends ImageDetails {
	public Image(byte[] bytes, String destinationPath) {
		super(0, 0);										// constructor clasa parinte Dimension(height, width)
		this.bytes = bytes;									// punem array-ul in clasa parinte
		this.destinationPath = destinationPath;				// salvam si calea destinatiei
	}
	
	/**
	 * Method that converts byte[] InputStream into BufferedImage object
	 * @param imageBytes represents the byte array that stores the image
	 */
	public void convertImageFromBytesToPixels(byte[] imageBytes) {// metoda ce converteste bytes[] in BufferedImage (BufferedImage va avea o dimensiume mai mare-BUG!!!)
		ByteArrayInputStream arr = new ByteArrayInputStream(imageBytes);		
	    try {
	        this.bufferedImage = ImageIO.read(arr);				// convertire byte[] -> BufferedImage (pentru a accesa pixelii)
	        this.dimensions = new Dimension(this.bufferedImage.getHeight(), this.bufferedImage.getWidth());
	    } catch (IOException e) {								// exceptie la citirea bytes[] in BufferedImage
	        throw new RuntimeException(e);
	    }
	}
	
	/**
	 * Method that writes a BufferedImage object as a bmp file at the current path (specified in the class field)
	 */
	public void writeImage() {									// metoda ce are rolul de a scrie bufferedImage la destinationPath
		RegisterTime timer = new RegisterTime();				// instanta care ne va ajuta sa calculam timpul de executie al unor metode
		File f = null;											// declarare instanta goala fisier
		timer.startTimer();										// start inregistrare timp
		
    	try { 		
    		f = new File(destinationPath);						// Deschidem fisierul
    		ImageIO.write(this.bufferedImage, "BMP", f); 		// scriem imaginea din bufferedImage, cu extensia .bmp            
    	} catch (FileNotFoundException e) {						// fisierul poate sa nu existe
	        System.out.println("Fisierul nu a fost gasit!");
	        System.out.println("Exceptie : " + e.getMessage());
    		System.exit(0);
	    } catch (IOException e) { 								// poate aparea o eroare la citirea imaginii din fisier
	        System.out.println("Eroare la scrierea in fisier!");
	        System.out.println("Exceptie : " + e.getMessage());
    		System.exit(0);
    	} 
    	
    	timer.stopTimer();										// stop inregistrare timp
		System.out.println("Scriere   : " + timer.getDuration() + "ms");
	}
	
	/**
	 * Method that takes the array of pixels from class fileds and changes the color depth from each pixel
	 * The old pixels are overwritten by the newest
	 */
	public void modifyImage() {											// metoda ce se ocupa de prelucrarea imaginii (aplicarea algoritmului)
		int imageHeight = dimensions.getHeight();						// luam inaltimea
		int imageWidth = dimensions.getWidth();							// salvam si latimea
		PixelsMatrix pixels = new PixelsMatrix(bufferedImage, imageHeight, imageWidth);	// cream o matrice de pixeli din bufferedImage
		RegisterTime timer = new RegisterTime();						// instanta care ne va ajuta sa calculam timpul de executie al unor metode
		
		timer.startTimer();												// start timer
		for(int row = 0; row < bufferedImage.getHeight(); row++) { 						
			for (int col = 0; col < bufferedImage.getWidth(); col++) {	// parcurgem fiecare pixel din matricea de pixeli 
				int pixel = pixels.getPixels()[row][col];				// luam pixelul
				Color color = new Color(pixel);							// luam culorile din pixel
				color.decreaseColorDepth(newColorDepth);		 		// reducem numarul de biti de reprezentare a culorilor la newColorDepth
                int newPixel = color.getColor();				 		// punem in noul pixel noile culori
                bufferedImage.setRGB(col, row, newPixel);			    // rescriem pixelul
			}
		}
		timer.stopTimer();												// stop timer
		System.out.println("Prelucrare: " + timer.getDuration() + "ms");// afisare timp executie
	}
}
