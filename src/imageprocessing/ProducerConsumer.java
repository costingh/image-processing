package imageprocessing;

/**
 * Abstract class that extends Thread Class and implements ProducerConsumerInterface
 * Producer and Consumer Thread will extend this class and will Override process() method
 * @author Constantin Gheorghe
 *
 */
public abstract class ProducerConsumer extends Thread implements ProducerConsumerInterface {
	/**
	 * Abstract method to be Overriden in Classes that will extend this one
	 */
    public abstract void process();				// metoda abstracta implementata de Producer cat si de Consumer
    
    /**
     * Method that will be called when the thread is started
     */
    public void run() { 
    	this.process(); 						// metoda care se ocupa de prelucrarea imaginii
    }
    
    /**
     * No-args Constructor that calls Thread Constructor
     */
    protected ProducerConsumer() {
        super(); 								// Apelam constructorul superclasei
    }
}
