package imageprocessing;

/**
 * Clasa in care punem cate un sfert din imagine
 * Producatorul citeste sfertul de imagine din fisier si il pune in buffer
 * Consumatorul ia sfertul de imagine din buffer si il consuma
 * metodele get() si put() sunt sincronizate, pentru ca Producatorul si Consumatorul
 * sa se astepte unul pe celalalt
 */
/**
 * Class that holds 1/4 of an image as a byte array
 * It is used by the Producer and Consumer at the same time, so it has to use synchronized methods to get and store info
 * @author Constantin Gheorghe
 *
 */
public class Buffer {
	private byte[] bytes; 								// aici punem cate un sfert de imagine pe rand
	private boolean available = false; 					// specificam daca din buffer se poate lua sau pune
	
	/**
	 * Synchronized method for getting the byte array 
	 * It uses semaphore and notifyAll() to communicate with other threads
	 * @return an array of bytes 
	 */
	public synchronized byte[] get () { 				// metoda sincrona pentru returnarea array-ului de bytes
		while (!available) { 							// daca buffer-ul este folosit de producer
			try {
				wait (); 								// consumer-ul asteapta ca producer-ul sa termine de scris sfertul de imagine
			} 
			catch (InterruptedException e) { 			// exceptie la intrarea in Not Runnable
				System.out.println("Eroare aparuta la asteptarea Producatorului de catre Consumator!");
			}
		}
		available = false; 								// daca available a devenit true, continutul va fi luat de catre Consumer
		notifyAll(); 									// notificam producatorul
		return bytes; 									// returnam continutul buffer-ului
	}
	
	/**
	 * Synchronized method for setting the byte array 
	 * It uses semaphore and notifyAll() to communicate with other threads
	 */
	public synchronized void put (byte[] bytes) { 
		while (available) {
			try {
				wait();									// daca buffer-ul este folosit de Consumer, asteptam	
			} 
			catch (InterruptedException e) {
				System.out.println("Eroare aparuta la asteptarea Consumatorului de catre Producator!");
			}
		}
		this.bytes = bytes;								// salvam array-ul de bytes
		available = true; 								// indicam faptul ca producatorul a pus o valoare noua in buffer,
		notifyAll(); 									// si notificam consumatorul
	}
}