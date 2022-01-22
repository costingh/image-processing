package test;

import java.util.Scanner;
import imageprocessing.*;

/**
 * Test Class for Decreasing Color Depth of An image Algorithm
 * @author Constantin Gheorghe
 *
 */
public class TestMain {
	/**
	 * Main method that will call Producer and Consumer Threads for each image to be processed
	 * It logs in the console all the steps alongside with time taken for each operation
	 * @param args is an array of variable length containing folderPath, inputName, outputName, newColorBitDepth (ranges from 1 to 7)
	 */
    public static void main(String... args)  {			
    	// daca nu au fost specificate argumentele, cerem informatii de la consola
    	if(args.length == 0) {
    		Scanner sc = new Scanner(System.in);		
    		System.out.println("Introduceti destinatia folderului (EXEMPLU: ./src/images/ ):");
    		String folderPath = sc.next();							// citire destinatie folder sursa
    		System.out.println("Introduceti numele imaginii de input (EXEMPLU: 1.bmp ):");
    		String inputFileName = sc.next();						// nume input
    		System.out.println("Introduceti numele output-ului (EXEMPLU: 1_modificata.bmp ):");
    		String outputFileName = sc.next();						// citirea numelui cu care va fi creat output-ul
    		System.out.println("Introduceti noul numar de biti per culoare (de la 1 la 7 inclusiv):");
    		int newColorDepth = sc.nextInt();						// citire numar de biti pe care vor fi reprezentate culorie
    		sc.close();												// inchidem Scanner-ul
    		
    		
    		if (newColorDepth < 1 || newColorDepth > 7) {			// daca informatiile nu sunt valide, incheiem executia programului
    			System.out.println("Noul numar de biti per culoare nu este valid!");
    			System.exit(0);										// incheiem executia programului
    		} else {
    			processImage(folderPath, inputFileName, outputFileName, newColorDepth);// apelare metoda ce porneste thread-urile
    		}	
    	} else {													// daca au fost specificate argumente din linia de comanda
    		if(args.length == 4) {									// numarul argumentelor trebuie sa fie 4: ./src/images | 1.bmp | 1nou.bmp | 3
    			String folderPath = args[0];						// primul argument este destinatia folderului in care se afla imaginea: ./src/images
    			String inputFileName = args[1];						// al doilea reprezinta numele imaginii
    			String outputFileName = args[2];					// al treilea reprezinta noua ddenumire a imaginii prelucrate
    			int newColorDepth = Integer.parseInt(args[3]);		// al treilea reprezinta nr de biti pe care va fi reprezentata fiecare culoare din componenta unui pixel
    			processImage(folderPath, inputFileName, outputFileName, newColorDepth);	// apelare metoda ce porneste thread-urile
    		} else {												// argumentele nu au fost specificate in intregime sau au fost specificate mai multe
    			System.out.println("Numarul de argumente nu este valid (este nevoie de 4)");
    			System.exit(0);										// incheiem executia programului
    		}
    	}
    }

    /**
     * 
     * @param folderPath represents the folder path of input image (without image name)
     * @param inputFileName	represents the image name
     * @param outputFileName is the name of the output to be created
     * @param newColorDepth is the new bit depth of each color from a pixel (initially is 8, so it must be between 1 and 7) 
     */
	private static void processImage(String folderPath, String inputFileName, String outputFileName,
			int newColorDepth) {
		System.out.println("\nCitire imagine");						
		Buffer buffer = new Buffer(); 												// cream o instanta de tip Buffer folosita in comun de producer si consumer
    	String inputPath = folderPath + inputFileName;								// luam sursa de la care trebuie citita imaginea
    	String destinationPath = folderPath + outputFileName;					    // destinatia unde va fi scrisa imaginea prelucrata
    	
        Producer producer = new Producer(buffer, inputPath); 						// cream un producer
        Consumer consumer = new Consumer(buffer, newColorDepth, destinationPath); 	// si un consumer
        
        producer.start();															// pornire producer
        consumer.start();															// pornire consumer
		
	}
}