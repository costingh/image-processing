package imageprocessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Concrete class that extends ProducerConsumer
 * It is used for rading an image as a byte array and storing it into a buffer
 * @author Constantin Gheorghe
 *
 */
public class Producer extends ProducerConsumer { 	
	private String filePath;											// calea de la care incarcam imaginea
	private Buffer buffer;												// bufferul in care punem imaginea sub forma de byte[] - array de bytes
	
	/**
	 * Constructor with parameters
	 * @param buffer is a refference to a Buffer object used by other thread
	 * @param filePath is the path from where the image will be readed
	 */
	public Producer (Buffer buffer, String filePath) { 					
		this.buffer = buffer;
		this.filePath = filePath;
	}

	/**
	 * Method that puts in buffer a quarter of the image while reading it, then waits for Consumer Thread to consume it
	 */
	@Override
	public void process() {
    	try {
    		File file = new File(filePath);					    		// Deschidem fisierul
    		FileInputStream in = new FileInputStream(file);				// cream un FileInputStream pentru fisierul curent
    		long size = file.length();									// retinem dimensiunea fisierului in bytes    		
			RegisterTime timer = new RegisterTime();					// instanta care ne va ajuta sa calculam timpul de executie al unor metode
			
    		for (int sfert = 0; sfert < 4; sfert++) {					// luam cate un sfert de imagine
				timer.startTimer();										// ---- START ----
    			byte[] arrayOfBytes = new byte[(int)size/4];			// cream un array de bytes care are dimensiunea egala cu un sfert din cea a fisierului
    			in.read(arrayOfBytes); 									// daca suntem la primul sfert, atunci citim tot sfertul in arrayOfBytes
    			timer.stopTimer();										// ----  STOP ----
    			buffer.put(arrayOfBytes);								// punem sfertul in buffer si asteptam raspunsul consumatorului
    			System.out.println("--------------- " + (sfert+1) + "/4 ----------------");
    			System.out.println("Citire    : " + timer.getDuration() + "ms");
    			try {
    				sleep(1000);										 // Se insereaza output la consola si sleep (1000) pentru a evidentia etapele comunicarii		
    			} 
    			catch (InterruptedException e) {
    				System.out.println("Eroare la intrarea producatorului in NotRunnable!");
    			}
    		}    
    		
    		in.close();													// inchidem fisierul
    	} catch (FileNotFoundException e) {								// fisierul nu exista
	        System.out.println("Fisierul nu a fost gasit!");			
	        System.out.println("Exceptie : " + e.getMessage());
	        System.exit(0);
	    } catch(IOException e) {										// eroare citire fisier
    		System.out.println("Eroare la citirea imaginii!");
    		System.out.println("Exceptie : " + e.getMessage());
    		System.exit(0);
    	}	
	}
}
