package imageprocessing;

/**
 * Concrete Class that consumes the buffer content and stores it in a new array
 * It extends ProducerConsumer Abstract Class
 * @author Constantin Gheorghe
 *
 */
public class Consumer extends ProducerConsumer { 
	private Buffer buffer;						// bufferul in care producatorul pune imaginea iar consumatorul o citeste
	private byte[] imageBytes;					// aici 
	private int newColorDepth;					// noul numar de biti pe care vreau sa reprezint culorile (1,2,3,4,5,6,7)
	private String destinationPath;				// calea la care va fi scrisa imaginea modificata
	
	/**
	 * Constructor that initializes the refference to the buffer, the new color depth of 
	 * pixels of the image, and the destination path that will hold the new image
	 * @param buffer is a Buffer object (same refference in Producer and Consumer)
	 * @param newColorDepth	is an integer value that specifies the new color bits number used for representation
	 * @param destinationPath stores the destination path of the new image as a String
	 */
	public Consumer(Buffer buffer, int newColorDepth, String destinationPath) {
		this.buffer = buffer;					
		this.newColorDepth = newColorDepth;
		this.destinationPath = destinationPath;
	}

	/**
	 * Override of inherited method process() from parent Class ProducerConsumer
	 * It reads the content of the Buffer instance and adds it to the image byte array
	 * When the reading is done, a new image will be created as a result of processing the original image
	 */
	@Override 
	public void process () {
		RegisterTime timer = new RegisterTime();						// instanta care ne va ajuta sa calculam timpul de executie al unor metode
		
		for (int sfert = 0; sfert < 4; sfert++) { 						// pentru fiecare sfert de imagine
			timer.startTimer();											// ---- START ----
			byte[] image = buffer.get();								// primesc sfertul sub forma de byte[]
			this.imageBytes = concat(this.imageBytes,  image);			// adaugam noul sfert la cele deja existente (un fel de imageBytes.push(image))
			timer.stopTimer();											// ----  STOP ----
			System.out.println("Consum    : " + timer.getDuration() + "ms");
			try {
				sleep(1000); 											// Se insereaza output la consola si sleep (1000) pentru a evidentia etapele comunicarii		
			} 
			catch (InterruptedException e) {
				System.out.println("Eroare la intrarea consumatorului in NotRunnable!");
			}
		}

		Image img = new Image(this.imageBytes , this.destinationPath);	// creeam un array de bytes din imaginea ce se afla la destinationPath
		img.convertImageFromBytesToPixels(this.imageBytes);				// convertim byte[] bytes -> int[][] pixels
		img.setNewColorDepth(this.newColorDepth);						// setam campul newColorDepth, ce indica noua dimensiune a fiecarei culori din pixel
		img.modifyImage();												// parcurgem pixels si modificam structura fiecarui pixel utilizand campul newColorDepth
		img.writeImage();												// scriem imaginea nou formata la destinationPath
	}
	
	/**
	 * Method that merges two byte arrays
	 * @param a first byte array
	 * @param b	second byte array
	 * @return a byte array by concatenating a and b
	 */
	public byte[] concat(byte[] a, byte[] b) {							// metoda ce efectueaza concatenarea de 2 array-uri de tip byte
		if(a == null) return b;											// daca primul array este null, rezultatul va fi cel de-al doilea
		byte[] c = new byte[a.length + b.length];						// alocam spatiu pt un nou array ce are dimensiunea celor 2 adunate
		System.arraycopy(a, 0, c, 0, a.length);							// copiem din array-ul a, de la indexul 0, in array-ul c, la pozitia 0, a.length elemente
		System.arraycopy(b, 0, c, a.length, b.length);					// copiem din array-ul b, de la indexul 0, in array-ul c, la pozitia a.length, a.length elemente
		return c;														// returnam noul array
	}
}