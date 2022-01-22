package imageprocessing;

/**
 * Class that stores the colors of a given pixel
 * @author Constantin Gheorghe
 */
public class Color {		
    private int blue;
    private int bits;		// noul numar de biti al culorilor
    
    /**
     * Constructor with parameters that gets the colors of a pixel
     * @param pixel is a 32bit integer
     */
    public Color(int pixel) {
    	// acest pixel provine din imaginea de prelucrat (bmp cu 24-bit color depth)
    	// pixelul este un int, pe 32 de biti, culoarea fiind reprezentata pe 24 de biti
    	// deci mai raman 8 biti redundanti - la 32bit depth acestia retineau saturatia alpha
    	//   alpha       red	   green      blue
    	// xxxxxxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx 
    	// in cazul nostru, pixelul avand 24 de biti, rezulta ca transparenta alpha nu este specificata
    	// dar imaginea noastra este GRAYSCALE deci red = blue = green
    	
    	
    	// asa am fi luat fiecare favloare red, green, blue daca erau diferite (dar ele sunt egale)
    	//this.red   = (pixel >> 16) 	& 0xFF;  			// pentru a lua valoarea red, trebuie sa siftam pixel 16 de pozitii la dreapta, si sa eliminam ce e in stanga
    	//this.green = (pixel >> 8) 	& 0xFF; 			// pentru a lua valoarea green, trebuie sa siftam pixel 8 de pozitii la dreapta, si sa eliminam ce e in stanga
    	//this.blue  =  pixel 		& 0xFF; 				// pentru a lua valoarea blue, trebuie sa eliminam tot ce e in stanga
    	
    	// prin urmare este de ajuns sa luam doar valoarea blue
    	this.blue  =  pixel 		& 0xFF; 				// pentru a lua valoarea blue, trebuie sa eliminam tot ce e in stanga
    }
    
    /**
     * Method that decreases the color depth of a pixel
     * Each value from each channel RGB are equals and they are represented on 8 bits
     * This method decreases this 8 bits and replaces all values from each RGB channel with new value computed
     * @param bits is the new number of bits used to represent colors (ranges from 1 to 7)
     */
    public void decreaseColorDepth(int bits) {
    	//                                    red	           
       	// xxxxxxxx | xxxxxxxx | xxxxxxxx | rrrrrrrr - > rrrr
		//      							 green
		// xxxxxxxx | xxxxxxxx | xxxxxxxx | gggggggg - > gggg
		//     								  blue	           
		// xxxxxxxx | xxxxxxxx | xxxxxxxx | bbbbbbbb - > bbbb
    	// daca vrem sa reprezentam culorile pe 4 biti trebuie sa shiftam la dreapta cu 4 biti si sa eliminam tot ce ramane in stanga.
    	this.bits = bits;
    	blue  = blue  >> (8-bits);				// dorim sa pastram x biti, deci eliminam 8-x biti prin shiftare la dreapta
    	blue  = blue  << (8-bits);				// umplem cu 0 bitii ce au fos eliminati
    }
    
    /**
     * Method that uses bitwise operations to create a new pixel (int) using same blue value for all 3 channels RGB
     * @returns the new pixel as integer
     */
    public int getColor () {
    	int red = blue << 16;							// culoarea red   are aceeiasi valoare ca si blue, dar trebuie shiftata cu 16 pozitii la stanga
    	int green = blue << 8;							// culoarea green are aceeiasi valoare ca si blue, dar trebuie shiftata cu  8 pozitii la stanga
    	int newPixel = 00000000 | red | green | blue;   // construim un nou pixel cu noile valori rgb
    	return newPixel;
    }
}
